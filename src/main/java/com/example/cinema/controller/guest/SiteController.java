package com.example.cinema.controller.guest;

import com.example.cinema.dto.request.ShowtimeCustomRequest;
import com.example.cinema.dto.response.AddressResponse;
import com.example.cinema.dto.response.SiteCustomResponse;
import com.example.cinema.dto.response.SiteDetailsResponse;
import com.example.cinema.dto.response.SiteNameResponse;
import com.example.cinema.entity.Address;
import com.example.cinema.entity.Site;
import com.example.cinema.service.SiteService;
import com.example.cinema.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/site")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;
    @PostMapping("/findSite")
    public ResponseEntity<List<SiteCustomResponse>> find(@RequestBody ShowtimeCustomRequest request){
        List<SiteCustomResponse> responses = siteService.getAllSiteByMovie(request);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    @GetMapping("/getAllAddress")
    public ResponseEntity<List<AddressResponse>> getAllAddress(){
        List<AddressResponse> response = siteService.getAllAddressSite();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/getSiteByAddress/{id}")
    public ResponseEntity<List<SiteNameResponse>> getAllSiteByAddress(@PathVariable("id")Long id){
        List<SiteNameResponse> responses = siteService.getAllSiteByAddress(id);
        return new ResponseEntity<>(responses,HttpStatus.OK);
    }
}
