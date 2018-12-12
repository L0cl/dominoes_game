import java.util.Scanner;

/**
*	Represents the domino game. Can have at least 2 players and 4 players maximul.
* It has two mode : Player vs Bot mode and Multiplayer mode.
* If the player is alone, the Player vs Bot is launch.
*/
public class Game {
	private DominoPile pile;
	private UsedDomino used;
	private Player [] players;

	/**
	* Constructs a domino game with the specified number of players and instantiates all the dominos.
	*/
	public Game(int nbPlayers) {
		this.pile = new DominoPile();
		this.pile.fill();
		System.out.println(this.pile);				// Diplaying all the dominos instantiated.
		this.used = new UsedDomino();

		if (nbPlayers == 1) {							// Human vs Bot mode
			System.out.println("Human vs Bot");
			this.players = new Player[nbPlayers + 1];
			this.players[0] = new Human(Game.askName("Enter a name : ", null), pile, used);
			this.players[1] = new Bot(pile, used);
		} else {										// Multiplayer mode
			this.players = new Player[nbPlayers];
			String [] names = new String[players.length];

			// Asking names
			for (int i = 0; i < names.length; i++) {		// Aksing names first make it easier to check if a name has been used
				names[i] = Game.askName("Enter a name : ", names);
			}
			// Create human players
			for (int i = 0; i < players.length; i++) {
				players[i] = new Human(names [i], pile, used);
			}
		}
	}

	/**
	* Asking a String that is not in the specified array in command line.
	* with the specified message until it's correct.
	*/
	private static String askName(String msg, String [] forbidden) {
		Scanner scan = new Scanner(System.in);
		String answer = "";
		while (answer == "") {
			System.out.print(msg);
			answer = scan.nextLine().replaceAll(" ", "");
			if (Game.arrayContains(forbidden, answer)) {
				System.out.println("The name " + answer + " is already taken. Please, choose another one.");
				answer = "";
			}
		}
		return answer;
	}

	/**
	*	Checks if the specified element is in the array.
	*/
	private static boolean arrayContains(String [] array, String element) {
		if (array == null)	return false;
		for (String str : array) {
			if (str != null && str.equals(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	*	Launches the domino set.
	*/
	public void play() {
		int bloquedPlayers = 0;
		int i = 0;
		do {
			if (i >= players.length)	i = 0;
			System.out.println("\n\n=============================================\n");
			System.out.println("Domino pile : " + this.pile.getLength() + " dominos left.");
			System.out.println("Used dominos : \n"+ this.used + "\n");
			this.players[i].play();
		} while (!this.players[i++].hasWon() && !this.allBlocked());

		//Moment of transition before the conclusion of the game
		try {
			System.out.println("The game is over. Wait to see the results.");
			for (int j = 0; j < 3; j++) {
				System.out.print(".");		// Progress bar
				Thread.sleep(1000);
			}
			System.out.println();			// Line break
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	/**
	*	Checks if there is at least one player that have a possibility to play
	*/
	private boolean allBlocked() {
		for (int i = 0; i < this.players.length; i++) {
			if (players[i].canPlay())	return false;
		}
		System.out.println("You are all blocked !");
		return true;
	}

	/**
	*	Sorts all the players by their score using a bubble sort variant
	*/
	private void sortPlayers() {
		for (Player p : this.players) {
			p.updateScore();
		}
		Player save = null;
		for (int i = 0; i < this.players.length; i++) {
			for (int j = i; j < this.players.length; j++) {
				if (this.players[i].getScore() > this.players[j].getScore()) {
					save = this.players[i];
					this.players[i] = this.players[j];
					this.players[j] = save;
				}
			}
		}
	}

	/**
	*	Displays the ranking of each players with their score
	*/
	public void displayLeaderboard() {
		System.out.println("==========================================================");
		System.out.println("\t\t\tLeaderboard");
		System.out.println("==========================================================\n");
		this.sortPlayers();
		int rank = 1;
		for (Player p : this.players) {
			System.out.println("\t\t" + rank++ + "- " + p.getName() + " -- score: " + p.getScore());
		}
	}

}
