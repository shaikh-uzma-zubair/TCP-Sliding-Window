import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		
		ServerSocket s = new ServerSocket(9999);
		Socket ss = s.accept();
		System.out.println("Connected");
		
		DataInputStream din = new DataInputStream(ss.getInputStream());
		DataOutputStream dout = new DataOutputStream(ss.getOutputStream());
		//String ack = "ACK:";
		
		while(true) {
			
			int recv = din.readInt();
			System.out.println("Client sent: " + recv);
			
			dout.writeInt(recv);
			
			if(recv == 999) {
				break;
			}
		}
		
		ss.close();

	}

}
