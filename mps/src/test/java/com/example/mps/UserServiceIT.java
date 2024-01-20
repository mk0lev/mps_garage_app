package com.example.mps;

import com.example.mps.model.*;
import com.example.mps.repository.contracts.AppointmentRepository;
import com.example.mps.repository.contracts.RepairRepository;
import com.example.mps.repository.contracts.UserRepository;
import com.example.mps.repository.contracts.VehicleRepository;
import com.example.mps.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceIT {

    private Booking appointment;
    private Vehicle vehicle;
    private User user;
    private Workshop workshop;
    private Repair repair;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RepairRepository repairRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        appointment = new Booking();
        user = new User();
        vehicle = new Vehicle();
        workshop = new Workshop();
        repair = new Repair();

        user.setUsername("Test owner");
        user.setWorkshop(workshop);
        vehicle.setOwner(user);
        vehicle.setId(1);

        appointment.setId(1);
        appointment.setVehicle(vehicle);
        appointment.setWorkshop(workshop);
    }

    @Test
    public void createAppointment() {
       userService.createBooking(appointment);
    }

    @Test
    public void deleteAppointment() {
       userService.deleteAppointment(appointment.getId());
    }

    @Test
    public void addCar() {
        userService.addCar(vehicle);
    }

    @Test
    public void deleteCar() {
        userService.removeCar(vehicle.getId());
    }

    @Test
    public void repair() {
        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);

        userService.repair(appointment, 100, user.getUsername());
    }

    @Test
    public void getRepairsByWorkshop() {
        Mockito.when(repairRepository.findAllByWorkshop(any())).thenReturn(Collections.singletonList(repair));
        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);
        List<Repair> repairList = userService.getRepairsByWorkshop(user.getUsername());

        Assertions.assertEquals(1, repairList.size());
    }

    @Test
    public void  getRepairsByClient() {
        Mockito.when(userRepository.findByUsername(any())).thenReturn(user);
        Mockito.when(repairRepository.findAllByClient(any())).thenReturn(Collections.singletonList(repair));
        List<Repair> repairList = userService.getRepairsByClient(user.getUsername());

        Assertions.assertEquals(1, repairList.size());
    }
}
