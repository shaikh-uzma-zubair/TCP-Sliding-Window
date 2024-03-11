import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.SplittableRandom;

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
