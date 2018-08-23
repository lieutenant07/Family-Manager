package com.assignment.utils;

import java.sql.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.assignment.entity.Child;

public class ChildFilterDate implements Specification<Child>{

	private Date birthDate;
	
	public ChildFilterDate(Date birthDate) {
		super();
		this.birthDate = birthDate;
	}

	@Override
    public Predicate toPredicate(Root<Child> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (birthDate == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }
        return cb.equal(root.get("birthDate"), this.birthDate);
	}
}
