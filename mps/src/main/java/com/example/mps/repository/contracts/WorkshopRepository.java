package com.example.mps.repository.contracts;

import com.example.mps.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {
    Workshop findByName(String name);
}
