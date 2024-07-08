package br.com.springsecurityjwt.resource.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkTest {

    private Network network;

    @BeforeEach
    public void setup() {
        network = new Network();
    }

    @Test
    public void testAddUnit() {
        Unit unit = createUnit("Unit 1");

        network.addUnit(unit);

        assertEquals(1, network.getUnits().size());
        assertEquals(network, unit.getNetwork());
    }

    @Test
    public void testRemoveUnit() {
        Unit unit1 = createUnit("Unit 1");
        Unit unit2 = createUnit("Unit 2");

        network.addUnit(unit1);
        network.addUnit(unit2);

        network.removeUnit(unit1);

        assertEquals(unit1.isActive(), network.getUnits().get(0).isActive());
        assertEquals(unit2.isActive(), network.getUnits().get(1).isActive());
    }

    @Test
    public void testMergeForUpdate() {
        Network existingNetwork = new Network();
        existingNetwork.setName("Existing Network");

        network.setName("Updated Network");

        network.mergeForUpdate(existingNetwork);

        assertEquals("Existing Network", network.getName());
    }

    private Unit createUnit(String name) {
        Unit unit = new Unit();
        unit.setId(UUID.randomUUID());
        unit.setName(name);
        unit.setCreatedAt(ZonedDateTime.now());
        unit.setUpdatedAt(ZonedDateTime.now());
        unit.setActive(true);
        unit.setNetwork(network);
        return unit;
    }
}