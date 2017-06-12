package tools;

import java.util.Vector;

import server.PersonalData;
import server.User;

/**
 * The Class Response. Used as a data transport from server to client
 */
public class Response {

	/** The request. */
	private Request.Requests request = null;
	
	/** The persons. */
	private Vector<PersonalData> persons = null;
	
	/** The users. */
	private Vector<User> users = null;
	
	/** The person. */
	private PersonalData person = null;
	
	/** The success. */
	private boolean success = false;

	/**
	 * Instantiates a new response.
	 *
	 * @param request the request
	 * @param persons the persons
	 * @param person the person
	 * @param success the success
	 */
	public Response(Request.Requests request, Vector<PersonalData> persons, PersonalData person, boolean success) {
		super();
		this.request = request;
		this.persons = persons;
		this.person = person;
		this.success = success;

	}

	/**
	 * Instantiates a new response.
	 */
	public Response() {
		super();
	}

	/**
	 * Checks if is success.
	 *
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public Request.Requests getRequest() {
		return request;
	}

	/**
	 * Sets the request.
	 *
	 * @param request the new request
	 */
	public void setRequest(Request.Requests request) {
		this.request = request;
	}

	/**
	 * Gets the persons.
	 *
	 * @return the persons
	 */
	public Vector<PersonalData> getPersons() {
		return persons;
	}

	/**
	 * Sets the persons.
	 *
	 * @param persons the new persons
	 */
	public void setPersons(Vector<PersonalData> persons) {
		this.persons = persons;
	}

	/**
	 * Gets the person.
	 *
	 * @return the person
	 */
	public PersonalData getPerson() {
		return person;
	}

	/**
	 * Sets the person.
	 *
	 * @param person the new person
	 */
	public void setPerson(PersonalData person) {
		this.person = person;
	}

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public Vector<User> getUsers() {
		return users;
	}

	/**
	 * Sets the users.
	 *
	 * @param users the new users
	 */
	public void setUsers(Vector<User> users) {
		this.users = users;
	}

}
