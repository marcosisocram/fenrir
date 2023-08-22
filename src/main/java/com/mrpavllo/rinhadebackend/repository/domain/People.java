package com.mrpavllo.rinhadebackend.repository.domain;

import com.mrpavllo.rinhadebackend.repository.aggregation.PeopleAggregation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@EqualsAndHashCode( callSuper = false )
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document( "people" )
@CompoundIndexes( {
        @CompoundIndex( name = "filtroRapido", def = "{'filtroRapido' : 1}" )
} )
public class People {

    @Id
    private String id;

    private String nome;

    private String apelido;
    private String nascimento;
    private List< String > stack;

    private String filtroRapido;
}
