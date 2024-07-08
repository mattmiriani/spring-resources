package br.com.springsecurityjwt.resource.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserDTOTest {

    @Test
    public void testUserDTO() {
        String username = "testuser";

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);

        assertEquals(username, userDTO.getUsername());
    }

    @Test
    public void testDefaultValues() {
        UserDTO userDTO = new UserDTO();

        assertNull(userDTO.getUsername());
    }

    @Test
    public void testSetAndGetMethods() {
        UserDTO userDTO = new UserDTO();

        String username = "testuser";
        userDTO.setUsername(username);

        assertEquals(username, userDTO.getUsername());

        String newUsername = "newuser";
        userDTO.setUsername(newUsername);

        assertEquals(newUsername, userDTO.getUsername());
    }
}