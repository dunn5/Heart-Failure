import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Controller {
	
	private ConnectionListener connectionListener;
	
	private BufferedReader bufferedReader;
	
	private static final int SENDER_WAIT_PERIOD = 2000; 

	public Controller() {
		setUp();
		getInputs();
	}

	private void setUp() {
		System.out.println("Enter the port for listening connections:");
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			int port = Integer.parseInt(bufferedReader.readLine());
			connectionListener = new ConnectionListener(port, SENDER_WAIT_PERIOD);
		} catch (IOException e) {
			System.out.println("The port number is invalid. Quiting the program.");
			System.exit(1);
		}
		Thread connectionListenerThread = new Thread(connectionListener);
		connectionListenerThread.start();	
		String ownIPAddress = connectionListener.getIPAddress();
		System.out.println("Started to listen connections on IP: "+ownIPAddress);
	}
	
	private void getInputs() {
		while (true) {
			System.out.println("Enter the IP address of the computer that you want to send messages to:");
			try {
				String IPAddress = bufferedReader.readLine();
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	
}
