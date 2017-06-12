package server;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import server.tools.ServerMain;
import tools.Response;
import tools.SerializeManager;
import tools.Request.Requests;

/**
 * The Class Users.
 */
public abstract class Users {

	/** The Hashtable containes Key = users login, Value = object user */
	public static Hashtable<String, User> ht = new Hashtable<String, User>();

	/**
	 * Gets the xml file name.
	 *
	 * @return the xml file name
	 */
	public static String getXmlFileName() {
		return new String("Users");
	}

	/**
	 * Adds the user.
	 *
	 * @param user
	 *            the user
	 */
	public static void addUser(User user) {
		ht.put(user.getLogin(), user);
		new SerializeManager<Hashtable<String, User>>().save(ht, getXmlFileName());
	}

	/**
	 * Check by login and password.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 * @param rnd
	 *            the rnd
	 * @return the user
	 */
	public static User checkByLoginAndPassword(String login, long password, int rnd) {
		User user = ht.get(login);
		if (user == null) {
			System.out.println("Пользавтеля с логином " + login + " не существует");
			return null;
		}
		if (new String("" + user.getPassword() + rnd).hashCode() != password) {
			System.out.println("Неверный пароль");
			return null;
		}
		return user;
	}

	/**
	 * Gets the ht.
	 *
	 * @return the ht
	 */
	public static Hashtable<String, User> getHt() {
		return ht;
	}

	/**
	 * Load data.
	 */
	public static void loadData() {
		ht = new SerializeManager<Hashtable<String, User>>().load(ht, getXmlFileName());
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public static Response getAll() {
		Enumeration<User> files = ht.elements();
		Vector<User> users = new Vector<User>();
		while (files.hasMoreElements()) {
			users.addElement(files.nextElement());
		}
		ServerMain.loggerServer.info(new String("Request to get all users is served"));
		Response responce = new Response(Requests.SHOW_USERS, null, null, true);
		responce.setUsers(users);
		return responce;
	}

	/**
	 * Change users.
	 *
	 * @param users
	 *            the users
	 * @return the response
	 */
	public static Response changeUsers(Vector<User> users) {
		ht.clear();
		for (int i = 0; i < users.size(); i++)
			addUser(users.get(i));
		Response responce = new Response(Requests.CHANGE_USERS, null, null, true);
		return responce;
	}

}
