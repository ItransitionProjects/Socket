package server;

/**
 * The Class User, stores info about single user registered in the system.
 */
public class User{

	/**
	 * The Enum Roles.
	 */
	public static enum Roles {
		
		/** The administrator. */
		ADMINISTRATOR, 
 /** The user. */
 USER, 
 /** The guest. */
 GUEST
	};

	/** The role. */
	private Roles role;
	
	/** The login. */
	private String login;
	
	/** The password. */
	private long password;
	
	/** The name. */
	private String name;


	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Roles getRole() {
		return role;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public long getPassword() {
		return password;
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
	 * Instantiates a new user.
	 *
	 * @param role the role
	 * @param login the login
	 * @param password the password
	 * @param name the name
	 */
	public User(Roles role, String login, String password, String name) {
		super();
		this.role = role;
		this.login = login;
		this.password = password.hashCode();
		this.name = name;
	}

	/**
	 * Instantiates a new user.
	 */
	public User() {
		super();
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Roles role) {
		this.role = role;
	}
	
	
}
