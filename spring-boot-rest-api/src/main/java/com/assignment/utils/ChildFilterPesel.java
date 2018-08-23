package com.assignment.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.assignment.entity.Child;

public class ChildFilterPesel implements Specification<Child>{

	private String pesel;
	
	public ChildFilterPesel(String pesel) {
		super();
		this.pesel = pesel;
	}

	@Override
    public Predicate toPredicate(Root<Child> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (pesel == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }
        return cb.equal(root.get("pesel"), this.pesel);
	}
}
