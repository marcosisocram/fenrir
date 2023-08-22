package com.mrpavllo.rinhadebackend.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePeopleDTO {

    @NotBlank
    @Size( min = 1, max = 32, message = "apelido com maximo 32 caracteres" )
    private String apelido;

    @NotBlank
    @Size( min = 1, max = 100, message = "nome com maximo 100 caracteres" )
    private String nome;

    @NotBlank
    @Pattern( regexp = "^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$", message = "Data invalida, formato correto 'AAAA-MM-DD'" )
    private String nascimento;

    private List< String > stack;

}
