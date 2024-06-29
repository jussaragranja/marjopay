package com.hackaton.marjopay.model.request;

import static com.hackaton.marjopay.util.Constant.MESSAGE_MAX_MIN_SIZE;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import com.hackaton.marjopay.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author pedroRhamon
 */

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

	@Schema(example = "string")
	private String name;

	@Schema(example = "string")
	private String cpf;

	@Schema(example = "string")
	private String password;

	@Schema(example = "true")
	private Boolean status;

	@Schema(example = "string")
	private String email;

	@Length(min = 1, max = 40, message = "Length must be between 1 and 40")
	@Schema(example = "string")
	private String phone;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;

	public UserRequest(User entity) {
		this.name = entity.getName();
		this.cpf = entity.getCpf();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.dateOfBirth = entity.getDateOfBirth();
		this.status = entity.getStatus();
	}
}
