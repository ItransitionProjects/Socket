package server.tools;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.Cataloger;

/**
 * The Class ChangeParser. Waits for the admin to be connected and changes parser on demand.
 */
public class ChangeParser implements Runnable {

	/** The Constant PORT. */
	final static int PORT = 6665;
	
	/** The thread, which waits for commands from admin */
	private Thread t;
	
	/** The in. */
	private DataInputStream in;

	/**
	 * Instantiates a new change parser and runs new thread.
	 */
	public ChangeParser() {
		super();
		t = new Thread(this);
		t.start();
	}

	/**
	 * Changes parser.
	 *
	 * @param socket the socket
	 */
	private void Change(Socket socket) {

		try {
			String command = in.readUTF();
			System.out.println(command);
			new RunnerByAnnotation().run(Cataloger.class, command);
			//TODO: в command передавать URL запрос и 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void run() {
		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {
			try {
				socket = ss.accept();
				in = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				System.out.println("I/O error: " + e);
			}
			Change(socket);
		}
	}
}
