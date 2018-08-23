package com.assignment.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.entity.Child;
import com.assignment.entity.Family;
import com.assignment.entity.Father;
import com.assignment.services.CreateFamilyService;
import com.assignment.services.ReadChildService;
import com.assignment.services.ReadFamilyService;
import com.assignment.services.ReadFatherService;
import com.assignment.services.SearchChildService;
import com.assignment.utils.ChildFilterDate;
import com.assignment.utils.ChildFilterFirstName;
import com.assignment.utils.ChildFilterPesel;
import com.assignment.utils.ChildFilterSecondName;
import com.assignment.utils.ChildFilterSex;

@CrossOrigin
@RestController
public class AppController {

	@Autowired
	CreateFamilyService createFamilyService;

	@Autowired
	ReadFamilyService readFamilyService;

	@Autowired
	ReadChildService readChildService;

	@Autowired
	SearchChildService searchChildService;

	@Autowired
	ReadFatherService readFatherService;

	// ----------FAMILY---------
	// Exposes end-points for adding/fetching family entity
	@RequestMapping(method = RequestMethod.GET, value = "/fam")
	public List<Family> getAllFamilies() {
		return readFamilyService.retrieveAllFamilies();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/fam")
	public Family addFamily(@RequestBody Family family) {
		createFamilyService.addFamily(family);
		return family;
	}

	// ----------CHILD---------
	// Exposes end-points for adding/fetching children to existing families
	@RequestMapping(method = RequestMethod.GET, value = "/fam/{id}/chld")
	public List<Child> getAllChildren(@PathVariable int id) {
		return readChildService.getAllChildren(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fam/{familyId}/chld/{id}")
	public Child getChild(@PathVariable int id) {
		return readChildService.readChild(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/fam/{familyId}/chld")
	public Child addChildToFamily(@RequestBody Child child, @PathVariable int familyId) {
		createFamilyService.addChildToFamily(child, readFamilyService.getFamily(familyId));
		return child;
	}

	// ----------FATHER---------
	// Exposes end-points for adding/fetching fathers to existing families
	@RequestMapping(method = RequestMethod.GET, value = "/fam/{id}/fath")
	public Father getFatherByFamilyId(@PathVariable int id) {
		return readFatherService.getFatherByFamilyId(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fath/{fatherId}")
	public Father getFatherByFatherId(@PathVariable int fatherId) {
		return readFatherService.getFatherByFatherId(fatherId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/fam/{familyId}/fath")
	public Father addFatherToFamily(@RequestBody Father father, @PathVariable int familyId) {
		createFamilyService.addFatherToFamily(father, readFamilyService.getFamily(familyId));
		return father;
	}

	// ----------SEARCH---------
	// This service provides unique list of fathers based on search criteria
	// Number of predicates necessary to initiate the search is arbitrary (1-4),
	// thanks to specifications
	@RequestMapping("/searchChild")
	public Page<Father> findFathers(@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "secondName", required = false) String secondName,
			@RequestParam(value = "pesel", required = false) String pesel,
			@RequestParam(value = "sex", required = false) String sex,
			@RequestParam(value = "birthDate", required = false) Date birthDate,
			@PageableDefault(value = 5) Pageable pageable) {

		Specification<Child> spec = Specifications.where(new ChildFilterFirstName(firstName))
				.and(new ChildFilterSecondName(secondName)).and(new ChildFilterPesel(pesel))
				.and(new ChildFilterSex(sex)).and(new ChildFilterDate(birthDate));

		return searchChildService.searchResult(spec, pageable);
	}
}
