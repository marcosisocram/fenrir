package com.mrpavllo.rinhadebackend.service.mapper;


import com.mrpavllo.rinhadebackend.repository.aggregation.PeopleAggregation;
import com.mrpavllo.rinhadebackend.repository.domain.People;
import com.mrpavllo.rinhadebackend.service.dto.PeopleDTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PeopleDTOMapper {

    public PeopleDTO toPeopleDTO( People people ) {
        return PeopleDTO.builder( )
                .id( people.getId( ) )
                .nome( people.getNome( ) )
                .apelido( people.getApelido( ) )
                .nascimento( people.getNascimento( ) )
                .stack( people.getStack( ) )
                .build( );
    }

    public People toPeople( PeopleDTO peopleDTO ) {

        return People.builder( )
                .id( peopleDTO.getId( ) )
                .nascimento( peopleDTO.getNascimento( ) )
                .apelido( peopleDTO.getApelido( ) )
                .nome( peopleDTO.getNome( ) )
                .stack( peopleDTO.getStack( ) )
                .filtroRapido( peopleDTO.getNome( ) + ";" + peopleDTO.getApelido( ) + ";" + String.join( ";", Optional.ofNullable( peopleDTO.getStack( ) ).orElse( Collections.emptyList( ) ) ) )
                .build( );
    }

    public List< PeopleDTO > fromAggregaiontoPeopleDTOs( List< PeopleAggregation > peopleList ) {
        return peopleList.stream( )
                .map( this :: toPeopleDTO )
                .collect( Collectors.toList( ) );
    }

    public List< PeopleDTO > toPeopleDTOs( List< People > peopleList ) {
        return peopleList.stream( )
                .map( this :: toPeopleDTO )
                .collect( Collectors.toList( ) );
    }

    public PeopleDTO toPeopleDTO( PeopleAggregation people ) {
        return PeopleDTO.builder( )
                .id( people.getId( ) )
                .nome( people.getNome( ) )
                .apelido( people.getApelido( ) )
                .nascimento( people.getNascimento( ) )
                .stack( people.getStack( ) )
                .build( );
    }
}
