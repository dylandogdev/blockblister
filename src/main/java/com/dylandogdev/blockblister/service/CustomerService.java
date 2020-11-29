package com.dylandogdev.blockblister.service;

import com.dylandogdev.blockblister.entities.CustomerEntity;
import com.dylandogdev.blockblister.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerEntity> getAllCustomers(Integer page, Integer limit, String sort) {
        Pageable paging = PageRequest.of(page, limit, Sort.by(sort));

        Page<CustomerEntity> pagedResult = customerRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        }
        return new ArrayList<CustomerEntity>();
    }
}
