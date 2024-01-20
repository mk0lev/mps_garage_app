package com.example.mps.controllers.TLcontrollers;

import com.example.mps.model.Booking;
import com.example.mps.model.DTO.BookingDTO;
import com.example.mps.model.mappers.BookingDTOMapper;
import com.example.mps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/client/booking")
public class BookingController {
    private final UserService userService;
    private final BookingDTOMapper bookingDTOMapper;

    @Autowired
    public BookingController(UserService userService, BookingDTOMapper bookingDTOMapper) {
        this.userService = userService;
        this.bookingDTOMapper = bookingDTOMapper;
    }

    @GetMapping
    public String showBookingPage(Model model, Principal principal){
        model.addAttribute(new BookingDTO());
        model.addAttribute(userService.getAllWorkshops());
        model.addAttribute(userService.getAllUserCars(principal.getName()));
        return "booking";
    }

    @GetMapping("/all")
    public String showAllAppointmentsPage(Model model, Principal principal){
        model.addAttribute(userService.getAllUserAppointments(principal.getName()));
        return "appointment_history";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createBooking(BookingDTO bookingDTO, Principal principal){
        bookingDTO.setClientName(principal.getName());
        userService.createBooking(bookingDTOMapper.mapToBooking(bookingDTO));

        return "redirect:/client/booking";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteAppointment(int id){
        userService.deleteAppointment(id);

        return "redirect:/client/booking";
    }
}
