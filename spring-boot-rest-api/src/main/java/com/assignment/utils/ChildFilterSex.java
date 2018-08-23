package com.assignment.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.assignment.entity.Child;

public class ChildFilterSex implements Specification<Child>{

	private String sex;
	
	public ChildFilterSex(String sex) {
		super();
		this.sex = sex;
	}

	@Override
    public Predicate toPredicate(Root<Child> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (sex == null) {
            return cb.isTrue(cb.literal(true)); // always true = no filtering
        }
        return cb.equal(root.get("sex"), this.sex);
	}
}
