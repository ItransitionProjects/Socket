package server.tools;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import server.Cataloger;
import server.Users;

/**
 * The Class ServerMain. Starts the server
 */
public class ServerMain {
	
	/** The Constant PORT. */
	final static int PORT = 6666;
	
	/** The Constant loggerServer. */
	public final static Logger loggerServer = Logger.getLogger(ServerMain.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		ServerSocket ss = null;
		Socket socket = null;
		//new IsClientAlive();
		try {

			ss = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();

		}
		Users.loadData();
		Cataloger.loadData();
		new ChangeParser();
		while (true) {
			try {
				socket = ss.accept();
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
			new ServerThreadCommunication(socket);
		}
	}

}