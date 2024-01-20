package com.example.mps.model.DTO;

import com.example.mps.model.Booking;
import com.example.mps.model.Repair;
import com.example.mps.model.Vehicle;
import lombok.Data;
import java.util.List;

@Data
public class UserDTO {
    protected String username;

    protected String password;

    protected boolean enabled;

    private String services;

    protected List<Repair> repairs;

    protected List<Booking> appointments;

    private List<Vehicle> vehicleList;

    private String workshopName;
}
