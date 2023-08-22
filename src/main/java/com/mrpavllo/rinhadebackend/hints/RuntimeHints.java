package com.mrpavllo.rinhadebackend.hints;

import com.mrpavllo.rinhadebackend.controller.dto.PeopleResponseDTO;
import com.mrpavllo.rinhadebackend.service.dto.PeopleDTO;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

@Configuration
@ImportRuntimeHints( RuntimeHints.class )
public class RuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints( org.springframework.aot.hint.RuntimeHints hints, ClassLoader classLoader ) {
        hints.serialization( ).registerType( PeopleDTO.class );
        hints.serialization( ).registerType( PeopleResponseDTO.class );
    }
}
