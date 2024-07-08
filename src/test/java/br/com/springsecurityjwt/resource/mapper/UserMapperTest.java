package br.com.springsecurityjwt.resource.mapper;

import br.com.springsecurityjwt.resource.dto.UserDTO;
import br.com.springsecurityjwt.resource.model.TBUser;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void testToUserDTO() {
        TBUser user = new TBUser();
        user.setUsername("testuser");

        UserDTO userDTO = mapper.toUserDTO(user);

        assertEquals(user.getUsername(), userDTO.getUsername());
    }

    @Test
    public void testToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");

        TBUser user = mapper.toUser(userDTO);

        assertEquals(userDTO.getUsername(), user.getUsername());
    }

    @Test
    public void testNullValues() {
        assertNull(mapper.toUserDTO(null));

        assertNull(mapper.toUser(null));
    }
}
