package com.hackaton.marjopay.model.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.hackaton.marjopay.model.Payment;
import com.hackaton.marjopay.model.User;

import lombok.Data;

/**
 * @author pedroRhamon
 */
@Data
public class PaymentRequest {
	
	private Long id;
	
	private BigDecimal value;
	
	private Long userId;
	
	private LocalDateTime dateTransaction;
	
	public PaymentRequest(Payment entity) {
		this.id = entity.getId();
		this.value = entity.getValue();
		this.userId = entity.getUser().getId();
		this.dateTransaction = entity.getDateTransaction();
	}

}
