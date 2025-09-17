package com.devsu.client.mapper;
import com.devsu.client.DTO.ClientRequestDto;
import com.devsu.client.DTO.ClientResponseDto;
import com.devsu.client.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {Client.class})
public interface ClientMapper {

     ClientResponseDto toClientDto(Client client);
     List<ClientResponseDto> toClientDtoList(List<Client> clients);
    void updateClientFromDto(ClientRequestDto dto, @MappingTarget Client entity);
}
