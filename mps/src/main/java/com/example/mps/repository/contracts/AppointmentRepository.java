package com.example.mps.repository.contracts;

import com.example.mps.model.Booking;
import com.example.mps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByClient(User user);

}
