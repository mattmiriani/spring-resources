package br.com.springsecurityjwt.resource.controller;

import br.com.springsecurityjwt.resource.dto.NetworkDTO;
import br.com.springsecurityjwt.resource.dto.SearchNetworkDTO;
import br.com.springsecurityjwt.resource.dto.UnitDTO;
import br.com.springsecurityjwt.resource.mapper.NetworkMapper;
import br.com.springsecurityjwt.resource.mapper.UnitMapper;
import br.com.springsecurityjwt.resource.service.NetworkService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static java.util.Optional.of;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/networks")
public class NetworkController {

    private final NetworkService networkService;
    private final NetworkMapper networkMapper;
    private final UnitMapper unitMapper;

    @GetMapping
    public ResponseEntity<Page<NetworkDTO>> findAllNetworks(@RequestBody SearchNetworkDTO search) {
        return ResponseEntity.ok(of(networkService.findAll(search).map(networkMapper::toNetworkDTO)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("Network not found"))
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NetworkDTO> findNetworkById(@PathVariable("id") UUID networkId) {
        return ResponseEntity.ok(networkMapper.toNetworkDTO(networkService.findById(networkId)));
    }

    @PostMapping
    public ResponseEntity<NetworkDTO> createNetwork(@RequestBody NetworkDTO networkDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(networkMapper.toNetworkDTO(networkService.create(networkMapper.toNetwork(networkDTO))));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NetworkDTO> updateNetwork(@PathVariable("id") UUID networkId,
                                                    @RequestBody NetworkDTO networkDTO) {
        return ResponseEntity.ok(networkMapper.toNetworkDTO(networkService.update(networkId, networkMapper.toNetwork(networkDTO))));
    }

    @PatchMapping("/add-unit/{id}")
    public ResponseEntity<NetworkDTO> addUnit(@PathVariable("id") UUID networkId,
                                              @RequestBody UnitDTO unitDTO) {
        return ResponseEntity.ok(networkMapper.toNetworkDTO(networkService.addUnit(networkId, unitMapper.toUnit(unitDTO))));
    }

    @DeleteMapping("/remove-unit/{id}")
    public ResponseEntity<NetworkDTO> removeUnit(@PathVariable("id") UUID networkId,
                                                 @RequestBody UnitDTO unitDTO) {
        return ResponseEntity.ok(networkMapper.toNetworkDTO(networkService.removeUnit(networkId, unitMapper.toUnit(unitDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNetwork(@PathVariable("id") UUID networkId) {
        networkService.delete(networkId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
