package com.hotel.hotelapi.service.impl;

import com.hotel.hotelapi.dao.PersonDao;
import com.hotel.hotelapi.domain.Person;
import com.hotel.hotelapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao dao;


    @Override
    public List<Person> findAll() {
        return dao.selectAll();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public Person save(Person person) {
        return dao.save(person);
    }

    @Override
    public Person update(Person person) {
        return dao.save(person);
    }

    @Override
    public void delete(Person person) {
        dao.delete(person);
    }

    @Override
    public List<Person> findByName(String name) {
        return dao.findByNameLikeIgnoreCase("%"+name+"%");
    }

    @Override
    public List<Person> findByDocument(String document) {
        return dao.findByDocumentLikeIgnoreCase("%"+document+"%");
    }

    @Override
    public List<Person> findByFone(String fone) {
        return dao.findByFoneLikeIgnoreCase("%"+fone+"%");
    }
}

