package com.mrpavllo.rinhadebackend.repository.aggregation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PeopleAggregation {
    private String id;
    private String nome;
    private String apelido;
    private String nascimento;
    private List< String > stack;
}
