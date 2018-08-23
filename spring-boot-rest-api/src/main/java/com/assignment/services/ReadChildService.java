package com.assignment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entity.Child;
import com.assignment.repository.ChildRepository;

@Service
public class ReadChildService {

	@Autowired
	private ChildRepository childRepository;
	//Fetches all children
	public List<Child> getAllChildren(int familyId){
		List<Child> children = new ArrayList<>();
		childRepository.findByFamilyId(familyId)
		.forEach(children::add);
		return children;
	}
	//Fetches a single child from the repo, given the ID
	public Child readChild(int childId) {
		return childRepository.findOne(childId);
	}
}
