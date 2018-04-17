package com.hotel.hotelapi.service;


import com.hotel.hotelapi.domain.Checkin;

import java.util.List;

public interface CheckInService {


    List<Checkin> findPersonOutOfHotel();

    List<Checkin> findPersonInHotel();

    Checkin save(Checkin checkin);

    Checkin update(Checkin checkin);

    void delete(Checkin checkin);


}

