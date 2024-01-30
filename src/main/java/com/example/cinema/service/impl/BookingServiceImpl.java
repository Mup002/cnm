package com.example.cinema.service.impl;

import com.example.cinema.dto.response.SiteDetailsResponse;
import com.example.cinema.entity.SiteDetails;
import com.example.cinema.repository.SiteDetailsRepository;
import com.example.cinema.service.BookingService;
import com.example.cinema.utils.mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final SiteDetailsRepository siteDetailsRepository;
    @Override
    public List<SiteDetailsResponse> getSiteDetails(Long idSite) {
        List<SiteDetails> siteDetails = siteDetailsRepository.findSiteDetailsBySiteId(idSite);
        return mapper.mapperToLstSiteDetailsResponse(siteDetails);
    }
}
