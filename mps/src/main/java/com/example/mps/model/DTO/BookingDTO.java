package com.example.mps.model.DTO;

import lombok.Data;

@Data
public class BookingDTO {

    private String clientName;

    private String workshopName;

    private int vehicleId;

    private String date;
}
