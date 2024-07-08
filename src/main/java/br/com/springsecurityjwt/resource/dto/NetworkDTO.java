package br.com.springsecurityjwt.resource.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class NetworkDTO {
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private ZonedDateTime expiredAt;
    private boolean active;
    private List<UnitDTO> units;
    private UserDTO user;
}
