package com.example.apelsinrestapi.repository;

import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail,Integer> {
    List<Detail> findAllByActiveTrue();
    List<Detail> findAllByActiveTrueAndProductId(Integer id);
    List<Detail> findAllByActiveTrueAndOrdId(Integer id);

}
