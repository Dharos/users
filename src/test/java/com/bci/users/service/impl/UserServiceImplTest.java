package com.bci.users.service.impl;

import com.bci.users.dto.UserDTO;
import com.bci.users.entity.User;
import com.bci.users.exception.*;
import com.bci.users.mapper.UserMapper;
import com.bci.users.repository.UserRepository;
import com.bci.users.utility.UserUtility;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class UserServiceImplTest {

    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    private UserMapper userMapper = mock(UserMapper.class);

    private UserRepository userRepository = mock(UserRepository.class);

    private UserServiceImpl userService = new UserServiceImpl(userRepository,userMapper,passwordEncoder);


    @Test
    void createUserTest() throws UserEmailException, UserPasswordException, UserDuplicatedException {
        when(this.userMapper.map(any(UserDTO.class))).thenReturn(UserUtility.generateUser());
        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        when(this.userRepository.save(any(User.class))).thenReturn(UserUtility.generateUserSaved());
        when(this.userMapper.map(any(User.class))).thenReturn(UserUtility.generateUserDTOSaved());
        when(this.passwordEncoder.encode((String) any())).thenReturn(UserUtility.generateUserDTO().getPassword());
        UserDTO userDTO = UserUtility.generateUserDTO();
        UserDTO response = this.userService.createUser(userDTO);
        UserDTO expected = UserUtility.generateUserDTOSaved();

        assertEquals(expected.getName(), response.getName());
        assertEquals(expected.getEmail(), response.getEmail());
        assertEquals(expected.getCreated().toLocalDate(), LocalDateTime.now().toLocalDate());

    }

    @Test
    void createUserAndThrowUserDuplicatedExceptionTest() throws UserEmailException, UserPasswordException, UserDuplicatedException {
        User user = UserUtility.generateUser();
        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.of(user));
        when(this.userMapper.map(any(UserDTO.class))).thenReturn(UserUtility.generateUser());
        assertThrows(UserDuplicatedException.class, () -> this.userService.createUser(UserUtility.generateUserDTO()));

    }

    @Test
    void createUserAndThrowUserEmailExceptionTest() {
        User user = UserUtility.generateUser();
        user.setEmail("EmailNotValid");
        UserDTO userDTO = UserUtility.generateUserDTO();
        userDTO.setEmail("EmailNotValid");
        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        when(this.userMapper.map(any(UserDTO.class))).thenReturn(user);

        assertThrows(UserEmailException.class, () -> this.userService.createUser(userDTO));

    }

    @Test
    void createUserAndThrowPasswordNotValidExceptionTest() {
        User user = UserUtility.generateUser();
        user.setPassword("PasswordNotValid");
        UserDTO userDTO = UserUtility.generateUserDTO();
        userDTO.setPassword("PasswordNotValid");
        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        when(this.userMapper.map(any(UserDTO.class))).thenReturn(user);

        assertThrows(UserPasswordException.class, () -> this.userService.createUser(userDTO));
    }

    @Test
    @Disabled
    void loginTest() throws UserNotFoundException, UserTokenNotFoundException {

        String token = UserUtility.getUserToken();
//        when(this.userRepository.findByEmailAndToken((String)any(),(String)any()))
//                .thenReturn(Optional.of(UserUtility.generateUserSaved()));
//        doReturn(Optional.of(UserUtility.generateUserSaved())).when(this.userRepository)
//                .findByEmailAndToken((String)any(),(String)any());
//        mockedStatic.when(()-> JwtUtil.getEmailUser((String)any())).thenReturn(UserUtility.generateUser().getEmail());
        UserDTO response = this.userService.login(token);

        assertTrue(response.getLastLogin() != null);
        assertEquals(response.getLastLogin().toLocalDate(), LocalDateTime.now().toLocalDate());
        assertNotEquals(response.getToken(), token);
    }

}

