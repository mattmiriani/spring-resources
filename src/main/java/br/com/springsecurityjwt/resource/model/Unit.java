package br.com.springsecurityjwt.resource.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_unit")
public class Unit implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID_V1")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;


    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "network_id")
    private Network network;

    public Unit() {
    }

    public Unit(Unit unit, Network network) {
        this.id = UUID.randomUUID();
        this.name = unit.getName();
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        this.active = true;
        this.network = network;
    }
}
