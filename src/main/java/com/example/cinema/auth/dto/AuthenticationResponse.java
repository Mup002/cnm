package com.example.cinema.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
    private String username;
    private String password;
    private TokenResponse tokenLst;
}
