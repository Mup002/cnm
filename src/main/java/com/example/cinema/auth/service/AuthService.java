package com.example.cinema.auth.service;

import com.example.cinema.auth.dto.*;
import com.example.cinema.dto.request.UserRequest;
import com.example.cinema.dto.response.UserResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface AuthService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    String logout();
    String register(@RequestBody UserRequest request);
    AccessTokenResponse refreshTokenExpired(RefreshTokenRequest request);
}
