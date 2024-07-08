package br.com.springsecurityjwt.resource.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TBUserTest {

    @Test
    public void testGettersAndSetters() {
        TBUser user = new TBUser();

        user.setId(1L);
        user.setUsername("john_doe");
        user.setPassword("password123");

        assertEquals(1L, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
}
