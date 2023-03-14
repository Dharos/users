package com.bci.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String id;

    @JsonFormat(pattern = "MMM d,yyyy HH:mm:ss a")
    private LocalDateTime created;
    @JsonProperty("last_login")

    @JsonFormat(pattern = "MMM d,yyyy HH:mm:ss a")
    private LocalDateTime lastLogin;

    private String token;

    @JsonProperty("is_active")
    private boolean isActive;

    private String name;

    private String email;

    private String password;

    private Set<PhoneDTO> phones;

}
