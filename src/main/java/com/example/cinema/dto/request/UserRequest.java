package com.example.cinema.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String date;
    private String gender;
    private String address;
//    private String role;
    private String phone;
}
