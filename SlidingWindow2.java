import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SlidingWindow2 {
    
    static int win;
    static int packets;
    final static double drop_rate = 0.2;
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
        List<Integer> globalNackList = new ArrayList<Integer>();
        
        while(ptr < packets) {
            
            List<Integer> sendBuffer = new ArrayList<Integer>();
            
            sendBuffer = prepareSend(ptr, Math.min(ptr + win, packets));
            
            System.out.println("window exhausted");
            System.out.println();
            
            // Assuming the last packet sent is the last in the sendBuffer, if not empty
            lastSent = sendBuffer.isEmpty() ? ptr : sendBuffer.get(sendBuffer.size() - 1);
            
            List<Integer> nack = transmit(sendBuffer, ptr, Math.min(ptr + win, packets));
            
            globalNackList.addAll(nack);
            
            ptr += win;
        }

        System.out.println("NACK List: " + globalNackList);
    }
    
    @SuppressWarnings("unchecked")
    private static List<Integer> prepareSend(int start, int end) {
        
        List<Integer> sendBuffer = new ArrayList<Integer>();
        
        for(int i = start; i < end; i++) {
            
            if(Drop.calcProb() > drop_rate) {
                sendBuffer.add(i);
            } else {
                dropCount++;
            }
        }
        
        return sendBuffer;    
    }

    private static List<Integer> transmit(List<Integer> sendBuffer, int start, int end) {
        List<Integer> nack = new ArrayList<>();
        
        for (int expectedPacket = start; expectedPacket < end; expectedPacket++) {
            if (!sendBuffer.contains(expectedPacket)) {
                nack.add(expectedPacket);
            }
        }
        
        System.out.println("Packets received in this window: " + sendBuffer);
        
        return nack;
    }
    
    // Stub for Drop.calcProb() as it's not defined in the original snippet
    // This should return a random double between 0.0 and 1.0
    private static class Drop {
        static double calcProb() {
            return Math.random();
        }
    }
}