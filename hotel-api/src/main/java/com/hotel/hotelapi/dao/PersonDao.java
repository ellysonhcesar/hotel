package com.hotel.hotelapi.dao;

import com.hotel.hotelapi.domain.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends CrudRepository<Person, Long> {

    @Query("SELECT a FROM Person a ")
    List<Person> selectAll();

    List<Person> findByNameLikeIgnoreCase(String name);

    List<Person> findByDocumentLikeIgnoreCase(String document);

    List<Person> findByFoneLikeIgnoreCase(String fone);

}

