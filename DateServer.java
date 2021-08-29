
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * This program demonstrate the TCP server
 * @author emalianakasmuri
 *
 */

public class DateServer {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		
		try {
			
			// Create a server socket object
			server = new ServerSocket(59091);
			System.out.println("ProductServer is running. Ready to receive request.");
			
			// Continually running to listen for request
			while (true) {
				
				// Accept client request and wrap into socket
				Socket socket = server.accept();
				
				// Open output stream and respond to client's request
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(new Date().toString());
                
                socket.close();
            }
			
		} catch (IOException e) {

			System.out.println("Durian Tunggal... we got problem");
			e.printStackTrace();
		
		} finally {

			try {

				if (server != null)
					server.close();

			} catch (IOException e) {

				System.out.println("Durian Tunggal... we got problem. Server failed to close.");
				e.printStackTrace();
			}

		}
		
		
		

	}

}
