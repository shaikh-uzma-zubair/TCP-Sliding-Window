import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlidingWindow2 {
	
	static int win;
	static int packets;
	final static double drop_rate = 0.01;
	final static int retransmit_win = 20;
	static int dropCount = 0;

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("enter window size: ");
		win = sc.nextInt();
		
		System.out.print("enter total no. of packets: ");
		packets = sc.nextInt();
		
		slideWindow(win, packets);
		
	}

	@SuppressWarnings("unchecked")
	public static void slideWindow(int win, int packets) {
		
		int ptr = 0, lastSent = 0;
		List globalNackList = new ArrayList<Integer>();
		
		while(ptr <= packets) {
			
			List sendBuffer = new ArrayList<Integer>();
			
			sendBuffer = prepareSend(ptr, ptr+win);
			
			System.out.println("window exhausted");
			System.out.println();
			
			lastSent = (int) sendBuffer.get(sendBuffer.size() - 1);
			
			List nack = transmit(sendBuffer, lastSent);
			
			globalNackList.addAll(nack);
			
			ptr = ptr + win;
			
			if (ptr % retransmit_win == 0) {
				
				List retransmitNack = retransmit(globalNackList);
				globalNackList.clear();
				globalNackList.addAll(retransmitNack);
			}
			
		}
	}
	
	@SuppressWarnings("unchecked")
	private static List prepareSend(int start, int end) {
		
		List sendBuffer = new ArrayList<Integer>();
		
		for(int i = start; i<end; i++) {
			
			if(Drop.calcProb() > drop_rate) {
				sendBuffer.add(i);
			
			} else {
				dropCount++;
				
				if(dropCount >= 10) {
					sendBuffer.add(i);
				}
			}
			
		}
		
		return sendBuffer;	
	}

	private static List retransmit(List globalNackList) {
		
		int size = globalNackList.size();
		List retransmitBuffer = new ArrayList<Integer>();
		int lastSent = 0;
		
		List retransmitNack = new ArrayList<Integer>();
		
		
		if(size < win) {
			
			retransmitBuffer = prepareSend(0, size);
			
			lastSent = (int) retransmitBuffer.get(retransmitBuffer.size() - 1);
			retransmitNack = transmit(retransmitBuffer, lastSent);
			
		} else {
			
			int ptr = 0;
			
			while(ptr < size) {
				
				retransmitBuffer = prepareSend(ptr, ptr+win);
				lastSent = (int) retransmitBuffer.get(retransmitBuffer.size() - 1);
				List nack = transmit(retransmitBuffer, lastSent);
				
				retransmitNack.addAll(nack);
				
				ptr = ptr+win;
				
				if(size - ptr < win) {
					
					int local_win = size - ptr;
					retransmitBuffer = prepareSend(ptr, ptr+local_win);
					
					lastSent = (int) retransmitBuffer.get(retransmitBuffer.size() - 1);
					nack = transmit(retransmitBuffer, lastSent);
					retransmitNack.addAll(nack);
				}
			}
		}
		
		return retransmitNack;
		
	}

	private static List transmit(List sendBuffer, int lastSent) {
		
		List nack = new ArrayList<Integer>();
		
		int temp = lastSent;
		
		System.out.println("batch received: ");
		
		for (int i = 0; i<sendBuffer.size(); i++) {
			System.out.println(sendBuffer.get(i));
		}
		
		for(int i = 0; i < sendBuffer.size(); i++) {
			
			if((int) sendBuffer.get(i) == temp + 1) {
				
				temp = (int) sendBuffer.get(i);
				
			} else {
				
				nack.add(i);
			}
		}
		
		return nack;
	
	}

	/*public static int transmit(List sendBuffer, int lastAck) {
		
		//int ack = -1;
		
		List nack = new ArrayList<Integer>();
		
		int temp = lastAck;
		
		
		System.out.println("batch received: ");
		for (int i = 0; i<sendBuffer.size(); i++) {
			System.out.println(sendBuffer.get(i));
		}
		
		for (int i = 0; i<sendBuffer.size(); i++) {
			
			
		}
		
		if(sendBuffer.size() == win) {
			ack = (int) sendBuffer.get(win - 1);
		} 
		else if(sendBuffer.size() < win) {
			
			int temp = lastAck;
			
			for (int i = 0; i<sendBuffer.size(); i++) {
				
				if((int) sendBuffer.get(i) == temp + 1) {
					temp = (int) sendBuffer.get(i);
					continue;
				}
			}
			
			ack = temp;
		}
		
		return ack;
		*/
		
}



/*

total pkt = 10K
window = 1K
retransmit after = 2K
 
 
 
window size = 1k

sender sent 1 3 4 6 7 

9 10 11 13 14

nack = 8

NACK 2 5

send nack as [2 5] to sender

when 10000 packets exhausted sender will retrasmit [2 5]


/*for(int i = ptr; i < ptr+win; i++) {
				
				if(Drop.calcProb() > drop_rate) {
					sendBuffer.add(i);
				
				} else {
					dropCount++;
					//System.out.println("now drop count: " + dropCount);
					
					if(dropCount >= 10) {
						sendBuffer.add(i);
					}
				}
				
			}*/

