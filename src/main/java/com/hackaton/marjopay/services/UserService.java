package com.hackaton.marjopay.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.hackaton.marjopay.model.User;
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
	
	private PasswordEncoder encoder;
	
	public User autenticar(String cpf, String senha) {
		Optional<User> usuario = this.userRepository.findByEmail(cpf);
		
		if(!usuario.isPresent()) {
			throw new ResourceAccessException("User present");
		}
		
		boolean senhasBatem = encoder.matches(senha, usuario.get().getPassword());
		
		if(!senhasBatem) {
			throw new ResourceAccessException("Password invalid");
		}

		return usuario.get();
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Optional<User> obterPorId(Long id) {
		return this.userRepository.findById(id);
	}
	
	public void autenticacaoSenha(String senha, Optional<User> user) throws ResourceAccessException {
		if(!user.isPresent()) {
			throw new ResourceAccessException("Usuario não encontrado");
		}
		boolean senhasBatem = encoder.matches(senha, user.get().getPassword());
		if(!senhasBatem) {
			throw new ResourceAccessException("password invalid");
		}
	}
	
	public UserResponse updateUser(Long id, UserResponse userResponse) throws ResourceAccessException {
        User userExistent = userRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Error of id"));

        userExistent.setName(userResponse.getName());
        userExistent.setEmail(userResponse.getEmail());
        userExistent.setCpf(userResponse.getCpf());
        userExistent.setDateOfBirth(userResponse.getDateOfBirth());
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
		return new UserResponse(userRepository.save(user));
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
