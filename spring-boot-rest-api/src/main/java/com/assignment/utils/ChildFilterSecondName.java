package com.assignment.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.assignment.entity.Child;

public class ChildFilterSecondName implements Specification<Child>{

	private String secondName;
	
	
	public ChildFilterSecondName(String secondName) {
		super();
		this.secondName = secondName;
	}


	@Override
    public Predicate toPredicate(Root<Child> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (secondName == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }
        return cb.equal(root.get("secondName"), this.secondName);
	}
}
