package com.example.validaaation.repository;

import com.example.validaaation.model.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ValidationRepo extends JpaRepository<Validation, Integer> {

}
