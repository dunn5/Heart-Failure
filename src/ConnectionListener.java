import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ConnectionListener implements Runnable {
	
	private ServerSocket serverSocket;
	
	private int waitPeriod;
	
	public ConnectionListener(int port, int waitPeriod) {
		this.waitPeriod = waitPeriod;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("The port number is invalid and the listening socket couldn't be established. " +
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
				MessageReceiver messageReceiver = new MessageReceiver(receiverSocket, waitPeriod);
				Thread messageReceiverThread = new Thread(messageReceiver);
				messageReceiverThread.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
}
