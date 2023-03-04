package com.example.Library.Management.System.Repository;

import com.example.Library.Management.System.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
