package com.example.cinema.auth.dto;

import lombok.Data;

@Data
public class TokenRequest {
    private String refreshToken;
}
