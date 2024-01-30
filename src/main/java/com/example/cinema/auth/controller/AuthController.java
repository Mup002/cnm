package com.example.cinema.auth.controller;

import com.example.cinema.auth.dto.*;
import com.example.cinema.dto.request.UserRequest;
import com.example.cinema.dto.response.UserResponse;
import com.example.cinema.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest){
       AuthenticationResponse authenticationResponse= authService.login(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
    @GetMapping("/logout")
    public String logoutUser(){
        String status = authService.logout();
        return status;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserRequest userRequest){
        String status = authService.register(userRequest);
        return status;
    }
    @PostMapping("/refreshToken")
    public ResponseEntity<AccessTokenResponse> refresh(@RequestBody RefreshTokenRequest request){
        AccessTokenResponse response = authService.refreshTokenExpired(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
