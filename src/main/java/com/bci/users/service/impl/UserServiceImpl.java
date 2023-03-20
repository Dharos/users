package com.bci.users.service.impl;

import com.bci.users.dto.UserDTO;
import com.bci.users.entity.User;
import com.bci.users.exception.*;
import com.bci.users.mapper.UserMapper;
import com.bci.users.repository.UserRepository;
import com.bci.users.service.UserService;
import com.bci.users.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private final String PASSWORD_REGEX = "^(?=.*?[A-Z]){1,1}(?=.*?[a-z])(?=.*?[0-9]){2,2}.{8,12}$";

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserDTO createUser(UserDTO userDTO) throws UserEmailException, UserPasswordException, UserDuplicatedException {
        log.info("Creando nuevo usario con email: {}", userDTO.getEmail());
        User user = userMapper.map(userDTO);
        if(!userRepository.findByEmail(user.getEmail()).equals(Optional.empty())){
            log.error("Ya existe un usuario con el email {} registrado", userDTO.getEmail());
            throw new UserDuplicatedException("Ya existe un usuario con el email "
                    + user.getEmail()+" registrado.");
        }
        checkEmailIsValid(user.getEmail());
        checkPasswordIsValid(user.getPassword());
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userCreated = userRepository.save(user);
        String token = JwtUtil.getAuthenticationJWT(user.getName(),user.getEmail());
        log.info("Usuario con email: {} creado", user.getEmail());
        return userMapper.map(userCreated,token);
    }


    public UserDTO login(String token) throws UserNotFoundException, UserTokenNotFoundException {
        if(token == null || token.equals("")){
            throw new UserTokenNotFoundException("Debe ingresar un token para realizar el login.");
        }

        String email = JwtUtil.getEmailUser(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UserNotFoundException("La credencial de acceso es invalida o no se encuentra registrada."));
        user.setLastLogin(LocalDateTime.now());
        user= userRepository.save(user);
        String newToken = JwtUtil.getAuthenticationJWT(user.getName(),user.getEmail());
        return userMapper.map(user,newToken);
    }

    private void checkEmailIsValid(String email) throws UserEmailException {
        if(!email.matches(EMAIL_REGEX)){
            throw new UserEmailException("El email es invalido, ingrese un email valido.");
        }
    }

    private void checkPasswordIsValid(String password) throws UserPasswordException {
        if (!password.matches(PASSWORD_REGEX)) {
            throw new UserPasswordException("El formato de la contraseña no es valida.");
        }
    }

}
