package com.example.mps.repository.contracts;

import com.example.mps.model.User;
import com.example.mps.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findAllByOwner(User user);
}
