package br.com.springsecurityjwt.resource.dto;

import br.com.springsecurityjwt.resource.model.Unit;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SearchNetworkDTOTest {

    @Test
    public void testDefaultValues() {
        SearchNetworkDTO searchNetworkDTO = new SearchNetworkDTO();

        assertEquals(Optional.empty(), searchNetworkDTO.getSearch());
        assertEquals(Optional.empty(), searchNetworkDTO.getActive());
        assertNull(searchNetworkDTO.getUnits());
        assertNull(searchNetworkDTO.getPageableDTO());
    }

    @Test
    public void testCustomValues() {
        SearchNetworkDTO searchNetworkDTO = new SearchNetworkDTO();
        searchNetworkDTO.setSearch("Test Search");
        searchNetworkDTO.setActive(true);

        Unit unit1 = new Unit();
        Unit unit2 = new Unit();
        List<Unit> units = List.of(unit1, unit2);
        searchNetworkDTO.setUnits(units);

        PageableDTO pageableDTO = new PageableDTO();
        searchNetworkDTO.setPageableDTO(pageableDTO);

        assertEquals(Optional.of("Test Search"), searchNetworkDTO.getSearch());
        assertEquals(Optional.of(true), searchNetworkDTO.getActive());
        assertEquals(units, searchNetworkDTO.getUnits());
        assertEquals(pageableDTO, searchNetworkDTO.getPageableDTO());
    }

    @Test
    public void testNullValues() {
        SearchNetworkDTO searchNetworkDTO = new SearchNetworkDTO();
        searchNetworkDTO.setSearch(null);
        searchNetworkDTO.setActive(null);

        assertEquals(Optional.empty(), searchNetworkDTO.getSearch());
        assertEquals(Optional.empty(), searchNetworkDTO.getActive());
    }
}
