package br.com.springsecurityjwt.resource.mapper;

import br.com.springsecurityjwt.resource.dto.UserDTO;
import br.com.springsecurityjwt.resource.model.TBUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toUserDTO(TBUser user);

    TBUser toUser(UserDTO userDTO);
}
