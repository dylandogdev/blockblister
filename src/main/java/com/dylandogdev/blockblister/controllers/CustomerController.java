package com.dylandogdev.blockblister.controllers;

import com.dylandogdev.blockblister.entities.CustomerEntity;
import com.dylandogdev.blockblister.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAllCustomers(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") String sort
    ) {
        List<CustomerEntity> customers = service.getAllCustomers(page, limit, sort);

        return new ResponseEntity<List<CustomerEntity>>(customers, new HttpHeaders(), HttpStatus.OK);
    }
}
