package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Persona;


@RestController
@RequestMapping(value = "/persona/api/v1")
public class PersonaController {

   
    public static final String SERVICE_ONE_URL = "http://localhost:8080/persona";



    @Autowired
    RestTemplate restTemplate;


    @GetMapping
    public ResponseEntity<List<Persona>> getProducts() {

        ResponseEntity<List<Persona>> response = restTemplate.exchange(SERVICE_ONE_URL, HttpMethod.GET,
                HttpEntity.EMPTY, new ParameterizedTypeReference<List<Persona>>() {
                });

        return ResponseEntity.status(HttpStatus.SC_OK).body(response.getBody());
    }


    @PostMapping
    public ResponseEntity<Map> createProduct(@RequestBody Persona persona) {

 
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SERVICE_ONE_URL)
                .queryParam("id", persona.getId())
                .queryParam("nombre", persona.getNombre())
                .queryParam("apellido", persona.getApellido())
                .queryParam("edad", persona.getEdad())
                .queryParam("nacionalidad", persona.getNacionalidad());



        ResponseEntity<Map> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, HttpEntity.EMPTY,
                Map.class);

        return response;
    }

 
    @PutMapping
    public ResponseEntity<Persona> updateProduct(@RequestBody Persona persona) {

        HttpEntity<Persona> request = new HttpEntity<>(persona);
        ResponseEntity<Persona> response = restTemplate.exchange(SERVICE_ONE_URL, HttpMethod.PUT, request,
                Persona.class);
        return response;
    }

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Map> deleteProduct(@PathVariable("id") Long id) {

        ResponseEntity<Map> response = restTemplate.exchange((SERVICE_ONE_URL + "/" + id), HttpMethod.DELETE,
                HttpEntity.EMPTY, Map.class);
        return response;
    }
}