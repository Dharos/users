package com.bci.users.service;

import com.bci.users.dto.UserDTO;
import com.bci.users.exception.*;

public interface UserService {
    public UserDTO createUser(UserDTO user) throws UserEmailException, UserPasswordException, UserDuplicatedException;

    public UserDTO login(String token) throws UserNotFoundException, UserTokenNotFoundException;
}
