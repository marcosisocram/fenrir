package com.mrpavllo.rinhadebackend.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PeopleDTO implements Serializable {




    private String id;
    private String apelido;
    private String nome;
    private String nascimento;
    private List< String > stack;
}
