package com.hotel.hotelapi.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Checkin {

    @Id
    @SequenceGenerator( name = "checkin_id_sequence", sequenceName = "checkin_id_seq", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "checkin_id_sequence" )
    private Long id;

    @OneToOne(targetEntity=Person.class, fetch=FetchType.EAGER)
    @JoinColumn(name="idPerson")
    private Person person;


    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Boolean haveVehicle;

    @Transient
    private BigDecimal totalPrice;

    public Checkin(Person person, LocalDateTime entryDate, LocalDateTime exitDate, Boolean haveVehicle) {
        this.person = person;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
        this.haveVehicle = haveVehicle;
    }

    public Checkin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }

    public LocalDateTime getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDateTime exitDate) {
        this.exitDate = exitDate;
    }

    public Boolean getHaveVehicle() {
        return haveVehicle;
    }

    public void setHaveVehicle(Boolean haveVehicle) {
        this.haveVehicle = haveVehicle;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
