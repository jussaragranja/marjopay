package com.hackaton.marjopay.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackaton.marjopay.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentResponse {

    private BigDecimal value;

    private Long userId;

    @DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy:HH:mm")
    private LocalDateTime dateTransaction;

    public PaymentResponse(BigDecimal value, Long userId, LocalDateTime dateTransaction) {
        this.value = value;
        this.userId = userId;
        this.dateTransaction = dateTransaction;
    }

}
