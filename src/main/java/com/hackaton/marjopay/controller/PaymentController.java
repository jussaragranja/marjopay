package com.hackaton.marjopay.controller;

import com.hackaton.marjopay.exception.ResourceNotFoundException;
import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.repository.PaymentRepository;
import com.hackaton.marjopay.services.PaymentService;
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

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public List<Payment> listPayments(){
        return paymentService.findAll();
    }

    @DeleteMapping("/delete-payment/{id}")
    public void deletePaymentById(@PathVariable(value = "id") long id){
        paymentService.deletePaymentById(id);
    }

    @PutMapping("/update-payment")
    public Payment updatePayment(@RequestBody Payment payment){
        return paymentService.updatePayment(payment);
    }


    @PostMapping("/create-payment")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Payment createPayment(@RequestParam Long userId, @RequestParam BigDecimal value){
        return paymentService.createPayment(userId, value);
    }

}
