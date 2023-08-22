package com.mrpavllo.rinhadebackend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class JsonUtils {

    public static String objectToJson( Object object ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString( object );
        } catch( JsonProcessingException e ) {
            return null;
        }
    }

    public static < M > List< M > jsonToList( String json, Class< M > clazz ) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue( json, mapper.getTypeFactory().constructCollectionType( List.class, clazz ) );
        } catch( IOException var4 ) {
            return Collections.emptyList();
        }
    }
}
