import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ConnectionListener implements Runnable {
	
	private ServerSocket serverSocket;
	
	public ConnectionListener(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("The port number is invalid and the listening socket couldn't established. " +
					"Quiting the program.");
			System.exit(1);		
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "Couldn't retrieve the local host IP address.";
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket receiverSocket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}
