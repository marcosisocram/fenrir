package com.mrpavllo.rinhadebackend.service;

import com.mrpavllo.rinhadebackend.repository.CacheRepository;
import com.mrpavllo.rinhadebackend.repository.PeopleRepository;
import com.mrpavllo.rinhadebackend.repository.domain.People;
import com.mrpavllo.rinhadebackend.service.dto.PeopleDTO;
import com.mrpavllo.rinhadebackend.service.exception.NotFoundException;
import com.mrpavllo.rinhadebackend.service.exception.SaveException;
import com.mrpavllo.rinhadebackend.service.mapper.PeopleDTOMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Service
@Slf4j
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final CacheRepository cacheRepository;

    private final PeopleDTOMapper peopleDTOMapper;

    public Long count( ) {
        return peopleRepository.count( );
    }

    public PeopleDTO getById( String id ) {

        List< PeopleDTO > cacheRepositoryByKey = cacheRepository.findByKey( id );
        if ( Objects.nonNull( cacheRepositoryByKey ) && ! cacheRepositoryByKey.isEmpty( ) ) {
            return cacheRepositoryByKey.get( 0 );
        }

        final People people = peopleRepository.findById( id ).orElseThrow( NotFoundException :: new );

        PeopleDTO peopleDTO = peopleDTOMapper.toPeopleDTO( people );

        if ( peopleDTO != null ) {
            cacheRepository.saveAll( id, List.of( peopleDTO ) );
        }

        return peopleDTO;
    }

    public List< PeopleDTO > getPeopleByFilter( String filter ) {

        List< PeopleDTO > cacheRepositoryByKey = cacheRepository.findByKey( filter );

        if ( Objects.nonNull( cacheRepositoryByKey ) ) {
            return cacheRepositoryByKey;
        }

        List< PeopleDTO > peopleDTOs = peopleDTOMapper.fromAggregaiontoPeopleDTOs( peopleRepository.findByFilter( filter ) );

        if ( peopleDTOs != null && ! peopleDTOs.isEmpty( ) ) {
            cacheRepository.saveAll( filter, peopleDTOs );
        }

        return peopleDTOs;
    }


    public String createPeople( PeopleDTO peopleDTO ) {

        List< PeopleDTO > cacheRepositoryByKey = cacheRepository.findByKey( peopleDTO.getApelido( ) );
        if ( Objects.nonNull( cacheRepositoryByKey ) ) {
            throw new SaveException( );
        }

        final String uuid = UUID.randomUUID( ).toString( );

        peopleDTO.setId( uuid );

        cacheRepository.saveAll( peopleDTO.getApelido( ), List.of( peopleDTO ) );

        cacheRepository.saveAll( peopleDTO.getId( ), List.of( peopleDTO ) );

        CompletableFuture.runAsync( ( ) -> peopleRepository.save( peopleDTOMapper.toPeople( peopleDTO ) ) );

        return uuid;
    }

}
