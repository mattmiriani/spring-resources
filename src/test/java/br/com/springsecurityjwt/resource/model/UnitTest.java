package br.com.springsecurityjwt.resource.model;

import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {

    @Test
    public void testUnitConstructorWithNetwork() {
        Network network = new Network();
        network.setId(UUID.randomUUID());
        network.setName("Test Network");

        Unit unit = new Unit(new Unit(), network);

        assertNotNull(unit.getId());
        assertEquals("Test Network", unit.getNetwork().getName());
        assertTrue(unit.isActive());
        assertNotNull(unit.getCreatedAt());
        assertNotNull(unit.getUpdatedAt());
        assertEquals(unit.getCreatedAt(), unit.getUpdatedAt());
    }

    @Test
    public void testUnitGettersAndSetters() {
        Unit unit = new Unit();

        unit.setId(UUID.randomUUID());
        unit.setName("Test Unit");
        unit.setCreatedAt(ZonedDateTime.now().minusDays(1));
        unit.setUpdatedAt(ZonedDateTime.now());
        unit.setActive(true);

        assertEquals("Test Unit", unit.getName());
        assertTrue(unit.isActive());
        assertNotNull(unit.getCreatedAt());
        assertNotNull(unit.getUpdatedAt());
        assertNotEquals(unit.getCreatedAt(), unit.getUpdatedAt());
    }
}
