package com.dylandogdev.blockblister.repository;

import com.dylandogdev.blockblister.entities.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Integer> {
}
