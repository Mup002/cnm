package com.example.cinema.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data

public class TokenResponse {
    private String accessToken;
    private Date accessExpiration;
    private String refreshToken;
    private Date refreshExpiration;
}
