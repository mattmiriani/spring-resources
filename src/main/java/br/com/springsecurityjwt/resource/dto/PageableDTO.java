package br.com.springsecurityjwt.resource.dto;

import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static java.util.Objects.isNull;

public class PageableDTO {

    @Setter
    private Integer pageNumber = 0;
    @Setter
    private Integer pageSize = 20;
    @Setter
    private String sortField;
    private Sort.Direction sortDir = Sort.Direction.ASC;

    public Optional<Integer> getPageNumber() {
        return Optional.ofNullable(pageNumber);
    }

    public Optional<Integer> getPageSize() {
        return Optional.ofNullable(pageSize);
    }

    public Optional<String> getSortField() {
        return Optional.ofNullable(sortField);
    }

    public Optional<Sort.Direction> getSortDir() {
        return Optional.ofNullable(this.sortDir);
    }

    public void setSortDir(String sortDir) {
        this.sortDir = isNull(sortDir) ? null : Sort.Direction.fromString(sortDir);
    }

    public Sort getSort() {
        if (getSortDir().isPresent() && getSortField().isPresent()) {
            return Sort.by(getSortDir().get(), getSortField().get());
        }

        return Sort.unsorted();
    }

    public Pageable getPageable() {
        return PageRequest.of(pageNumber, pageSize, getSort());
    }
}
