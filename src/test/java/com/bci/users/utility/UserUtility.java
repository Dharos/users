package com.bci.users.utility;

import com.bci.users.dto.UserDTO;
import com.bci.users.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class UserUtility {
    public static User generateUser() {
        User user = new User();
        user.setActive(true);
        user.setEmail("john.doe@gmail.com");
        user.setId("001");
        user.setName("Jhon Doe");
        user.setPassword("z5fydAdhys9a");
        user.setPhones(new ArrayList<>());
        return user;
    }

    public static UserDTO generateUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setActive(true);
        userDTO.setEmail("jhon.doe@gmail.com");
        userDTO.setName("Jhon Doe");
        userDTO.setPassword("z5fydAdhys9a");
        userDTO.setPhones(new HashSet<>());
        return userDTO;
    }

    public static User generateUserSaved() {
        User user = new User();
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setEmail("john.doe@gmail.com");
        user.setId("001");
        user.setLastLogin(null);
        user.setName("Jhon Doe");
        user.setPassword("z5fydAdhys9a");
        user.setPhones(new ArrayList<>());
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiSmhvbiBEb2UiLCJlbWFpbCI6Impob24uZG9lQGdtYWlsLmNvbSIsInN1YiI6Impob24uZG9lQGdtYWlsLmNvbSIsImlhdCI6MTY3ODgxMjcyMn0.4Gf8RqKfUJ2eHTezGj56Lq-8YiQA03G_LssUDcIMzF4");
        return user;
    }

    public static UserDTO generateUserDTOSaved() {
        UserDTO userDTO = new UserDTO();
        userDTO.setCreated(LocalDateTime.now());
        userDTO.setLastLogin(null);
        userDTO.setActive(true);
        userDTO.setEmail("jhon.doe@gmail.com");
        userDTO.setName("Jhon Doe");
        userDTO.setPassword("z5fydAdhys9a");
        userDTO.setPhones(new HashSet<>());
        userDTO.setToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiSmhvbiBEb2UiLCJlbWFpbCI6Impob24uZG9lQGdtYWlsLmNvbSIsInN1YiI6Impob24uZG9lQGdtYWlsLmNvbSIsImlhdCI6MTY3ODgxMjcyMn0.4Gf8RqKfUJ2eHTezGj56Lq-8YiQA03G_LssUDcIMzF4");
        return userDTO;
    }

    public static String getUserToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiSmhvbiBEb2UiLCJlbWFpbCI6Impob24uZG9lQGdtYWlsLmNvbSIsInN1YiI6Impob24uZG9lQGdtYWlsLmNvbSIsImlhdCI6MTY3ODgxMjcyMn0.4Gf8RqKfUJ2eHTezGj56Lq-8YiQA03G_LssUDcIMzF4";
        return token;
    }

    public static User generateUserSavedWithLastLogin() {
        User user = new User();
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setEmail("john.doe@gmail.com");
        user.setId("001");
        user.setLastLogin(null);
        user.setName("Jhon Doe");
        user.setPassword("z5fydAdhys9a");
        user.setPhones(new ArrayList<>());
        user.setLastLogin(LocalDateTime.now());
        user.setToken("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiSmhvbiBEb2UiLCJlbWFpbCI6Impob24uZG9lQGdtYWlsLmNvbSIsInN1YiI6Impob24uZG9lQGdtYWlsLmNvbSIsImlhdCI6MTY3ODgxMjcyMn0.4Gf8RqKfUJ2eHTezGj56Lq-8YiQA03G_LssUDcIMzF4");
        return user;
    }

    public static UserDTO generateUserDTOSavedWithLastLogin() {
        UserDTO userDto = UserUtility.generateUserDTOSaved();
        userDto.setLastLogin(LocalDateTime.now());
        return userDto;
    }
}
