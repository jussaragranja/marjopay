package com.hackaton.marjopay.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.hackaton.marjopay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackaton.marjopay.exception.ResourceNotFoundException;
import com.hackaton.marjopay.model.User;
import com.hackaton.marjopay.model.request.UserRequest;
import com.hackaton.marjopay.model.response.UserResponse;
import com.hackaton.marjopay.services.UserService;
import static com.hackaton.marjopay.util.Constant.*;
import static com.hackaton.marjopay.util.Constant.MESSAGE_PARAMETERS_EMPTY_OR_NULL;
@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public UserRequest getUserById(@PathVariable(value = "id") long id){
        return userService.obterPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_USER_NOT_FOUND));
    }

    @DeleteMapping("/delete-user/{id}")
    public void deleteUserById(@PathVariable(value = "id") Long id){
        if(userService.obterPorId(id) == null) {
            throw new ResourceNotFoundException(MESSAGE_USER_NOT_FOUND);
        }
        userService.deletarUsuario(id);
    }

    @PutMapping("/update-user")
    public UserResponse updateUser(@RequestBody UserResponse userResponse, Long id){
        return userService.updateUser(id, userResponse);
    }

    @PostMapping("/create-user")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setCpf(userRequest.getCpf());
        user.setPassword(userRequest.getPassword());
        user.setStatus(userRequest.getStatus());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setDateOfBirth(userRequest.getDateOfBirth());

        return userService.salvarUsuario(user);
    }

}
