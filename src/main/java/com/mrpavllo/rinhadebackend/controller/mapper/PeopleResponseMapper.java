package com.mrpavllo.rinhadebackend.controller.mapper;

import com.mrpavllo.rinhadebackend.controller.dto.CreatePeopleDTO;
import com.mrpavllo.rinhadebackend.controller.dto.PeopleResponseDTO;
import com.mrpavllo.rinhadebackend.service.dto.PeopleDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PeopleResponseMapper {

    public List< PeopleResponseDTO > toPeopleResponseDTOs( List< PeopleDTO > peopleDTOList ) {
        return peopleDTOList.stream( )
                .map( this :: toPeopleResponseDTO )
                .collect( Collectors.toList( ) );
    }

    public PeopleResponseDTO toPeopleResponseDTO( PeopleDTO peopleDTO ) {
        return PeopleResponseDTO.builder( )
                .id( peopleDTO.getId( ) )
                .stack( peopleDTO.getStack( ) )
                .nome( peopleDTO.getNome( ) )
                .apelido( peopleDTO.getApelido( ) )
                .nascimento( peopleDTO.getNascimento( ) )
                .build( );
    }

    public PeopleDTO toPeopleDTO( CreatePeopleDTO peopleDTO ) {
        return PeopleDTO.builder( )
                .apelido( peopleDTO.getApelido( ) )
                .nome( peopleDTO.getNome( ) )
                .stack( peopleDTO.getStack( ) )
                .nascimento( peopleDTO.getNascimento( ) )
                .build( );
    }
}
