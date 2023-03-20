package com.bci.users.service;

import com.bci.users.dto.UserDTO;
import com.bci.users.exception.*;

public interface UserService {
    UserDTO createUser(UserDTO user) throws UserEmailException, UserPasswordException, UserDuplicatedException;

    UserDTO login(String token) throws UserNotFoundException, UserTokenNotFoundException;
}
