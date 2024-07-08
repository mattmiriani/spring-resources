package br.com.springsecurityjwt.resource.dto;

import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UnitDTOTest {

    @Test
    public void testUnitDTO() {
        UUID id = UUID.randomUUID();
        String name = "Test Unit";
        ZonedDateTime createdAt = ZonedDateTime.now();
        ZonedDateTime updatedAt = ZonedDateTime.now().plusHours(1);
        boolean active = true;

        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(id);
        unitDTO.setName(name);
        unitDTO.setCreatedAt(createdAt);
        unitDTO.setUpdatedAt(updatedAt);
        unitDTO.setActive(active);

        assertEquals(id, unitDTO.getId());
        assertEquals(name, unitDTO.getName());
        assertEquals(createdAt, unitDTO.getCreatedAt());
        assertEquals(updatedAt, unitDTO.getUpdatedAt());
        assertTrue(unitDTO.isActive());
    }

    @Test
    public void testDefaultValues() {
        UnitDTO unitDTO = new UnitDTO();

        assertNull(unitDTO.getId());
        assertNull(unitDTO.getName());
        assertNull(unitDTO.getCreatedAt());
        assertNull(unitDTO.getUpdatedAt());
        assertFalse(unitDTO.isActive());
    }

    @Test
    public void testSetAndGetMethods() {
        UUID id = UUID.randomUUID();
        String name = "Test Unit";
        ZonedDateTime createdAt = ZonedDateTime.now();
        ZonedDateTime updatedAt = ZonedDateTime.now().plusHours(1);
        boolean active = true;

        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(id);
        unitDTO.setName(name);
        unitDTO.setCreatedAt(createdAt);
        unitDTO.setUpdatedAt(updatedAt);
        unitDTO.setActive(active);

        assertEquals(id, unitDTO.getId());
        assertEquals(name, unitDTO.getName());
        assertEquals(createdAt, unitDTO.getCreatedAt());
        assertEquals(updatedAt, unitDTO.getUpdatedAt());
        assertTrue(unitDTO.isActive());

        UUID newId = UUID.randomUUID();
        String newName = "Updated Unit";
        ZonedDateTime newCreatedAt = ZonedDateTime.now().minusDays(1);
        ZonedDateTime newUpdatedAt = ZonedDateTime.now().plusDays(1);
        boolean newActive = false;

        unitDTO.setId(newId);
        unitDTO.setName(newName);
        unitDTO.setCreatedAt(newCreatedAt);
        unitDTO.setUpdatedAt(newUpdatedAt);
        unitDTO.setActive(newActive);

        assertEquals(newId, unitDTO.getId());
        assertEquals(newName, unitDTO.getName());
        assertEquals(newCreatedAt, unitDTO.getCreatedAt());
        assertEquals(newUpdatedAt, unitDTO.getUpdatedAt());
        assertFalse(unitDTO.isActive());
    }
}
