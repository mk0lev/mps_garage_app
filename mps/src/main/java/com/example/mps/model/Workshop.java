package com.example.mps.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY)
    private List<User> workerList;

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY)
    private List<Repair> repairs;
}
