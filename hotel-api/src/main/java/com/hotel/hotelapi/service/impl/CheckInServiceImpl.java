package com.hotel.hotelapi.service.impl;

import com.hotel.hotelapi.dao.CheckInDao;
import com.hotel.hotelapi.domain.Checkin;
import com.hotel.hotelapi.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private CheckInDao dao;

    private final static BigDecimal weekday = new BigDecimal(120);
    private final static BigDecimal weekend = new BigDecimal(150);
    private final static BigDecimal carWeekday = new BigDecimal(15);
    private final static BigDecimal carWeekend = new BigDecimal(20);
    private final static LocalTime limitTime = LocalTime.of(16,30,0,0);

    @Override
    public List<Checkin> findPersonOutOfHotel() {
        return calculatePrice(dao.findByExitDateLessThanEqual(LocalDateTime.now()));
    }

    @Override
    public List<Checkin> findPersonInHotel() {
        return calculatePrice(dao.findByExitDateGreaterThanEqual(LocalDateTime.now()));
    }

    @Override
    public Checkin save(Checkin checkin) {
        return dao.save(checkin);
    }

    @Override
    public Checkin update(Checkin checkin) {
        return dao.save(checkin);
    }

    @Override
    public void delete(Checkin checkin) {
        dao.delete(checkin);
    }


    private List<Checkin> calculatePrice(List<Checkin> checkinList){
        if(checkinList != null){
            for (Checkin checkin : checkinList) {
                LocalDateTime start = LocalDateTime.from(checkin.getEntryDate());
                List<LocalDateTime> totalDates = new ArrayList<>();
                while (!start.isAfter(checkin.getExitDate())) {
                    totalDates.add(start);
                    start = start.plusDays(1);
                }

                for (LocalDateTime day:totalDates) {
                    calculatePriceOfDay(checkin, day);
                }

                if(checkin.getExitDate().toLocalTime().isAfter(limitTime)){
                    calculatePriceOfDay(checkin, checkin.getExitDate());
                }

            }
        }

        return checkinList;
    }

    private void calculatePriceOfDay(Checkin checkin, LocalDateTime day) {
        if(day.getDayOfWeek().getValue() == DayOfWeek.SATURDAY.getValue()
                || day.getDayOfWeek().getValue() == DayOfWeek.SUNDAY.getValue()){


            if(checkin.getTotalPrice() == null){
                checkin.setTotalPrice(new BigDecimal(0l));
            }

            checkin.setTotalPrice(checkin.getTotalPrice().add(weekend));
            if(checkin.getHaveVehicle()){
                checkin.setTotalPrice(checkin.getTotalPrice().add(carWeekend));
            }
        }else{
            if(checkin.getTotalPrice() == null){
                checkin.setTotalPrice(new BigDecimal(0l));
            }

            checkin.setTotalPrice(checkin.getTotalPrice().add(weekday));
            if(checkin.getHaveVehicle()){
                checkin.setTotalPrice(checkin.getTotalPrice().add(carWeekday));
            }
        }
    }
}

