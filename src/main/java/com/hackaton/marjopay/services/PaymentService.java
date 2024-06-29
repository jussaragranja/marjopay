package com.hackaton.marjopay.services;

import com.hackaton.marjopay.exception.ResourceNotFoundException;
import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.User;
import com.hackaton.marjopay.model.response.PaymentResponse;
import com.hackaton.marjopay.repository.PaymentRepository;
import com.hackaton.marjopay.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.hackaton.marjopay.util.Constant.MESSAGE_PARAMETERS_EMPTY_OR_NULL;
import static com.hackaton.marjopay.util.Constant.MESSAGE_USER_NOT_FOUND;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    public PaymentResponse createPayment(Long userId, BigDecimal value) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (value == null || value.equals(BigDecimal.ZERO)) {
            throw new ResourceNotFoundException("Payment value cannot be null or zero");
        }

        Payment payment = new Payment();
        payment.setUser(user);
        payment.setValue(value);
        payment.setDateTransaction(LocalDateTime.now());

        Payment savedPayment = paymentRepository.save(payment);

        return new PaymentResponse(savedPayment.getValue(), user.getId(), savedPayment.getDateTransaction());
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
