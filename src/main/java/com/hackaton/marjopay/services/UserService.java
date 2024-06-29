package com.hackaton.marjopay.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.hackaton.marjopay.model.User;
import com.hackaton.marjopay.model.request.UserRequest;
import com.hackaton.marjopay.model.response.UserResponse;
import com.hackaton.marjopay.repository.UserRepository;

import jakarta.transaction.Transactional;

/**
 * @author pedroRhamon
 */

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Page<UserRequest> findAll(Pageable pageable) {
		return userRepository.findAll(pageable).map(UserRequest::new);
	}
	
	public Optional<UserRequest> obterPorId(Long id) {
		return this.userRepository.findById(id).map(UserRequest::new);
	}
	
	public UserResponse updateUser(Long id, UserResponse userResponse) throws ResourceAccessException {
        User userExistent = userRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Error of id"));

        userExistent.setName(userResponse.getName());
        userExistent.setEmail(userResponse.getEmail());
        userExistent.setCpf(userResponse.getCpf());
        userExistent.setDateOfBirth(userResponse.getDateCreation());
        userExistent.setDateUpdated(LocalDateTime.now());        
        criptografarPasswrod(userExistent); 
        
        return new UserResponse(userRepository.save(userExistent));
    }

	
	private void criptografarPasswrod(User user) {
		String password = user.getPassword();
		user.setPassword(password);
	}
	
	@Transactional
	public UserResponse salvarUsuario(User user) throws ResourceAccessException {
		validarEmail(user.getEmail());
		user.setCpf(user.getCpf());
		user.setName(user.getName());
		user.setDateOfBirth(user.getDateOfBirth());
		user.setDateCreation(LocalDateTime.now());
		criptografarPasswrod(user);
		return  new UserResponse(userRepository.save(user));
	}

	private void validarEmail(String email) throws ResourceAccessException {
		boolean existe = this.userRepository.existsByEmail(email);
		if(existe) {
			throw new ResourceAccessException("email já cadastrado");
		}
	}
	
	public void deletarUsuario(Long id) {
		this.userRepository.deleteById(id);
	}

}
