package com.assignment.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.assignment.entity.Child;

public class ChildFilterFirstName implements Specification<Child>{

	private String firstName;
	
	public ChildFilterFirstName(String firstName) {
		super();
		this.firstName = firstName;
	}

	@Override
    public Predicate toPredicate(Root<Child> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (firstName == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }
        return cb.equal(root.get("firstName"), this.firstName);
	}
}
