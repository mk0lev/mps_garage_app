package com.example.mps.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    protected String username;

    protected String password;

    protected boolean enabled;

    private String services;

    @OneToMany(mappedBy = "worker")
    protected List<Repair> repairs;

    @OneToMany(mappedBy = "client")
    protected List<Booking> appointments;

    @OneToMany(mappedBy = "owner")
    private List<Vehicle> vehicleList;

    @ManyToOne
    private Workshop workshop;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
