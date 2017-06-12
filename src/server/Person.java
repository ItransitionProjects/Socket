package server;

/**
 * The Class Person. Used to store general info about person.
 */
public class Person {
	
	/** The surname. */
	private String surname;
	
	/** The name. */
	private String name;
	
	/** The fathername. */
	private String fathername;
	
	/** The phone. */
	private String phone;
	
	/** The email. */
	private String email;
	
	/** The sex. */
	private String sex;

	/**
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the fathername.
	 *
	 * @return the fathername
	 */
	public String getFathername() {
		return fathername;
	}

	/**
	 * Sets the fathername.
	 *
	 * @param fathername the new fathername
	 */
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the sex.
	 *
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the sex.
	 *
	 * @param sex the new sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String toString() {
		return "Person [surname=" + surname + ", name=" + name + ", fathername=" + fathername + ", phone=" + phone
				+ ", email=" + email + ", sex=" + sex + "]";
	}

}
