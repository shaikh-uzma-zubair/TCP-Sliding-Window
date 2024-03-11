import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlidingWindow {
	
	static int win;
	static int frames;

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		System.out.print("enter window size: ");
		win = sc.nextInt();
		
		System.out.print("enter total no. of frames: ");
		frames = sc.nextInt();
		
		slideWindow(win, frames);
		
	}

	public static void slideWindow(int win, int frames) {
		
		int ptr = 0, lastAck = 0, dropCount = 0;
		
		while(ptr <= frames) {
			
			List sendBuffer = new ArrayList<Integer>();
			
			for(int i=ptr; i<ptr+win; i++) {
				
				//System.out.println("now sending: " + i);
				
				int p = Drop.calcProb();
				
				if(p <= 8) {
					sendBuffer.add(i);
				
				} else {
					dropCount ++;
					System.out.println("now drop count: " + dropCount);
					
					if(dropCount >= 3) {
						sendBuffer.add(i);
					}
				}
				
				if(i == frames) {
					System.out.println("done");
					break;
				}
				
			}
			
			System.out.println("window exhausted");
			System.out.println();
			
			int currAck = transmit(sendBuffer, lastAck);
			System.out.println("Acked: " + currAck);
			
			lastAck = currAck;
			
			System.out.println();
			
			//checkAcks();
			
			//ptr = ptr + win;
			ptr = lastAck + 1;
			System.out.println("now ptr is: " + ptr);
		
		}
	}

	public static int transmit(List sendBuffer, int lastAck) {
		
		int ack = -1;
		
		System.out.println("batch received: ");
		for (int i = 0; i<sendBuffer.size(); i++) {
			System.out.println(sendBuffer.get(i));
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
		
	}

}
