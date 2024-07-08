package br.com.springsecurityjwt.resource.repository;

import br.com.springsecurityjwt.resource.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NetworkRepository extends JpaRepository<Network, UUID> {
}
