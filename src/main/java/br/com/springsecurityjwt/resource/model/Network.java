package br.com.springsecurityjwt.resource.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@ToString(exclude = "units")
@Entity(name = "tb_network")
public class Network implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Column(name = "expired_at")
    private ZonedDateTime expiredAt;

    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private TBUser user;

    @OneToMany(
            mappedBy = "network", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true
    )
    private List<Unit> units = new ArrayList<>();

    public Network() {
    }

    public Network(Network network) {
        this.id = UUID.randomUUID();
        this.name = network.getName();
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        this.expiredAt = network.getExpiredAt();
        this.active = true;
        this.user = network.getUser();
        this.units = this.addUnits(network.getUnits());
    }

    public List<Unit> addUnits(List<Unit> units) {
        this.units = units.stream()
                .map(unit -> new Unit(unit, this))
                .collect(Collectors.toList());

        return this.units;
    }

    public void addUnit(Unit unit) {
        this.units.add(new Unit(unit, this));
    }

    public void removeUnit(Unit unit) {
        this.units.removeIf(u -> u.getId().equals(unit.getId()));
    }

    public void mergeForUpdate(Network network) {
        this.name = network.getName();
    }
}
