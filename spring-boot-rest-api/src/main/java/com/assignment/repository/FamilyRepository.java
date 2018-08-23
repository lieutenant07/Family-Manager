package com.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Integer> {

}
