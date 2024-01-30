package com.example.cinema.auth.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AccessTokenResponse {
    private String accessToken;
    private Date accessExpiration;
}
