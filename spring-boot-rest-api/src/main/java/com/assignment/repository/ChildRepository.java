package com.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer>,JpaSpecificationExecutor<Child> {
	public List<Child> findByFamilyId(int familyId);
}
