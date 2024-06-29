package com.hackaton.marjopay.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.hackaton.marjopay.model.User;

import lombok.Getter;

@Getter
public class UserPrincipal {
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	private UserPrincipal(User user) {
		this.username = user.getCpf();
		this.password = user.getPassword();

//		this.authorities = user.getRoles().stream().map(role -> {
//			return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
//		}).collect(Collectors.toList());
	}

	public static UserPrincipal create(User user) {
		return new UserPrincipal(user);
	}
}