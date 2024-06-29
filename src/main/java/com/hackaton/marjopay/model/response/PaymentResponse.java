package com.hackaton.marjopay.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.User;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class PaymentResponse {

	private BigDecimal value;
	
	private Long user;

	@DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy:HH:mm")
	private LocalDateTime dateTransaction;

	public PaymentResponse(BigDecimal value, Long user, LocalDateTime dateTransaction) {
		this.value = value;
		this.user = user;
		this.dateTransaction = dateTransaction;
	}

}
