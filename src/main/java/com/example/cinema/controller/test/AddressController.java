package com.example.cinema.controller.test;

import com.example.cinema.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    @GetMapping("/getAllAddressByMovie/{id}/on/{date}")
    public ResponseEntity<List<String>> getAllAddressByMovie(@PathVariable("id")Long id, @PathVariable("date")String date){
        List<String> reponses = addressService.findByDayAndMovie(id, date);
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }
}
