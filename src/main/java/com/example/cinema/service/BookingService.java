package com.example.cinema.service;

import com.example.cinema.dto.response.SiteDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    List<SiteDetailsResponse> getSiteDetails(Long idSite);
}
