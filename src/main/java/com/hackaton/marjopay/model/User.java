package com.hackaton.marjopay.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String name;
	
	private String cpf;
	
	private String passwrod;
	
	private Boolean status = false;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm")
	private LocalDateTime dateOfBirth;

	@Column(name = "date_Creation")
	@DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm")
	private LocalDateTime dateCreation;
	
	@Column(name = "date_Updated")
	@DateTimeFormat(pattern = "dd/MM/yyyy:HH:mm")
	private LocalDateTime dateUpdated;
}