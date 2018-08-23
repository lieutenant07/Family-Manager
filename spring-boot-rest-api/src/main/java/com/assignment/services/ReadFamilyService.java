package com.assignment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entity.Child;
import com.assignment.entity.Family;
import com.assignment.entity.Father;
import com.assignment.repository.FamilyRepository;

//ReadFamily from specification
@Service
public class ReadFamilyService {

	@Autowired
	ReadFatherService readFatherService;
	
	@Autowired
	SearchChildService searchChildService;
	
	@Autowired
	ReadChildService readChildService;
	
	@Autowired
	FamilyRepository familyRepository;
	
	public List<Family> retrieveAllFamilies() {
		return familyRepository.findAll();
	}
	
	public Family getFamily(int familyId) {
		return familyRepository.findOne(familyId);
	}
	
	public Father getFatherbyFamilyId(int familyId) {
		return readFatherService.getFatherByFamilyId(familyId);
	}
	
	public Father getFatherbyFatherId(int fatherId) {
		return readFatherService.getFatherByFatherId(fatherId);
	}
	
	public List<Integer> getChildrenIds(int familyId){
		return searchChildService.searchChild(familyId);
	}
	
	public List<Child> getChildren(int familyId){
		List<Child> children = new ArrayList<>();
		searchChildService.searchChild(familyId);
		for(Integer childId : searchChildService.searchChild(familyId)) {
			children.add(readChildService.readChild(childId));
		}
		return children;
	}
}
