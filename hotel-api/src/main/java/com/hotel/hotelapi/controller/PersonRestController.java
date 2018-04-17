package com.hotel.hotelapi.controller;


import com.hotel.hotelapi.domain.Person;
import com.hotel.hotelapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET)
    public Optional<Person> findPersonById(@PathVariable("id") Long id) {
        return personService.findById(id);
    }

    @RequestMapping(value = "/api/person", method = RequestMethod.GET)
    public List<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping(value = "/api/person", method = RequestMethod.POST)
    public Person create(@RequestBody Person person) {
        return personService.save(person);
    }

    @RequestMapping(value = "/api/person", method = RequestMethod.PUT)
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @RequestMapping(value = "/api/person", method = RequestMethod.DELETE)
    public void delete(@RequestBody Person person) {
        personService.delete(person);
    }

    @RequestMapping(value = "/api/list/person/name/{name}", method = RequestMethod.GET)
    public List<Person> findByName(@PathVariable("name") String name) {
        return personService.findByName(name);
    }

    @RequestMapping(value = "/api/list/person/document/{document}", method = RequestMethod.GET)
    public List<Person> findByDocument(@PathVariable("document") String document) {
        return personService.findByDocument(document);
    }

    @RequestMapping(value = "/api/list/person/fone/{fone}", method = RequestMethod.GET)
    public List<Person> findByFone(@PathVariable("fone") String fone) {
        return personService.findByFone(fone);
    }
}
