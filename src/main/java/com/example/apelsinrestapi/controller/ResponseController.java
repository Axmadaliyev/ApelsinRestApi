package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.entity.Invoice;
import com.example.apelsinrestapi.repository.DetailRepository;
import com.example.apelsinrestapi.repository.InvoiceRepository;
import com.example.apelsinrestapi.service.DetailService;
import com.example.apelsinrestapi.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/response")
public class ResponseController {
    final DetailService detailService;
    final DetailRepository detailRepository;
    final InvoiceRepository invoiceRepository;
    final InvoiceService invoiceService;

    @GetMapping("/byOrdId/{id}")
    public HttpEntity<?> getAllByOrd(@PathVariable Integer id) {
        return ResponseEntity.ok().body(detailRepository.findAllByActiveTrueAndOrdId(id));
    }
    @GetMapping("/byProductId/{id}")
    public HttpEntity<?> getAllByProduct(@PathVariable Integer id) {
        return ResponseEntity.ok().body(detailRepository.findAllByActiveTrueAndProductId(id));
    }
    @GetMapping("/expired_invoices")
    public HttpEntity<?> getExpired_invoices() {
        List<Invoice> all = invoiceRepository.getAllByActiveTrue();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/wrong_date_invoices")
    public HttpEntity<?> getWrong_date_invoices() {
        List<Invoice> all = invoiceRepository.getWrong_date_invoices();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/overpaid_invoices")
    public HttpEntity<?> getOverpaid_invoices() {
        List<Invoice> all = invoiceRepository.getOverpaid_invoices();
        return ResponseEntity.ok().body(all);
    }
}
