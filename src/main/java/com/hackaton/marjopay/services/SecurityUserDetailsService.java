package com.hackaton.marjopay.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hackaton.marjopay.model.User;
import com.hackaton.marjopay.repository.UserRepository;

@Service
public class SecurityUserDetailsService implements UserDetailsService {

	private UserRepository usuarioRepository;

	public SecurityUserDetailsService(UserRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User usuarioEncontrado = usuarioRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email não cadastrado."));

		return org.springframework.security.core.userdetails.
				User.builder().username(usuarioEncontrado.getCpf())
				.password(usuarioEncontrado.getPassword())
				.build();
	}

}