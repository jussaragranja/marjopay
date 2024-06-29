package com.hackaton.marjopay.controller;

import com.hackaton.marjopay.exception.ResourceNotFoundException;
import com.hackaton.marjopay.model.User;
import com.hackaton.marjopay.model.request.UserRequest;
import com.hackaton.marjopay.model.response.TokenResponse;
import com.hackaton.marjopay.model.response.UserResponse;
import com.hackaton.marjopay.repository.UserRepository;
import com.hackaton.marjopay.services.JwtService;
import com.hackaton.marjopay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.hackaton.marjopay.util.Constant.MESSAGE_USER_NOT_FOUND;
@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/users")
    public List<User> listUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(value = "id") long id){
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

    @PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody UserResponse response ) {
		try {
			User usuarioAutenticado = userService.autenticar(response.getCpf(), response.getPassword());
			String token = jwtService.gerarToken(usuarioAutenticado);
			TokenResponse tokenDTO = new TokenResponse( usuarioAutenticado.getCpf(), token);
			return ResponseEntity.ok(tokenDTO);
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
