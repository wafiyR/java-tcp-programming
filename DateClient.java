
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * This program demonstrate the TCP client
 * 
 * @author emalianakasmuri
 *
 */
public class DateClient {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		try {
			
			// Create object and connect to the server that runs on local host
			socket = new Socket("127.0.0.1", 59091);
			
			// Read response from the server
			Scanner in = new Scanner(socket.getInputStream());
			
			// Display response from the server
	        System.out.println("Server response: " + in.nextLine());
	        
		} catch (Exception e) {
			
			System.out.println("Durian Tunggal... we got problem");
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (socket != null)
					socket.close();
			
			} catch (Exception e) {
				
				System.out.println("Durian Tunggal... we got problem");
				e.printStackTrace();
			}
			
			System.out.println("End of request");
			
		}
        

	}

}
