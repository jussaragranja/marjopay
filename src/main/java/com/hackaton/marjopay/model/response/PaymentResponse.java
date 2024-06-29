package com.hackaton.marjopay.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.User;

import lombok.Data;

@Data
public class PaymentResponse {
	
	private Long id;
	
	private BigDecimal value;
	
	private Long user;
	
	private LocalDateTime dateTransaction;
	
	public PaymentResponse(Payment entity) {
		this.id = entity.getId();
		this.value = entity.getValue();
		this.user = entity.getUser().getId();
		this.dateTransaction = entity.getDateTransaction();
	}
}
