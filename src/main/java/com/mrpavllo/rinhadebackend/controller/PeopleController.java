package com.mrpavllo.rinhadebackend.controller;

import com.mrpavllo.rinhadebackend.controller.dto.CreatePeopleDTO;
import com.mrpavllo.rinhadebackend.controller.dto.PeopleResponseDTO;
import com.mrpavllo.rinhadebackend.controller.mapper.PeopleResponseMapper;
import com.mrpavllo.rinhadebackend.service.PeopleService;
import com.mrpavllo.rinhadebackend.service.dto.PeopleDTO;
import com.mrpavllo.rinhadebackend.service.exception.FilterException;
import com.mrpavllo.rinhadebackend.service.exception.NotFoundException;
import com.mrpavllo.rinhadebackend.service.exception.SaveException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    private final PeopleResponseMapper peopleResponseMapper;

    @GetMapping( value = "/contagem-pessoas", produces = MediaType.TEXT_PLAIN_VALUE )
    public String getCount( ) {
        return peopleService.count( ).toString( );
    }

    @GetMapping( value = "/pessoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity< PeopleResponseDTO > getPeopleById( @PathVariable( "id" ) String id ) {
        try {
            return ResponseEntity.ok( peopleResponseMapper.toPeopleResponseDTO( peopleService.getById( id ) ) );
        } catch ( NotFoundException notFoundException ) {
            return ResponseEntity.notFound( ).build( );
        }
    }

    @GetMapping( value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity< List< PeopleResponseDTO > > getPeopleByFilter( @RequestParam( value = "t" ) String filter ) {
        try {
            return ResponseEntity.ok( peopleResponseMapper.toPeopleResponseDTOs( peopleService.getPeopleByFilter( filter ) ) );
        } catch ( FilterException filterException ) {
            return ResponseEntity.badRequest( ).build( );
        }
    }

    @PostMapping( value = "/pessoas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity< Void > postPeople( @RequestBody @Valid CreatePeopleDTO people ) {
        try {
            final String uuid = peopleService.createPeople( peopleResponseMapper.toPeopleDTO( people ) );

            return ResponseEntity.created( URI.create( String.format( "/pessoas/%s", uuid ) ) ).build( );

        } catch ( SaveException saveException ) {
            return ResponseEntity.badRequest( ).build( );
        }
    }

}
