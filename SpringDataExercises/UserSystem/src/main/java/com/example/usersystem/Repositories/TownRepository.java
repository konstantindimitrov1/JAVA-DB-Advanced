package com.example.usersystem.Repositories;

import com.example.usersystem.Entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {
}
