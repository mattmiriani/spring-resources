package br.com.springsecurityjwt.resource.mapper;

import br.com.springsecurityjwt.resource.dto.UnitDTO;
import br.com.springsecurityjwt.resource.model.Unit;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UnitMapperTest {

    private final UnitMapper mapper = Mappers.getMapper(UnitMapper.class);

    @Test
    public void testToUnit() {
        UnitDTO unitDTO = new UnitDTO();
        UUID id = UUID.randomUUID();
        unitDTO.setId(id);
        unitDTO.setName("Test Unit");
        unitDTO.setCreatedAt(ZonedDateTime.now());
        unitDTO.setUpdatedAt(ZonedDateTime.now().plusHours(1));
        unitDTO.setActive(true);

        Unit unit = mapper.toUnit(unitDTO);

        assertEquals(unitDTO.getId(), unit.getId());
        assertEquals(unitDTO.getName(), unit.getName());
        assertEquals(unitDTO.getCreatedAt(), unit.getCreatedAt());
        assertEquals(unitDTO.getUpdatedAt(), unit.getUpdatedAt());
        assertTrue(unit.isActive());
    }

    @Test
    public void testToUnitDTO() {
        Unit unit = new Unit();
        unit.setId(UUID.randomUUID());
        unit.setName("Test Unit");
        unit.setCreatedAt(ZonedDateTime.now());
        unit.setUpdatedAt(ZonedDateTime.now().plusHours(1));
        unit.setActive(true);

        UnitDTO unitDTO = mapper.toUnitDTO(unit);

        assertNull(unitDTO.getId());
        assertEquals(unit.getName(), unitDTO.getName());
        assertEquals(unit.getCreatedAt(), unitDTO.getCreatedAt());
        assertEquals(unit.getUpdatedAt(), unitDTO.getUpdatedAt());
        assertTrue(unitDTO.isActive());
    }
}

