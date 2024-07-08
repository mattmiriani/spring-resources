package br.com.springsecurityjwt.resource.mapper;

import br.com.springsecurityjwt.resource.dto.UnitDTO;
import br.com.springsecurityjwt.resource.model.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UnitMapper {

    UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

    Unit toUnit(UnitDTO unitDTO);

    @Mapping(target = "id", ignore = true)
    UnitDTO toUnitDTO(Unit unit);
}
