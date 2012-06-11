import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class MessageReceiver implements Runnable {
	
	private Socket receiverSocket;
	
	private static final int INPUT_BUFFER_SIZE = 1000;
	
	private int waitPeriod;

	public MessageReceiver(Socket receiverSocket, int waitPeriod) {
		this.receiverSocket = receiverSocket;
		this.waitPeriod = waitPeriod;
	}
	
	@Override
	public void run() {
		
		try {
			InputStream inputStream = receiverSocket.getInputStream();
			byte[] inputBuffer = new byte[INPUT_BUFFER_SIZE];
			int bytesRead = 0;
			while ((bytesRead = inputStream.read(inputBuffer)) != -1) {
				byte[] actualInput = new byte[bytesRead];
				for (int i=0; i<bytesRead; i++) {
					actualInput[i] = inputBuffer[i];
				}
				String receivedMessage = new String(actualInput, "UTF-8");
				inputBuffer = new byte[INPUT_BUFFER_SIZE];
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
