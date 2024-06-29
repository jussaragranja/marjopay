package com.hackaton.marjopay.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackaton.marjopay.model.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PaymentResponse {
	
	private BigDecimal value;
	
	private Long user;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dateTransaction;
	
	public PaymentResponse(BigDecimal value, Long user, LocalDateTime dateTransaction) {
		this.value = value;
		this.user = user;
		this.dateTransaction = dateTransaction;
	}
}
