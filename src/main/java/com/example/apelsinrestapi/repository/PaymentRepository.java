package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    List<Payment> findAllByActiveTrue();
}
