package com.example.apelsinrestapi.service;
import com.example.apelsinrestapi.dto.ApiResponse;
import com.example.apelsinrestapi.dto.PaymentDto;
import com.example.apelsinrestapi.entity.Category;
import com.example.apelsinrestapi.entity.Invoice;
import com.example.apelsinrestapi.entity.Payment;
import com.example.apelsinrestapi.repository.CategoryRepository;
import com.example.apelsinrestapi.repository.InvoiceRepository;
import com.example.apelsinrestapi.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    final PaymentRepository paymentRepository;
    final InvoiceRepository invoiceRepository;
    public ApiResponse add(PaymentDto dto) {
        Invoice invoice = new Invoice();
        invoice.setId(dto.getInvId());
        Invoice save = invoiceRepository.save(invoice);
        Payment payment = new Payment();
        payment.setInvId(save);
        payment.setAmount(dto.getAmmount());
        payment.setTime(dto.getTime());
        paymentRepository.save(payment);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, PaymentDto dto) {

        Optional<Payment> byId = paymentRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Payment payment = byId.get();
        payment.setTime(dto.getTime());
        payment.setAmount(dto.getAmmount());
        Invoice invoice = payment.getInvId();
        payment.setInvId(invoice);
        paymentRepository.save(payment);
        return new ApiResponse("Edited", true);
    }

}
