
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import product.Product;
import transaction.OrderedItem;


public class OrderServer {

	public static void main(String[] args) {
		
		ServerSocket server = null;
		ArrayList<Product> products = new ArrayList<Product>();
		
		Product product1 = new Product();
		Product product2 = new Product();
		Product product3 = new Product();
		
		product1.setName("Whopper");
		product2.setName("Whopper with cheese");
		product3.setName("BK Double Mushroom Swiss");
		
		product1.setPrice(11.95);
		product2.setPrice(14.15);
		product3.setPrice(11.45);
		
		product1.setProductCode(101);
		product2.setProductCode(102);
		product3.setProductCode(103);
		
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		try {
			
			// Create a server socket object
			server = new ServerSocket(59091);
			System.out.println("The server has been connected");
			
			// Continually running to listen for request
			while (true) {
				
				System.out.println("Waiting for order...");
				
				// Accept client request and wrap into socket
				Socket socket = server.accept();
				
				ObjectInputStream instr = new ObjectInputStream(socket.getInputStream());
				OrderedItem orderedItem=null;
				
				try{
					orderedItem = (OrderedItem) instr.readObject();
				}catch(IOException e){
					e.printStackTrace();
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
				
				double totalPrice=0;
				
				//Respond to client's request
				PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
				output.println("Code \t Name \t Price \t Quantity \t Subtotal");
				for(Product p:products){
					if(p.getProductCode()==orderedItem.getProductCode()){
						totalPrice+=p.getPrice()*orderedItem.getQuantity();
						output.print(p.getProductCode()+"\t"+p.getName()+"\t"+p.getPrice()+"\t "+orderedItem.getQuantity()+"\t\t");
					}

				}
				
				//Output the result
				output.println(totalPrice);
				
				System.out.println("Complete request");
				
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
