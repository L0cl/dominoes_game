import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	/**
	*	Makes the user enter an integer that in in the interval.
	* The specified message is used to ask the user about the number.
	*/
	public static int askIntBtw(int inf, int sup, String msg) {
		if (inf > sup) {
			System.out.println("Impossible interval.");
			return 1;
		}
		Scanner scan = new Scanner(System.in);
		int answer = -1;
		while (answer == -1) {
			System.out.print(msg);
			try {
				answer = scan.nextInt();
				if (answer < inf || answer > sup) {
					System.out.println("You should enter a number btw " + inf + " and " + sup);
					answer = -1;
				}
			} catch (InputMismatchException e) {
				System.out.println("Not a number.");
				answer = -1;
				scan.nextLine();
			}
		}
		return answer;
	}

	public static void main(String [] args) {
		int mode = Main.askIntBtw(1, 4, "Number of players : ");
		Game g = new Game(mode);
		g.play();
		g.displayLeaderboard();

	}
}
