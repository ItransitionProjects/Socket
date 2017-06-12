package tools;

import java.util.Vector;

import server.PersonalData;
import server.User;

/**
 * The Class Request. Used a data transport from client to server.
 */
public class Request {

	/**
	 * The Enum Requests.
	 */
	public static enum Requests {
		
		/** The show all. */
		SHOW_ALL, 
 /** The find. */
 FIND, 
 /** The add. */
 ADD, 
 /** The edit. */
 EDIT, 
 /** The delete. */
 DELETE, 
 /** The exit. */
 EXIT, 
 /** The show users. */
 SHOW_USERS, 
 /** The change users. */
 CHANGE_USERS
	};

	/** The request. */
	private Requests request = null;
	
	/** The search str. */
	private String searchStr = null;
	
	/** The personal data. */
	private PersonalData personalData = null;
	
	/** The personal data new. */
	private PersonalData personalDataNew = null;
	
	/** The users. */
	private Vector<User> users = new Vector<User>();

	/**
	 * Instantiates a new request.
	 */
	public Request() {
		super();
	}

	/**
	 * Instantiates a new request.
	 *
	 * @param request the request
	 * @param searchStr the search str
	 * @param personalData the personal data
	 * @param personalDataNew the personal data new
	 */
	public Request(Requests request, String searchStr, PersonalData personalData, PersonalData personalDataNew) {
		super();
		this.request = request;
		this.searchStr = searchStr;
		this.personalData = personalData;
		this.personalDataNew = personalDataNew;
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public Requests getRequest() {
		return request;
	}

	/**
	 * Sets the request.
	 *
	 * @param request the new request
	 */
	public void setRequest(Requests request) {
		this.request = request;
	}

	/**
	 * Gets the personal data.
	 *
	 * @return the personal data
	 */
	public PersonalData getPersonalData() {
		return personalData;
	}

	/**
	 * Gets the personal data new.
	 *
	 * @return the personal data new
	 */
	public PersonalData getPersonalDataNew() {
		return personalDataNew;
	}

	/**
	 * Sets the personal data new.
	 *
	 * @param personalDataNew the new personal data new
	 */
	public void setPersonalDataNew(PersonalData personalDataNew) {
		this.personalDataNew = personalDataNew;
	}

	/**
	 * Sets the personal data.
	 *
	 * @param personalData the new personal data
	 */
	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
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

	/**
	 * Gets the search str.
	 *
	 * @return the search str
	 */
	public String getSearchStr() {
		return searchStr;
	}

	/**
	 * Sets the search str.
	 *
	 * @param searchStr the new search str
	 */
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

}
