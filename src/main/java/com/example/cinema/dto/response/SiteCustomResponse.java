package com.example.cinema.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SiteCustomResponse {
    private String name;
    private String address;
    private List<Object> list = new ArrayList<>();
}
