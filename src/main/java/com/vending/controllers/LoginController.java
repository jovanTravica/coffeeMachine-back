package com.vending.controllers;

import com.vending.SecurityConfiguration;
import com.vending.models.LoginUser;
import com.vending.models.User;
import com.vending.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api")
public class LoginController {

    @Autowired
   SecurityConfiguration securityConfiguration;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/login")
    public User login (@RequestBody LoginUser loginDto) throws Exception {

     String userName = loginDto.getUsername();
     String loginPassword =loginDto.getPassword();
     Optional <User> user = Optional.ofNullable(userRepository.findUserByUsername(userName)) ;

if (!user.isPresent())
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid username");

     String userPassword = user.get().getPassword();

         if (securityConfiguration.getPasswordEncoder().matches(loginPassword, userPassword))
             return user.get();
         else
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");

}}
