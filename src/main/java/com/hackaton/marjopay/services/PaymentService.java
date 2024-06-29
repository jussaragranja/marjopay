//package com.hackaton.marjopay.services;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.ResourceAccessException;
//
//import com.hackaton.marjopay.model.Payment;
//import com.hackaton.marjopay.model.User;
//import com.hackaton.marjopay.model.request.PaymentRequest;
//import com.hackaton.marjopay.model.response.PaymentResponse;
//import com.hackaton.marjopay.model.response.UserResponse;
//import com.hackaton.marjopay.repository.PaymentRepository;
//
//import jakarta.transaction.Transactional;
//
///**
// * @author pedroRhamon
// */
//@Service
//public class PaymentService {
//	
//	@Autowired
//	private PaymentRepository paymentRepository;
//	
//	public Page<PaymentRequest> findAll(Pageable pageable) {
//		return paymentRepository.findAll(pageable).map(PaymentRequest::new);
//	}
//	
//	public Optional<PaymentRequest> obterPorId(Long id) {
//		return this.paymentRepository.findById(id).map(PaymentRequest::new);
//	}
//	
//	public PaymentResponse updateUser(Long id, PaymentResponse paymentResponse) throws ResourceAccessException {
//        Payment paymentExistent = paymentRepository.findById(id)
//                .orElseThrow(() -> new ResourceAccessException("Error of id"));
//
//        paymentExistent.setValue(paymentResponse.getValue());
//        paymentExistent.setDateTransaction(LocalDateTime.now());
//        paymentExistent.setUser(paymentResponse.getUser());
//        
//        return new PaymentResponse(paymentRepository.save(paymentExistent));
//    }
//
//	
//	private void criptografarPasswrod(User user) {
//		String password = user.getPassword();
//		user.setPassword(password);
//	}
//	
//	@Transactional
//	public ponse salvarUsuario(User user) throws ResourceAccessException {
//		validarEmail(user.getEmail());
//		user.setCpf(user.getCpf());
//		user.setName(user.getName());
//		user.setDateOfBirth(user.getDateOfBirth());
//		user.setDateCreation(LocalDateTime.now());
//		criptografarPasswrod(user);
//		return  new UserResponse(paymentRepository.save(user));
//	}
//
//	private void validarEmail(String email) throws ResourceAccessException {
//		boolean existe = this.paymentRepository.existsByEmail(email);
//		if(existe) {
//			throw new ResourceAccessException("email já cadastrado");
//		}
//	}
//	
//	public void deletarUsuario(Long id) {
//		this.userRepository.deleteById(id);
//	}
//
//
//}
