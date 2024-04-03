// Import the static method random from the Math class, allowing it to be used without prefixing it with Math.
import static java.lang.Math.random;

// Import the Scanner class from the java.util package to read input from various sources (like user input from the console).
import java.util.Scanner;

// Import the Random class from the java.util package to generate random numbers of various data types.
import java.util.Random;


public class Drop {

	public static void main(String[] args) {
		
		int count = 0;
		
		for (int i=0; i<15; i++) {
			
			int p = calcProb();
			
			if(p > 5) {
				
				System.out.println(i);
				
			} else {
				
				System.out.println("drop: " + i);
				count ++;
				
				if(count >= 3) {
					System.out.println(i);

				}
				
				System.out.println("count: " + count);
			}
		}

	}

	public static double calcProb() {
		
		Random random = new Random();
		
		return random.nextDouble();
	}
}
