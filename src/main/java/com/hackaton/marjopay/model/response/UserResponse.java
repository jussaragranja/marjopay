package com.hackaton.marjopay.model.response;

import static com.hackaton.marjopay.util.Constant.MESSAGE_MAX_MIN_SIZE;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.hackaton.marjopay.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {

	@Schema(example = "string")
	private String name;

	@Schema(example = "string")
	private String cpf;

	private String password;

	private Boolean status = false;

	@Schema(example = "string")
	private String email;

	@Length(min = 1, max = 40, message = "Length must be between 1 and 40")
	@Schema(example = "string")
	private String phone;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Schema(example = "10/10/2010")
	private LocalDate dateOfBirth;

	public UserResponse(User entity) {
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.dateOfBirth = entity.getDateOfBirth();
		this.status = entity.getStatus();
	}

}
