package com.hackaton.marjopay.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {
	
	private String nome;
	private String token;

}
