package com.bci.users.controller;

import com.bci.users.dto.UserDTO;
import com.bci.users.exception.*;
import com.bci.users.mapper.UserMapper;
import com.bci.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody UserDTO userDTO) throws UserEmailException, UserPasswordException, UserDuplicatedException {
        return ResponseEntity.created(null).body(userService.createUser(userDTO));
    }


    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(HttpServletRequest request) throws UserTokenNotFoundException, UserNotFoundException {
        String token = request.getHeader("authorization");

        UserDTO userDTO = userService.login(token);
        return ResponseEntity.ok(userDTO);
    }




}
