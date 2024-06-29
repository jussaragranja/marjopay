package com.hackaton.marjopay.controller;

import com.hackaton.marjopay.exception.ResourceNotFoundException;
import com.hackaton.marjopay.model.User;
import com.hackaton.marjopay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.hackaton.marjopay.util.Constant.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_USER_NOT_FOUND));
    }

    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestBody User user){
        if(!userRepository.findById(user.getId()).isPresent()) {
            throw new ResourceNotFoundException(MESSAGE_USER_NOT_FOUND);
        }
        userRepository.delete(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public User deleteUserById(@PathVariable(value = "id") long id){
        if(userRepository.findById(id) == null) {
            throw new ResourceNotFoundException(MESSAGE_USER_NOT_FOUND);
        }
        return userRepository.deleteById(id);
    }

    @PutMapping("/update-user")
    public User updateUser(@RequestBody User user){
        if(!userRepository.findById(user.getId()).isPresent()) {
            throw new ResourceNotFoundException(MESSAGE_USER_NOT_FOUND);
        }
        return userRepository.save(user);
    }

    @PostMapping("/create-user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        if (user.getId() != null){
            if(userRepository.findById(user.getId()).isPresent()) {
                throw new ResourceNotFoundException(MESSAGE_CPF_REGISTERED);
            }
        }
        if(user.getCpf() == null || user.getName() == null ||
                user == null || user.getEmail() == null || user.equals("")){
            throw new ResourceNotFoundException(MESSAGE_PARAMETERS_EMPTY_OR_NULL);
        }
        user.setDateCreation(LocalDateTime.now());
        return userRepository.save(user);
    }

}
