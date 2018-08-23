package com.assignment.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="father")
public class Father {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="fatherId")
	private int id;
	@Column(name="birthDate")
	private Date birthDate;
	@Column(name="firstName")
	private String firstName;
	@Column(name="secondName")
	private String secondName;
	@Column(name="pesel")
	private String pesel;
	@OneToOne
	@JoinColumn(name="fatherIdfamily",referencedColumnName ="familyId")
	
	private Family family;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	
	
	
	
}
