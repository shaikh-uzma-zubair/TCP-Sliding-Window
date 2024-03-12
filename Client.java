import java.io.DataInputStream; // Importing DataInputStream class for reading primitive data types from input stream
import java.io.DataOutputStream; // Importing DataOutputStream class for writing primitive data types to output stream
import java.io.IOException; // Importing IOException class for handling input/output exceptions
import java.net.Socket; // Importing Socket class for creating client sockets to communicate with server sockets
import java.util.SplittableRandom; // Importing SplittableRandom class for generating random numbers

// Class definition for the Client.
public class Client {

	public static void main(String[] args) throws IOException {


		Socket s = new Socket("localhost", 9999);
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		DataInputStream din = new DataInputStream(s.getInputStream());
		
		  for (int i=0; i<5; i++) {
			  
			  dout.writeInt(i);
			  int ack = din.readInt();
			  System.out.println("Server acked: " + ack);
		  }
		  
		dout.writeInt(999);
		s.close();
	}

}
