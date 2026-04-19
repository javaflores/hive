package br.com.bbts.hive.tasks.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
public class DadosClienteMercadoLivreDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String name;
	private String lastName;
	private Integer socialSecurityNumber;
	private String passportNumber;
	private String birthDate;
	private Integer genderType;
	private String motherFullName;
	private String fatherFullName;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(Integer socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getGenderType() {
		return genderType;
	}

	public void setGenderType(Integer genderType) {
		this.genderType = genderType;
	}

	public String getMotherFullName() {
		return motherFullName;
	}

	public void setMotherFullName(String motherFullName) {
		this.motherFullName = motherFullName;
	}

	public String getFatherFullName() {
		return fatherFullName;
	}

	public void setFatherFullName(String fatherFullName) {
		this.fatherFullName = fatherFullName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
