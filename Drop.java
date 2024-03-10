import static java.lang.Math.random;
import java.util.Scanner;
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

	private static int calcProb() {
		
		Random random = new Random();
		
		return random.nextInt(10 - 0 + 1) + 0;
	}

}
