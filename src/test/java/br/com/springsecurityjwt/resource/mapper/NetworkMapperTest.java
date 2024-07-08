package br.com.springsecurityjwt.resource.mapper;

import br.com.springsecurityjwt.resource.dto.NetworkDTO;
import br.com.springsecurityjwt.resource.dto.UnitDTO;
import br.com.springsecurityjwt.resource.dto.UserDTO;
import br.com.springsecurityjwt.resource.model.Network;
import br.com.springsecurityjwt.resource.model.TBUser;
import br.com.springsecurityjwt.resource.model.Unit;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkMapperTest {

    private final NetworkMapper mapper = Mappers.getMapper(NetworkMapper.class);

    @Test
    public void testToNetwork() {
        NetworkDTO networkDTO = new NetworkDTO();
        networkDTO.setName("Test Network");
        networkDTO.setCreatedAt(ZonedDateTime.now());
        networkDTO.setUpdatedAt(ZonedDateTime.now().plusHours(1));
        networkDTO.setExpiredAt(ZonedDateTime.now().plusDays(1));
        networkDTO.setActive(true);

        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(UUID.randomUUID());
        unitDTO.setName("Unit 1");
        networkDTO.setUnits(List.of(unitDTO));

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testuser");
        networkDTO.setUser(userDTO);

        Network network = mapper.toNetwork(networkDTO);

        assertEquals(networkDTO.getName(), network.getName());
        assertEquals(networkDTO.getCreatedAt(), network.getCreatedAt());
        assertEquals(networkDTO.getUpdatedAt(), network.getUpdatedAt());
        assertEquals(networkDTO.getExpiredAt(), network.getExpiredAt());
        assertEquals(networkDTO.isActive(), network.isActive());
        assertEquals(networkDTO.getUnits().size(), network.getUnits().size());
        assertEquals(networkDTO.getUnits().get(0).getId(), network.getUnits().get(0).getId());
        assertEquals(networkDTO.getUser().getUsername(), network.getUser().getUsername());
    }

    @Test
    public void testToNetworkDTO() {
        Network network = new Network();
        network.setName("Test Network");
        network.setCreatedAt(ZonedDateTime.now());
        network.setUpdatedAt(ZonedDateTime.now().plusHours(1));
        network.setExpiredAt(ZonedDateTime.now().plusDays(1));
        network.setActive(true);

        Unit unit = new Unit();
        unit.setId(UUID.randomUUID());
        unit.setName("Unit 1");
        network.setUnits(List.of(unit));

        TBUser user = new TBUser();
        user.setUsername("testuser");
        network.setUser(user);

        NetworkDTO networkDTO = mapper.toNetworkDTO(network);

        assertEquals(network.getName(), networkDTO.getName());
        assertEquals(network.getCreatedAt(), networkDTO.getCreatedAt());
        assertEquals(network.getUpdatedAt(), networkDTO.getUpdatedAt());
        assertEquals(network.getExpiredAt(), networkDTO.getExpiredAt());
        assertEquals(network.isActive(), networkDTO.isActive());
        assertEquals(network.getUnits().size(), networkDTO.getUnits().size());
        assertEquals(network.getUnits().get(0).getId(), networkDTO.getUnits().get(0).getId());
        assertEquals(network.getUser().getUsername(), networkDTO.getUser().getUsername());
    }
}

