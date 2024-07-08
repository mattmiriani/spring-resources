package br.com.springsecurityjwt.resource.dto;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NetworkDTOTest {

    @Test
    public void testNetworkDTO() {
        UnitDTO unit1 = new UnitDTO();
        UnitDTO unit2 = new UnitDTO();
        UserDTO user = new UserDTO();

        NetworkDTO network = new NetworkDTO();
        network.setName("Test Network");
        ZonedDateTime now = ZonedDateTime.now();
        network.setCreatedAt(now);
        network.setUpdatedAt(now.plusHours(1));
        network.setExpiredAt(now.plusDays(1));
        network.setActive(true);
        network.setUnits(Arrays.asList(unit1, unit2));
        network.setUser(user);

        assertEquals("Test Network", network.getName());
        assertEquals(now, network.getCreatedAt());
        assertEquals(now.plusHours(1), network.getUpdatedAt());
        assertEquals(now.plusDays(1), network.getExpiredAt());
        assertTrue(network.isActive());
        assertEquals(2, network.getUnits().size());
        assertEquals(unit1, network.getUnits().get(0));
        assertEquals(unit2, network.getUnits().get(1));
        assertEquals(user, network.getUser());
    }
}
