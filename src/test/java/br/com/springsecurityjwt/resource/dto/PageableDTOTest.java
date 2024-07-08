package br.com.springsecurityjwt.resource.dto;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageableDTOTest {

    @Test
    public void testDefaultValues() {
        PageableDTO pageableDTO = new PageableDTO();

        assertEquals(Optional.of(0), pageableDTO.getPageNumber());
        assertEquals(Optional.of(20), pageableDTO.getPageSize());
        assertEquals(Optional.empty(), pageableDTO.getSortField());
        assertEquals(Optional.of(Sort.Direction.ASC), pageableDTO.getSortDir());
    }

    @Test
    public void testCustomValues() {
        PageableDTO pageableDTO = new PageableDTO();
        pageableDTO.setPageNumber(2);
        pageableDTO.setPageSize(10);
        pageableDTO.setSortField("name");
        pageableDTO.setSortDir("DESC");

        assertEquals(Optional.of(2), pageableDTO.getPageNumber());
        assertEquals(Optional.of(10), pageableDTO.getPageSize());
        assertEquals(Optional.of("name"), pageableDTO.getSortField());
        assertEquals(Optional.of(Sort.Direction.DESC), pageableDTO.getSortDir());

        Sort sort = pageableDTO.getSort();
        assertTrue(sort.isSorted());
        assertEquals(Sort.Direction.DESC, sort.getOrderFor("name").getDirection());

        Pageable pageable = pageableDTO.getPageable();
        assertEquals(PageRequest.of(2, 10, sort), pageable);
    }

    @Test
    public void testNullSortDir() {
        PageableDTO pageableDTO = new PageableDTO();
        pageableDTO.setSortDir(null);

        assertEquals(Optional.empty(), pageableDTO.getSortDir());
        assertEquals(Sort.unsorted(), pageableDTO.getSort());
    }

    @Test
    public void testNullSortField() {
        PageableDTO pageableDTO = new PageableDTO();
        pageableDTO.setSortField(null);

        assertEquals(Optional.empty(), pageableDTO.getSortField());
        assertEquals(Sort.unsorted(), pageableDTO.getSort());
    }
}