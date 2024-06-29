package com.hackaton.marjopay.repository;

import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment deleteById(long id);

    Payment getPaymentsByUserCpf(String cpf);

}
