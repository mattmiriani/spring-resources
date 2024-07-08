package br.com.springsecurityjwt.resource.mapper;

import br.com.springsecurityjwt.resource.dto.NetworkDTO;
import br.com.springsecurityjwt.resource.model.Network;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NetworkMapper {

    NetworkMapper INSTANCE = Mappers.getMapper(NetworkMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "units", target = "units"),
            @Mapping(source = "user", target = "user")
    })
    Network toNetwork(NetworkDTO networkDTO);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    @Mapping(source = "expiredAt", target = "expiredAt")
    @Mapping(source = "active", target = "active")
    @Mapping(source = "units", target = "units")
    NetworkDTO toNetworkDTO(Network network);
}
