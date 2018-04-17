package com.hotel.hotelapi.dao;

import com.hotel.hotelapi.domain.Checkin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckInDao extends CrudRepository<Checkin,Long>{

    //person is out of hotel
    List<Checkin> findByExitDateLessThanEqual(LocalDateTime dateExit);

    @Query("SELECT a FROM Checkin a WHERE a.entryDate <= :now and a.exitDate >= :now")
    List<Checkin> listPersonInHotel(LocalDateTime now);

}

