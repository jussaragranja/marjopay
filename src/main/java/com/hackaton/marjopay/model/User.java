package com.hackaton.marjopay.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.hackaton.marjopay.util.Constant.MESSAGE_MAX_MIN_SIZE;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Length(min = 1, max = 240, message = MESSAGE_MAX_MIN_SIZE)
	private String name;

	@Length(min = 1, max = 11, message = MESSAGE_MAX_MIN_SIZE)
	private String cpf;
	
	private String password;
	
	private Boolean status = false;

	@Length(min = 1, max = 120, message = MESSAGE_MAX_MIN_SIZE)
	private String email;

	@Length(min = 1, max = 40, message = MESSAGE_MAX_MIN_SIZE)
	private String phone;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dateOfBirth;

	@Column(name = "date_Creation")
	@DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy:HH:mm")
	private LocalDateTime dateCreation;
	
	@Column(name = "date_Updated")
	@DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm")
	@JsonFormat(pattern = "dd/MM/yyyy:HH:mm")
	private LocalDateTime dateUpdated;
}