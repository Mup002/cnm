package com.example.cinema.repository;

import com.example.cinema.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByName(String name);
   ;

}
