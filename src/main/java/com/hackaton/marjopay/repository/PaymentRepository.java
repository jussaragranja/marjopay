package com.hackaton.marjopay.repository;

import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment deleteById(long id);


}
