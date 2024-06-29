package com.hackaton.marjopay.controller;

import com.hackaton.marjopay.exception.ResourceNotFoundException;
import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.hackaton.marjopay.util.Constant.*;
import static com.hackaton.marjopay.util.Constant.MESSAGE_PARAMETERS_EMPTY_OR_NULL;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payment")
    public List<Payment> listPayments(){
        return paymentRepository.findAll();
    }

    @GetMapping("/payment/{cpf}")
    public Payment getPaymentByCpf(@PathVariable(value = "cpf") String cpf){
        return paymentRepository.getPaymentsByUserCpf(cpf);
    }

    @DeleteMapping("/delete-payment/{id}")
    public Payment deletePaymentById(@PathVariable(value = "id") long id){
        if(paymentRepository.findById(id) == null) {
            throw new ResourceNotFoundException(MESSAGE_PAYMENT_NOT_FOUND);
        }
        return paymentRepository.deleteById(id);
    }

    @PutMapping("/update-payment")
    public Payment updatePayment(@RequestBody Payment payment){
        if(!paymentRepository.findById(payment.getId()).isPresent()) {
            throw new ResourceNotFoundException(MESSAGE_PAYMENT_NOT_FOUND);
        }
        return paymentRepository.save(payment);
    }

    @PostMapping("/create-payment")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Payment createPaymment(@RequestBody Payment payment){
        if(payment.getUser() == null || payment.getValue() == null || payment.getValue() == BigDecimal.ZERO){
            throw new ResourceNotFoundException(MESSAGE_PARAMETERS_EMPTY_OR_NULL);
        }
        return paymentRepository.save(payment);
    }

}
