
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class IntServer {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		
		try {
			
			// Create a server socket object
			server = new ServerSocket(59091);
			System.out.println("The server has been connected");
			
			// Continually running to listen for request
			while (true) {
				
				// Accept client request and wrap into socket
				Socket socket = server.accept();
				
				DataInputStream instr = new DataInputStream(socket.getInputStream());
				
				int input = instr.readInt();
				
				PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
				output.println(input*input);
                
				System.out.println("Complete");
				
                socket.close();
            }
			
		} catch (IOException e) {

			System.out.println("Problem connecting to the server");
			e.printStackTrace();
		
		} finally {

			try {

				if (server != null)
					server.close();

			} catch (IOException e) {

				System.out.println("Problem connecting to the server. Server failed to close.");
				e.printStackTrace();
			}

		}
		
		
		

	}

}
