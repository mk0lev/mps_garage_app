package com.example.mps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Repair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User worker;

    @ManyToOne
    private User client;

    @ManyToOne
    private Workshop workshop;

    @ManyToOne
    private Vehicle vehicle;

    private double price;
}
