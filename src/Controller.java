import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
	
	private ConnectionListener connectionListener;
	

	public Controller() {
		setUp();
	}

	private void setUp() {
		System.out.println("Enter the port for listening connections:");
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			int port = Integer.parseInt(bufferedReader.readLine());
			connectionListener = new ConnectionListener(port);
		} catch (IOException e) {
			System.out.println("The port number is invalid. Quiting the program.");
			System.exit(1);
		}
		Thread connectionListenerThread = new Thread(connectionListener);
		connectionListenerThread.start();	
		String ownIPAddress = connectionListener.getIPAddress();
		System.out.println("Started to listen connections on IP: "+ownIPAddress);
	}
	
}
