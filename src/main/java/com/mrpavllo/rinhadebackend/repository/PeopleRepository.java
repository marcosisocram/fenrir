package com.mrpavllo.rinhadebackend.repository;

import com.mrpavllo.rinhadebackend.repository.aggregation.PeopleAggregation;
import com.mrpavllo.rinhadebackend.repository.domain.People;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends MongoRepository< People, String >, PeopleQueries {

    List< People > findByIdIn( List<String> ids );

}
