package com.example.mps.model.DTO;

import com.example.mps.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {
    Booking appointment;
    double price;
}
