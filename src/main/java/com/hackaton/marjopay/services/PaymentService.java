package com.hackaton.marjopay.services;

import com.hackaton.marjopay.exception.ResourceNotFoundException;
import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.User;
import com.hackaton.marjopay.repository.PaymentRepository;
import com.hackaton.marjopay.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.hackaton.marjopay.util.Constant.MESSAGE_PARAMETERS_EMPTY_OR_NULL;
import static com.hackaton.marjopay.util.Constant.MESSAGE_USER_NOT_FOUND;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    public Payment createPayment(Long userId, BigDecimal value) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_USER_NOT_FOUND));

        if (value == null || value.equals(BigDecimal.ZERO)) {
            throw new ResourceNotFoundException(MESSAGE_PARAMETERS_EMPTY_OR_NULL);
        }

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setValue(value);
        payment.setDateTransaction(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Transactional
    public void deletePaymentById(Long id) {
        if(!paymentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }

    @Transactional
    public Payment updatePayment(Payment payment) {
        if(!paymentRepository.existsById(payment.getId())) {
            throw new ResourceNotFoundException("Payment not found");
        }
        return paymentRepository.save(payment);
    }

}
