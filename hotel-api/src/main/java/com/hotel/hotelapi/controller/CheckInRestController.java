package com.hotel.hotelapi.controller;


import com.hotel.hotelapi.domain.Checkin;
import com.hotel.hotelapi.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CheckInRestController {

    @Autowired
    private CheckInService checkInService;


    @RequestMapping(value = "/api/checkIn", method = RequestMethod.POST)
    public Checkin create(@RequestBody Checkin checkin) {
        return checkInService.save(checkin);
    }

    @RequestMapping(value = "/api/checkIn", method = RequestMethod.PUT)
    public Checkin update(@RequestBody Checkin checkin) {
        return checkInService.update(checkin);
    }

    @RequestMapping(value = "/api/checkIn", method = RequestMethod.DELETE)
    public void delete(@RequestBody Checkin checkin) {
        checkInService.delete(checkin);
    }

    @RequestMapping(value = "/api/checkIn/in", method = RequestMethod.GET)
    public List<Checkin> findPersonInHotel() {
        return checkInService.findPersonInHotel();
    }

    @RequestMapping(value = "/api/checkIn/out", method = RequestMethod.GET)
    public List<Checkin> findPersonOutOfHotel() {
        return checkInService.findPersonOutOfHotel();
    }

}
