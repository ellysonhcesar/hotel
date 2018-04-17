package com.hotel.hotelapi.dao;

import com.hotel.hotelapi.domain.Checkin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckInDao extends CrudRepository<Checkin,Long>{

    //person is out of hotel
    List<Checkin> findByExitDateLessThanEqual(LocalDateTime dateExit);

    //person is in hotel
    List<Checkin> findByExitDateGreaterThanEqual(LocalDateTime dateExit);

}

