package br.com.springsecurityjwt.resource.service;

import br.com.springsecurityjwt.resource.dto.SearchNetworkDTO;
import br.com.springsecurityjwt.resource.model.Network;
import br.com.springsecurityjwt.resource.model.TBUser;
import br.com.springsecurityjwt.resource.model.Unit;
import br.com.springsecurityjwt.resource.repository.NetworkCustomRepository;
import br.com.springsecurityjwt.resource.repository.NetworkRepository;
import br.com.springsecurityjwt.resource.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

class NetworkServiceTest {
    private final UUID networkRandomId = UUID.randomUUID();
    private final UUID unitRandomId = UUID.randomUUID();

    @InjectMocks
    private NetworkService networkService;

    @Mock
    private NetworkCustomRepository networkCustomRepository;
    @Mock
    private NetworkRepository networkRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class FindAll {

        @Test
        void findAllIsNotEmpty() {
            var search = new SearchNetworkDTO();
            var expectedPage = new PageImpl<>(List.of(new Network()));

            when(networkCustomRepository.find(search)).thenReturn(expectedPage);

            var result = networkService.findAll(search);

            Assertions.assertEquals(expectedPage, result);
        }

        @Test
        void findAllIsEmpty() {
            var search = new SearchNetworkDTO();
            var expectedPage = new PageImpl<Network>(List.of());

            when(networkCustomRepository.find(search)).thenReturn(expectedPage);

            var result = networkService.findAll(search);

            Assertions.assertEquals(expectedPage, result);
        }
    }

    @Nested
    class FindById {

        @Test
        void findById() {
            var networkId = networkRandomId;

            var network = new Network();
            network.setId(networkRandomId);
            network.setActive(true);

            when(networkRepository.findById(networkId)).thenReturn(Optional.of(network));

            Network result = networkService.findById(networkId);

            Assertions.assertEquals(network, result);
        }

        @Test
        void findByIdThrows() {
            when(networkRepository.findById(networkRandomId))
                    .thenReturn(Optional.empty());
            assertThrows(ResponseStatusException.class, () -> networkService.findById(networkRandomId));

            verify(networkRepository, times(1)).findById(networkRandomId);
        }
    }

    @Nested
    class Create {

        @Test
        void create() {
            var newNetwork = new Network();
            newNetwork.setId(networkRandomId);
            newNetwork.setName("name teste");

            var user = new TBUser();
            String userId = "username";
            user.setUsername(userId);

            newNetwork.setUser(user);

            when(userRepository.findById(userId))
                    .thenReturn(Optional.of(user));
            when(networkRepository.save(any(Network.class)))
                    .thenReturn(newNetwork);

            var result = networkService.create(newNetwork);

            Assertions.assertEquals(networkRandomId, result.getId());
            Assertions.assertEquals("name teste", result.getName());
        }
    }

    @Nested
    class Update {

        @Test
        void update() {
            var newNetwork = new Network();
            newNetwork.setId(networkRandomId);
            newNetwork.setName("name teste");
            newNetwork.setActive(true);

            when(networkRepository.save(any(Network.class)))
                    .thenReturn(newNetwork);
            when(networkRepository.findById(networkRandomId))
                    .thenReturn(Optional.of(newNetwork));

            newNetwork.setName("name teste 2");

            var result = networkService.update(networkRandomId, newNetwork);

            Assertions.assertEquals(networkRandomId, result.getId());
            Assertions.assertEquals("name teste 2", result.getName());
        }
    }

    @Nested
    class AddUnit {

        @Test
        void addUnit() {
            var newNetwork = new Network();
            newNetwork.setId(networkRandomId);
            newNetwork.setName("network teste");
            newNetwork.setActive(true);

            var newUnit = new Unit();
            newUnit.setId(unitRandomId);
            newUnit.setName("unit teste");
            newUnit.setActive(true);

            when(networkRepository.findById(networkRandomId))
                    .thenReturn(Optional.of(newNetwork));
            when(networkRepository.save(any(Network.class)))
                    .thenReturn(newNetwork);

            var result = networkService.addUnit(networkRandomId, newUnit);

            Assertions.assertEquals(networkRandomId, result.getId());
            Assertions.assertEquals("network teste", result.getName());
            Assertions.assertEquals(1, result.getUnits().size());
        }
    }

    @Nested
    class RemoveUnit {

        @Test
        void removeUnit() {
            var newNetwork = new Network();
            newNetwork.setId(networkRandomId);
            newNetwork.setName("network teste");
            newNetwork.setActive(true);

            var newUnit = new Unit();
            newUnit.setId(unitRandomId);
            newUnit.setName("unit teste");
            newUnit.setActive(true);

            newNetwork.addUnits(List.of(newUnit));

            when(networkRepository.findById(networkRandomId))
                    .thenReturn(Optional.of(newNetwork));
            when(networkRepository.save(any(Network.class)))
                    .thenReturn(newNetwork);

            var result = networkService.removeUnit(networkRandomId, newUnit);

            Assertions.assertEquals(networkRandomId, result.getId());
            Assertions.assertEquals("network teste", result.getName());
            Assertions.assertEquals(1, result.getUnits().size());
        }
    }

    @Nested
    class Delete {

        @Test
        void delete() {
            var newNetwork = new Network();
            newNetwork.setId(networkRandomId);
            newNetwork.setName("network teste");
            newNetwork.setActive(true);

            var newUnit = new Unit();
            newUnit.setId(unitRandomId);
            newUnit.setName("unit teste");
            newUnit.setActive(true);

            newNetwork.addUnits(List.of(newUnit));

            when(networkRepository.findById(networkRandomId))
                    .thenReturn(Optional.of(newNetwork));
            when(networkRepository.save(any(Network.class)))
                    .thenReturn(newNetwork);

            networkService.delete(networkRandomId);

            verify(networkRepository, times(0)).deleteById(networkRandomId);
            Assertions.assertFalse(newNetwork.isActive());
            Assertions.assertFalse(newNetwork.getUnits().get(0).isActive());
        }
    }
}