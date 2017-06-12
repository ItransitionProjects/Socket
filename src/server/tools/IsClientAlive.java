package server.tools;

/**
 * The Class IsClientAlive. Kills the session with client, if the client is not alive.
 */
public class IsClientAlive implements Runnable {

	/** The Thread t, which counts the time since last operation for every single user. */
	private Thread t;

	/**
	 * Instantiates a object and starts thread for counting.
	 */
	public IsClientAlive() {
		super();
		t = new Thread(this);
		t.start();
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < ServerThreadCommunication.getAliveThreads().size(); i++) {
				if (System.currentTimeMillis()
						- ServerThreadCommunication.getAliveThreads().get(i).getLastOperationTime() > 10000) {
					ServerThreadCommunication.getAliveThreads().get(i).getT().interrupt();
					ServerThreadCommunication.getAliveThreads().remove(i);
					break;
				}
			}
		}

	}

}
