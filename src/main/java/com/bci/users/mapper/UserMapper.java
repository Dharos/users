package com.bci.users.mapper;

import com.bci.users.dto.PhoneDTO;
import com.bci.users.dto.UserDTO;
import com.bci.users.entity.Phone;
import com.bci.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "lastLogin", ignore = true)
    User map(UserDTO userDTO);

    @Mapping(target = "token",source = "token")
    UserDTO map(User user, String token);

    Set<Phone> mapPhone(Set<PhoneDTO> phoneDTOList);
    Set<PhoneDTO> mapPhoneDto(Set<Phone> phoneList);



}
