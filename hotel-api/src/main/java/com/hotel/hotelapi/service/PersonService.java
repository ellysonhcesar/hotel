package com.hotel.hotelapi.service;


import com.hotel.hotelapi.domain.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> findById(Long id);

    Person save(Person person);

    Person update(Person person);

    void delete(Person person);

    List<Person> findByName(String name);

    List<Person> findByDocument(String document);

    List<Person> findByFone(String fone);
}

