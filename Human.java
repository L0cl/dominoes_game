import java.util.Scanner;
import java.lang.InterruptedException;
import java.util.Random;

/**
* Represent a human player. This player can choose its own moves.
* @see Player
*/
public class Human implements Player {
	protected String name;
	protected  DominoPile hand;
	protected int score;
	public static DominoPile pile;
	public static UsedDomino used;

	/**
	*	Constructs a player with a name, a hand and sharing the sme domino pile and used pile as other players.
	* The score is equals to 0 and shouldn't be updated until the end of the game.
	*/
	public Human(String name, DominoPile pile, UsedDomino used) {
		this.name = name;
		Human.pile = pile;
		Human.used = used;
		this.hand = Human.pile.obtainHand();
	}

	/**
	*	Getter for the name.
	*/
	public String getName() {
		return this.name;
	}

	/**
	*	Getter for the score.
	*/
	public int getScore() {
		return this.score;
	}

	/**
	*	Calculates the score of the player.
	*/
	public void updateScore() {
		this.score = this.hand.getTotal();
	}

	/**
	*	Events when a player have to pass his/her turn. It makes the player wait 3 seconds.
	*/
	private void passingTurn() {
		System.out.println("Can't play this turn... Wait for the next one.");
		try {
			for (int i = 0; i < 3; i++) {
				System.out.print(".");
				Thread.sleep(1000);
			}
			System.out.println();			/* Line break */
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	/**
	*	Makes the player play his turn.
	*/
	public void play() {
		System.out.println(this.name + "'dominos : \n" + this.hand);

		if (!this.canPlay()) {
			this.takeOneDomino();
			this.passingTurn();
		} else {
			Domino selected = this.hand.chooseDomino();
			while (!Human.used.add(selected) && !Human.used.isEmpty()) {
				selected = this.hand.chooseDomino();
			}
			this.hand.remove(selected);
		}
	}

	/**
	*	Checks if the domino pile is empty or null.
	*/
	public boolean canPlay() {
		if (Human.used.isEmpty()) {
			return true;
		} else {
			return this.hand.hasMatch(Human.used.getLast());
		}
	}

	/**
	*	Checks if the player win according that the his/her hand is empty.
	*/
	public boolean hasWon() {
		return this.hand.isEmpty();
	}

	/**
	*	Returns a domino from the pile, add it to the players hand and remove it from the pile
	*/
	public void takeOneDomino() {
		if (!Human.pile.isEmpty()) {
			Domino selection =  Human.pile.getRandomDomino();
			this.hand.add(selection);
			System.out.println("Taken : " + selection);
		}
	}

}
