package client.tools;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * The Class ChangeParserClient. Sets connection between server and
 * client(admin) via additional socket to change parser type
 */
public class ChangeParserClient {

	/** The DataOutputStream out used to send messages from client to server */
	private DataOutputStream out;

	/**
	 * Sends request to server to change parser.
	 *
	 * @param newChoise
	 *            the new parser to use
	 */
	public void changeParser(String newChoise) {
		try {
			//TODO: сделать из newChoise URL запрос и передавать его
			out.writeUTF(newChoise);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Instantiates a new change parser client and connects to the server
	 */
	public ChangeParserClient() {
		super();
		int serverPort = 6665;
		String address = "127.0.0.1";

		try {
			InetAddress ipAddress = InetAddress.getByName(address);
			@SuppressWarnings("resource")
			Socket socket = new Socket(ipAddress, serverPort);
			out = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

}
