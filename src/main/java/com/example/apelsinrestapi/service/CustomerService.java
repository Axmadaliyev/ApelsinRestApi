package com.example.apelsinrestapi.service;
import com.example.apelsinrestapi.dto.ApiResponse;
import com.example.apelsinrestapi.entity.Category;
import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.repository.CategoryRepository;
import com.example.apelsinrestapi.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;
    public ApiResponse add(Customer customer) {
        customerRepository.save(customer);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, Customer customer) {

        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Customer edited = byId.get();
        edited.setName(customer.getName());
        customerRepository.save(edited);
        return new ApiResponse("Edited", true);
    }
}
