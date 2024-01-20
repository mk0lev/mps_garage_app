package com.example.mps.controllers.TLcontrollers;

import com.example.mps.model.Vehicle;
import com.example.mps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/client/add_new_car")
public class VehicleController {
    private final UserService userService;

    @Autowired
    public VehicleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showVehiclePage(Model model){
        model.addAttribute(new Vehicle());
        return "add_new_car";
    }

    @GetMapping("/all")
    public String showAllVehiclePage(Model model, Principal principal){
        model.addAttribute(userService.getAllUserCars(principal.getName()));
        return "my_cars";
    }

    @RequestMapping(value = "/add_cars", method = RequestMethod.POST)
    public String addVehicle(Vehicle vehicle, Principal principal){
        vehicle.setOwner(userService.getUser(principal.getName()));
        userService.addCar(vehicle);

        return "redirect:/client/add_new_car/all";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeVehicle(@PathVariable int id){
        userService.removeCar(id);

        return "redirect:/client/add_new_car/all";
    }
}