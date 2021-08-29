
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import transaction.OrderedItem;
import product.Product;

public class OrderClient {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		OrderedItem order1 = new OrderedItem();
		order1.setProductCode(101);
		order1.setQuantity(3);
		
		
		try {
			
			// Create object and connect to the server that runs on local host
			socket = new Socket("127.0.0.1", 59091);
			
			System.out.println("Sending data to server");
			
			ObjectOutputStream ostr = new ObjectOutputStream(socket.getOutputStream());
			ostr.writeObject(order1);
			
			// Read response from the server
			Scanner in = new Scanner(socket.getInputStream());
			
			// Display response from the server
			while(in.hasNextLine()){
				System.out.println("Server response: " + in.nextLine());
			}
	        
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
