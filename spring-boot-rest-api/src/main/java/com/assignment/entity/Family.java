package com.assignment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name="family")
public class Family{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="familyId")
	private int id;
	@JsonIgnore
	@OneToOne(mappedBy="family",cascade = CascadeType.ALL)
	private Father father;
	@JsonIgnore
	@OneToMany(mappedBy="family",cascade = CascadeType.ALL)
	private List<Child> children;

//	public Family(int id) {
//		super();
//		this.id = id;
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Father getFather() {
		return father;
	}

	public void setFather(Father father) {
		this.father = father;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}
	
}
