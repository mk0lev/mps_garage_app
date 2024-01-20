package com.example.mps.controllers.TLcontrollers;

import com.example.mps.model.DTO.RepairDTO;
import com.example.mps.model.Repair;
import com.example.mps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/worker/repair")
public class RepairController {
    private final UserService userService;

    @Autowired
    public RepairController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRepairPage(Model model){
        model.addAttribute(new Repair());
        return "repair";
    }

    @GetMapping("/client")
    public String showClientRepairsPage(Model model, Principal principal){
        model.addAttribute(userService.getRepairsByClient(principal.getName()));

        //TODO
        return "repair";
    }

    @GetMapping("/workshop")
    public String showWorkshopRepairsPage(Model model, Principal principal){
        model.addAttribute(userService.getRepairsByWorkshop(principal.getName()));

        //TODO
        return "repair";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addVehicle(RepairDTO repairDTO, Principal principal){
        userService.repair(repairDTO.getAppointment(), repairDTO.getPrice(), principal.getName());

        return "redirect:/repair";
    }
}
