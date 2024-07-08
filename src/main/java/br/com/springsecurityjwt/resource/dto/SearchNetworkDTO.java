package br.com.springsecurityjwt.resource.dto;

import br.com.springsecurityjwt.resource.model.Unit;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Setter
public class SearchNetworkDTO {

    private String search;
    @Getter
    private List<Unit> units;
    private Boolean active;
    @Getter
    private PageableDTO pageableDTO;

    public Optional<String> getSearch() {
        return ofNullable(search);
    }

    public Optional<Boolean> getActive() {
        return ofNullable(active);
    }
}
