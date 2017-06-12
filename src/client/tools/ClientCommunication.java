package client.tools;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import client.gui.Main;
import tools.Request;
import tools.Request.Requests;
import tools.Response;
import tools.SerializeManager;

/**
 * The Class ClientCommunication. Used for connecting to the server,
 * transferring data in both directions.
 */
public class ClientCommunication {

	/** The address of the server */
	private String address;

	/** The port of the server */
	private int serverPort;

	/** The DataInputStream in is used to read data from socket */
	private DataInputStream in;

	/** The DataOutputStream out is used to write data to socket */
	private DataOutputStream out;

	/** The login. */
	private String login;

	/** The password. */
	private String password;

	/** The response. from the server */
	private Response response;

	/** True if the logged in user is admin. */
	private boolean isAdmin = false;

	/** True if the logged in user is guest. */
	private boolean isGuest = false;
	
	private Socket socket;

	/**
	 * Instantiates a new client communication.
	 *
	 * @param login
	 *            the login
	 * @param password
	 *            the password
	 */
	public ClientCommunication(String login, String password) {
		super();

		serverPort = 6666;
		address = "127.0.0.1";

		this.login = login;
		this.password = password;
	}

	/**
	 * Writes request to the server and sends it
	 *
	 * @param <T>
	 *            the type of data to send
	 * @param t
	 *            the data to send
	 */
	private <T> void write(T t) {
		String serializedObj = new SerializeManager<T>().serialize(t);
		try {
			out.writeUTF(serializedObj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Read.
	 *
	 * @param <T>
	 *            the type of data to read
	 * @param field
	 *            the variable to store read data
	 * @return the read data
	 */
	private <T> T read(T field) {
		String serializedObj = new String();
		try {
			serializedObj = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new SerializeManager<T>().deserialize(serializedObj, field);
	}

	/**
	 * Sets the new request.
	 *
	 * @param request
	 *            the request
	 * @return the response from a server
	 */
	public Response setNewRequest(Request request) {
		if(!socket.isConnected()){
			return null;
		}
		write(request);
		
		if (request.getRequest() != Requests.EXIT) {
			response = read(response);
			return response;
		} else
			return null;
	}

	/**
	 * Sets the connection with the server.
	 *
	 * @return true, if successful
	 */
	public boolean setConnection() {
		try {
			InetAddress ipAddress = InetAddress.getByName(address);
			//@SuppressWarnings("resource")
			socket = new Socket(ipAddress, serverPort);

			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			return false;
		}

		write(login);

		Integer rnd = null;
		rnd = read(rnd);

		Integer pswd = new Integer(new String("" + password.hashCode() + rnd).hashCode());
		write(pswd);

		String authorization = new String();
		authorization = read(authorization);
		if (authorization.equals("wrong user")) {
			Main.showMessageBox("Неправильное имя пользователя или пароль");
			return false;
		} else if (authorization.equals("admin")) {
			isAdmin = true;
			return true;
		} else if (authorization.equals("guest")) {
			isGuest = true;
			return true;
		}
		return true;
	}

	/**
	 * Checks if it is a guest.
	 *
	 * @return true, if it is a guest
	 */
	public boolean isGuest() {
		return isGuest;
	}

	/**
	 * Checks if it is an admin.
	 *
	 * @return true, if it is an admin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

}
