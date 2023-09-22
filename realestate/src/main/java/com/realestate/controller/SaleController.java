package com.realestate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.payload.SaleDto;
import com.realestate.service.SaleService;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<SaleDto> saveSale(@RequestBody SaleDto saleDto) {
        SaleDto sale = saleService.saveSale(saleDto);
        
        return new ResponseEntity<SaleDto>(sale, HttpStatus.CREATED);
    }
}
