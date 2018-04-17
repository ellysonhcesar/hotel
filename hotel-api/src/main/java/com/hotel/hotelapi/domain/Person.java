package com.hotel.hotelapi.domain;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @SequenceGenerator( name = "person_id_sequence", sequenceName = "person_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "person_id_sequence" )
    private Long id;

    private String name;

    private String document;

    private String fone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person(String name, String document, String fone) {
        this.name = name;
        this.document = document;
        this.fone = fone;
    }

    public Person() {

    }
}
