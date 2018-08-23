package com.assignment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.assignment.entity.Family;
import com.assignment.entity.Father;

@Repository
public interface FatherRepository extends PagingAndSortingRepository<Father, Integer> {
	Page<Father> findByFamilyIn(List<Family> families, Pageable pageable);
}
