package com.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.entity.Father;
import com.assignment.repository.FamilyRepository;
import com.assignment.repository.FatherRepository;

@Service
public class ReadFatherService {

	@Autowired
	private FatherRepository fatherRepository;
	
	@Autowired
	private FamilyRepository familyRepository;

	public Father getFatherByFamilyId(int familyId) {
		return familyRepository.findOne(familyId).getFather();
	}
	
	public Father getFatherByFatherId(int fatherId) {
		return fatherRepository.findOne(fatherId);
	}

}
