package com.mrpavllo.rinhadebackend.repository.impl;

import com.mrpavllo.rinhadebackend.repository.CacheRepository;
import com.mrpavllo.rinhadebackend.service.dto.PeopleDTO;
import com.mrpavllo.rinhadebackend.utils.JsonUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.mrpavllo.rinhadebackend.constants.Constants.CACHE_NAME;

@Repository
@RequiredArgsConstructor
public class CacheRepositoryImpl implements CacheRepository {

    private final CacheManager cacheManager;

    @Override
    public void save( String key, @NotNull PeopleDTO value ) {
        Objects.requireNonNull( cacheManager.getCache( CACHE_NAME ) ).putIfAbsent( key, JsonUtils.objectToJson( value ) );
    }

    @Override
    public List< PeopleDTO > findByKey( String key ) {
        return Optional
                .ofNullable( Objects.requireNonNull( cacheManager.getCache( CACHE_NAME ) ).get( key, String.class ) )
                .map( s -> JsonUtils.jsonToList( s, PeopleDTO.class ) )
                .orElse( null );
    }

    @Override
    public void saveAll( String key, List< PeopleDTO > value ) {
        Objects.requireNonNull( cacheManager.getCache( CACHE_NAME ) ).putIfAbsent( key, JsonUtils.objectToJson( value ) );
    }


}
