package com.mrpavllo.rinhadebackend.repository;

import com.mrpavllo.rinhadebackend.service.dto.PeopleDTO;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

import java.util.List;

public interface CacheRepository {

    void saveAll( String key, List< PeopleDTO > value );

    void save( String key, PeopleDTO value );

    List< PeopleDTO > findByKey( String key );

}
