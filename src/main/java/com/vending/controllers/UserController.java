package com.vending.controllers;

import com.vending.SecurityConfiguration;
import com.vending.models.User;
import com.vending.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin (origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1")
public class UserController {



        @Autowired
        private UserRepository userRepository;
    @Autowired
    private SecurityConfiguration securityConfiguration;

    Long maxUserId = 0L;

    @PostConstruct
    private void postContruct (){

        maxUserId = Optional.ofNullable(userRepository.findMaxUserId()).orElse(0L);

    }

    private Long getNextId () {

        maxUserId = maxUserId + 1;
        return maxUserId ;
    }


        @GetMapping("/users")
        public List<User> getAllUsers() {
                return userRepository.findAll();

        }
       @GetMapping("/users/{code}")
       Optional<User> one(@PathVariable String code) {
                return userRepository.findUserByCode(code);

        }




        @PostMapping("/users")
        public User createUser(@RequestBody User user) {


            if (userRepository.findUserByUsername(user.getUsername())!= null)
                throw new ResponseStatusException(HttpStatus.FOUND, "Username already exists");

            if (!user.getPassword().equals(user.getPasswordCheck())) {
                throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "Passwords do not match");
            }
            else{

                    Long id = getNextId();
                    System.out.println(id);
                    String encodedPassword = securityConfiguration.getPasswordEncoder().encode(user.getPassword());
                    User newUser = new User(id, id + "", user.getName(), user.getDescr(), user.getVersion(), user.getUsername(), encodedPassword, user.getPasswordCheck());

                    return userRepository.save(newUser);
                }

        }

        @PutMapping("/users/{code}")
        User replaceUser(@RequestBody User user, @PathVariable String code) {

                return userRepository.findUserByCode(code)
                        .map(newUser -> {
                                newUser.setCode(user.getCode());
                                newUser.setName(user.getName());
                              //  newUser.setVersion(user.getVersion());
                                newUser.setUsername(user.getUsername());
                                newUser.setPassword(user.getPassword());
                                return userRepository.save(user);
                        })
                        .orElseGet(() -> {
                                return userRepository.save(user);
                        });
        }



}
