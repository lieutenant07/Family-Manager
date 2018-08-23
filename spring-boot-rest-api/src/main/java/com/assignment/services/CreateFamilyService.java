package com.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entity.Child;
import com.assignment.entity.Family;
import com.assignment.entity.Father;
import com.assignment.repository.ChildRepository;
import com.assignment.repository.FamilyRepository;
import com.assignment.repository.FatherRepository;

@Service
public class CreateFamilyService {

	@Autowired
	ChildRepository childRepository;
	
	@Autowired
	FatherRepository fatherRepository;
	
	@Autowired
	FamilyRepository familyRepository;
	
	//Persists family to repo
	public void addFamily(Family family) {
		familyRepository.save(family);
	}
	//Persists a single child to repo
	public void addChildToFamily(Child child, Family family) {
		child.setFamily(family);// It is not required only to provide a child for a father,
		// as by doing only that we say nothing about association child->father (only
		// father->child).
		// Having said that, if we do not set reverse relation as above, foreign key in
		// 'child' SQL table will be left out NULL!
		childRepository.save(child);
	}
	//Persists a father child to repo
	public void addFatherToFamily(Father father, Family family) {
		father.setFamily(family);// same as above
		fatherRepository.save(father);
	}
}
