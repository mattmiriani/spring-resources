package br.com.springsecurityjwt.resource.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
public class UnitDTO {

    private UUID id;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean active;
}
