import static java.lang.Math.random; // Importing random method from Math class
import java.util.Scanner; // Importing Scanner class to read input from the user
import java.util.Random; // Importing Random class to generate random numbers

public class Example {

    public static int generateFrame(int winSize) {

        Random random = new Random();
        int i, noOfGeneratedFrame;

        noOfGeneratedFrame = random.nextInt(500) % winSize;

        if (noOfGeneratedFrame == 0) {
            return winSize;
        } else {
            return noOfGeneratedFrame;
        }
    }

    public static int generateAck(int noOfSent) {

        Random random = new Random();
        int noOfAckFrame;

        noOfAckFrame = (int) (random.nextInt(500) % noOfSent);

        return noOfAckFrame;
    }

	public static void main(String[] args) {
        int noOfFrame, winSize, startByte = 0, endByte = 0, noOfAck = 0,
                noOfSent = 0;

        @SuppressWarnings("resource")
        Scanner scn = new Scanner(System.in); // Creating Scanner object to read user input

        // Prompting user to enter the total number of frames
        System.out.println("Enter the total number of frames: ");
        noOfFrame = scn.nextInt();

        // Prompting user to enter the window size
        System.out.println("Enter the window size: ");
        winSize = scn.nextInt();

        int dueFrame = noOfFrame;

        while (dueFrame >= 0) {

            noOfSent = generateFrame(winSize);

            endByte += noOfSent;

            if (endByte > noOfFrame) {
                endByte = noOfFrame;
            }

            for (int i = startByte + 1; i <= endByte; i++) {
                System.out.println("Sending frame " + i);
            }

            noOfAck = generateAck(noOfSent);

            startByte += noOfAck;

            if (startByte > noOfFrame) {
                startByte = noOfFrame;
            }

            System.out.println("Acknowledgement for the frame upto " + startByte);

            dueFrame -= noOfAck;

            endByte = startByte;
        }
        // Conclusion message for the Sliding Window Protocol
        System.out.println("\n The Sliding Window Protocol concludes here.");
    }

}
