package com.assignment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.assignment.entity.Child;
import com.assignment.entity.Family;
import com.assignment.entity.Father;
import com.assignment.repository.ChildRepository;
import com.assignment.repository.FatherRepository;

@Service
public class SearchChildService {

	@Autowired
	private ChildRepository childRepository;
	
	@Autowired
	private FatherRepository fatherRepository;
	
	//Gets children ID's given the family ID
	public List<Integer> searchChild(int familyId){
		List<Integer> childIds = new ArrayList<>();
		for (Child child : childRepository.findByFamilyId(familyId)) {
			childIds.add(child.getId());
		}
		return childIds;
	}
	//Takes into consideration searching criteria (specifications) and pageables
	//, which in turn will be utilized in AppController
	public Page<Father> searchResult(Specification<Child> spec, Pageable pageable) {
		List<Child> validCandidates=childRepository.findAll(spec);
		List<Family> families = fetchFamilies(validCandidates);
		return fatherRepository.findByFamilyIn(families, pageable);
	}
	//Helper method for the search engine
	public List<Family> fetchFamilies(List<Child> children) {
		List<Family> families = new ArrayList<Family>();
		for(Child child : children) {
			if(!families.contains(child.getFamily())) {
				families.add(child.getFamily());
			}
		}
		return families;
	}
}
