package com.hackaton.marjopay.controller;

import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.response.PaymentResponse;
import com.hackaton.marjopay.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class PaymentController {

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
    public PaymentResponse createPayment(@RequestParam Long userId, @RequestParam BigDecimal value){
        return paymentService.createPayment(userId, value);
    }

}
