package com.example.cinema.auth.service.impl;

import com.example.cinema.auth.dto.*;
import com.example.cinema.dto.request.UserRequest;
import com.example.cinema.entity.Role;
import com.example.cinema.entity.User;
import com.example.cinema.repository.UserRepository;
import com.example.cinema.auth.service.AuthService;
import com.example.cinema.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthenticationResponse auth = new AuthenticationResponse();
        auth.setUsername(authenticationRequest.getUsername());
        auth.setPassword(authenticationRequest.getPassword());
        TokenResponse tokenResponse = new TokenResponse();
        String accessToken  = jwtTokenProvider.createToken(authentication);
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setAccessExpiration(jwtTokenProvider.getExpiration(accessToken, jwtTokenProvider.getAccessKey()));
        String refreshToken = jwtTokenProvider.refreshToken(authentication);
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setRefreshExpiration(jwtTokenProvider.getExpiration(refreshToken, jwtTokenProvider.getRefreshKey()));
        auth.setTokenLst(tokenResponse);

        return auth;
    }

    @Override
    public String logout() {
        SecurityContextHolder.clearContext();
        return "Logout successful";
    }

    @Override
    public String register(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        user.setGender(request.getGender());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date formated = new Date();
        try {
             formated = format.parse(request.getDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        user.setDate(formated);
//        user.setDate(request.getDate());
        user.setRole(Role.USER);
        userRepository.save(user);
        return "done";
    }

    @Override
    public AccessTokenResponse refreshTokenExpired(RefreshTokenRequest request) {
        String newAccessToken = jwtTokenProvider.refreshTokenIfExpired(request.getRefreshToken());
        AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
        accessTokenResponse.setAccessToken(newAccessToken);
        accessTokenResponse.setAccessExpiration(jwtTokenProvider.getExpiration(newAccessToken, jwtTokenProvider.getAccessKey()));
        return accessTokenResponse;
    }


}
