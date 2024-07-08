package br.com.springsecurityjwt.resource.service;

import br.com.springsecurityjwt.resource.dto.SearchNetworkDTO;
import br.com.springsecurityjwt.resource.model.Network;
import br.com.springsecurityjwt.resource.model.Unit;
import br.com.springsecurityjwt.resource.repository.NetworkCustomRepository;
import br.com.springsecurityjwt.resource.repository.NetworkRepository;
import br.com.springsecurityjwt.resource.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Lazy))
public class NetworkService {

    private static final Logger log = LoggerFactory.getLogger(NetworkService.class);
    private final NetworkCustomRepository networkCustomRepository;
    private final NetworkRepository networkRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<Network> findAll(SearchNetworkDTO search) {
        try {
            return networkCustomRepository.find(search);
        } catch (Exception e) {
            log.error("Error finding Networks", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public Network findById(UUID networkId) {
        return networkRepository.findById(networkId).filter(Network::isActive).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Network not found")
        );
    }

    private Network save(Network network) {
        log.info("Saving Network: {}", network);

        network.setUpdatedAt(ZonedDateTime.now());

        try {
            return networkRepository.save(network);
        } catch (Exception e) {
            log.error("Error saving Network: {}", network, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Network create(Network network) {
        var user = userRepository.findById(network.getUser().getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );

        network.setUser(user);

        return save(new Network(network));
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Network update(UUID networkId, Network network) {
        var networkToUpdate = this.findById(networkId);

        networkToUpdate.mergeForUpdate(network);

        return save(networkToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Network addUnit(UUID networkId, Unit unit) {
        var network = this.findById(networkId);

        network.addUnit(unit);

        return this.save(network);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Network removeUnit(UUID networkId, Unit unit) {
        var network = this.findById(networkId);

        network.removeUnit(unit);

        return this.save(network);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(UUID networkId) {
        var network = this.findById(networkId);

        network.setActive(false);
        network.getUnits().forEach(u -> u.setActive(false));
        network.setExpiredAt(ZonedDateTime.now());

        this.save(network);
    }
}
