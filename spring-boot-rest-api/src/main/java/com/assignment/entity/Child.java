package com.assignment.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="child")
public class Child  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="childId")
	private int id;
	@Column(name="firstName")
	private String firstName;
	@Column(name="secondName")
	private String secondName;
	@Column(name="pesel")
	private String pesel;
	@Column(name="sex")
	private String sex;
	@Column(name="birthDate")
	private Date birthDate;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="childIdfamily",referencedColumnName="familyId")
	private Family family;
	
	public int getId() {
		return id;
	}
	public void setId(int iD) {
		id = iD;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}
	
	
}
