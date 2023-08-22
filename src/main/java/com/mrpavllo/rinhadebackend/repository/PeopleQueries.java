package com.mrpavllo.rinhadebackend.repository;

import com.mrpavllo.rinhadebackend.repository.aggregation.PeopleAggregation;

import java.util.List;

public interface PeopleQueries {

    List< PeopleAggregation > findByFilter( String filter );

}
