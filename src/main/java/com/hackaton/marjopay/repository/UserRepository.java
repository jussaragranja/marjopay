package com.hackaton.marjopay.repository;

import com.hackaton.marjopay.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User deleteById(long id);

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	User findByCpf(String username);

//	User findByUsernameFetchRoles(String username);

}
