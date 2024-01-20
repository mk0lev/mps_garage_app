package com.example.mps.model.mappers;

import com.example.mps.model.Booking;
import com.example.mps.model.DTO.BookingDTO;
import com.example.mps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BookingDTOMapper {

    private final UserService userService;

    @Autowired
    public BookingDTOMapper(UserService userService) {
        this.userService = userService;
    }

    public Booking mapToBooking(BookingDTO bookingDTO){

        Booking booking = new Booking();
        booking.setDate(bookingDTO.getDate());
        booking.setClient(userService.getUser(bookingDTO.getClientName()));
        booking.setWorkshop(userService.getWorkshopByName(bookingDTO.getWorkshopName()));
        booking.setVehicle(userService.getVehicleById(bookingDTO.getVehicleId()));
        return booking;

    }
}
