package server;

import server.tools.xml.ISerializeXml;

/**
 * The Class PersonalData. Stores full info about persons.
 */
public class PersonalData implements ISerializeXml {

	/** The person. */
	private Person person = new Person();

	/** The person job. */
	private PersonJob personJob = new PersonJob();

	/**
	 * Instantiates a new personal data.
	 *
	 * @param surname
	 *            the surname
	 * @param name
	 *            the name
	 * @param fathername
	 *            the fathername
	 * @param phone
	 *            the phone
	 * @param email
	 *            the email
	 * @param employee
	 *            the employee
	 * @param jobExperience
	 *            the job experience
	 * @param jobPosition
	 *            the job position
	 * @param sex
	 *            the sex
	 */
	public PersonalData(String surname, String name, String fathername, String phone, String email, String employee,
			String jobExperience, String jobPosition, String sex) {
		super();
		this.person.setSurname(surname);
		this.person.setName(name);
		this.person.setFathername(fathername);
		this.person.setPhone(phone);
		this.person.setEmail(email);
		this.personJob.setEmployee(employee);
		this.personJob.setJobExperience(jobExperience);
		this.personJob.setJobPosition(jobPosition);
		this.person.setSex(sex);
	}

	public String toString() {
		return "PersonalData [person=" + person + ", personJob=" + personJob + "]";
	}

	/**
	 * Instantiates a new personal data.
	 */
	public PersonalData() {
		super();
	}

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return person.getSurname();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return person.getName();
	}

	/**
	 * Gets the fathername.
	 *
	 * @return the fathername
	 */
	public String getFathername() {
		return person.getFathername();
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return person.getPhone();
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return person.getEmail();
	}

	/**
	 * Gets the employee.
	 *
	 * @return the employee
	 */
	public String getEmployee() {
		return personJob.getEmployee();
	}

	/**
	 * Gets the job experience.
	 *
	 * @return the job experience
	 */
	public String getJobExperience() {
		return personJob.getJobExperience();
	}

	/**
	 * Gets the job position.
	 *
	 * @return the job position
	 */
	public String getJobPosition() {
		return personJob.getJobPosition();
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname
	 *            the new surname
	 */
	public void setSurname(String surname) {
		this.person.setSurname(surname);
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.person.setName(name);
	}

	/**
	 * Sets the fathername.
	 *
	 * @param fathername
	 *            the new fathername
	 */
	public void setFathername(String fathername) {
		this.person.setFathername(fathername);
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone
	 *            the new phone
	 */
	public void setPhone(String phone) {
		this.person.setPhone(phone);
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.person.setEmail(email);
	}

	/**
	 * Sets the employee.
	 *
	 * @param employee
	 *            the new employee
	 */
	public void setEmployee(String employee) {
		this.personJob.setEmployee(employee);
	}

	/**
	 * Sets the job experience.
	 *
	 * @param jobExperience
	 *            the new job experience
	 */
	public void setJobExperience(String jobExperience) {
		this.personJob.setJobExperience(jobExperience);
	}

	/**
	 * Sets the job position.
	 *
	 * @param jobPosition
	 *            the new job position
	 */
	public void setJobPosition(String jobPosition) {
		this.personJob.setJobPosition(jobPosition);
	}

	
	public String getXmlFileName() {
		return new String(getName() + getSurname());
	}

	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return person.getSex();
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex
	 *            the new sex
	 */
	public void setSex(String sex) {
		this.person.setSex(sex);
	}
}
