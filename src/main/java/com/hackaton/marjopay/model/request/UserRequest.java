package com.hackaton.marjopay.model.request;

import static com.hackaton.marjopay.util.Constant.MESSAGE_MAX_MIN_SIZE;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.hackaton.marjopay.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
	
	private String name;

	private String cpf;
	
	private String password;
	
	private Boolean status = false;

	private String email;

	@Length(min = 1, max = 40, message = MESSAGE_MAX_MIN_SIZE)
	private String phone;

	private LocalDateTime dateOfBirth;
	
	public UserRequest(User entity) {
	    this.name = entity.getName();
	    this.cpf = entity.getCpf();
	    this.email = entity.getEmail();
	    this.phone = entity.getPhone();
	    this.dateOfBirth = entity.getDateOfBirth();
	    this.status = entity.getStatus();
	}
}
