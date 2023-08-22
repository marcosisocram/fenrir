package com.mrpavllo.rinhadebackend.repository.impl;

import com.mrpavllo.rinhadebackend.repository.PeopleQueries;
import com.mrpavllo.rinhadebackend.repository.aggregation.PeopleAggregation;
import com.mrpavllo.rinhadebackend.repository.domain.People;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

@RequiredArgsConstructor
public class PeopleQueriesImpl implements PeopleQueries {


    private final MongoTemplate mongoTemplate;

    @Override
    public List< PeopleAggregation > findByFilter( String filter ) {

        final Criteria criteria = new Criteria( );

        criteria.orOperator(
                Criteria.where( "filtroRapido" )
                        .regex( String.format( ".*%s.*", filter ), "i" ) );

        final Aggregation aggregation = Aggregation.newAggregation( Aggregation.match( criteria ), Aggregation.limit( 50 ) );

        AggregationResults< PeopleAggregation > peopleAggregations = mongoTemplate.aggregate( aggregation, People.class, PeopleAggregation.class );

        return peopleAggregations.getMappedResults( );
    }
}
