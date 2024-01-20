package com.example.mps.repository.contracts;

import com.example.mps.model.Repair;
import com.example.mps.model.User;
import com.example.mps.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository  extends JpaRepository<Repair, Integer> {
    List<Repair> findAllByClient(User user);

    List<Repair> findAllByWorkshop(Workshop workshop);

}
