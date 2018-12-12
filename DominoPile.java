import java.util.Random;

/**
*	Represent a domino pile. It is used for the shared domino pile or the hand of a player.
*/
public class DominoPile {
	private Domino [] pile;

	/**
	*	Constructs an empty domino pile.
	*/
	public DominoPile() {
		this.pile = null;
	}

	/**
	*	Constructs a domino pile with the specifed array.
	*/
	public DominoPile(Domino [] pile) {
		this.pile = pile;
	}

	/**
	*	Returns the number of dominos in the pile.
	*/
	public int getLength() {
		return this.pile.length;
	}

	/**
	*	Removes everything in the pile and fill it the all possible dominos.
	*/
	public void fill() {
		Domino [] array = new Domino[28];
		int i = 0;
		for (int left = 0; left <= 6; left++) {
			for (int right = left; right <= 6; right++) {
				array[i++] = new Domino(left, right);
			}
		}
		this.pile = array;
	}

	/**
	*	Returns a String to display all dominos of the pile indexed in column.
	*/
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < this.pile.length; i++) {
			strb.append("\t(" + i + ") ");
			strb.append(this.pile[i]);
			strb.append("\n");
		}
		return strb.toString();
	}

	/**
	*	Adds the specified domino in the pile if it does not contain it.
	*/
	public void add(Domino d) {
		if (!this.contains(d)) {
	        Domino [] anotherArray = new Domino[this.pile.length + 1];
	        System.arraycopy(this.pile, 0, anotherArray, 0, anotherArray.length - 1);
	        anotherArray[anotherArray.length - 1] = d;		// Placing the new domino at the end of the new array.
	        this.pile = anotherArray;
		}
    }

	/**
	*	Removes the specified domino from the pile if it contains this former.
	* Does nothing if it doesn't.
	*/
	public void remove(Domino target) {
		if (this.contains(target)) {
			Domino [] anotherArray = new Domino[(this.pile.length - 1 >= 0)
												? (this.pile.length - 1) : 0];		// Length of the array shouldn't be negative.
			int k = 0;
			for (int i = 0; i < this.pile.length; i++) {
				if (!this.pile[i].equals(target)) {
					anotherArray[k++] = this.pile[i];
				}
			}
			this.pile = anotherArray;
		}
	}

	/**
	*	Checks if the domino pile ocntains the specified domino.
	*/
	public boolean contains(Domino target) {
		for (int i = 0; i < this.pile.length; i++) {
			if (this.pile[i].equals(target)) {
				return true;
			}
		}
		return false;
	}

	/**
	*	Returns a random domino in the pile if it's not empty. It does nothing if it's empty.
	*/
	public Domino getRandomDomino() {
		Random intGenerator = new Random();
		int rank;
		Domino save;
		if (!this.isEmpty()) {
			rank = intGenerator.nextInt(this.pile.length);
			save = this.pile[rank];
			this.remove(save);
			return save;
		}
		System.out.println("No domino left.");
		return null;
	}

	/**
	*	Returns a DominoPile with 7 dominos randomly chosen.
	* Returns null if there is not enough dominos.
	*/
	public DominoPile obtainHand() {
		if (this.pile != null && this.pile.length < 7) {
			System.out.println("Not enough domino.");
			return null;
		} else {
			Domino [] array = new Domino[7];
			for (int i = 0; i < 7; i++) {
				array[i] = this.getRandomDomino();
			}
			DominoPile hand = new DominoPile();
			hand.pile = array;
			return hand;
		}
	}

	/**
	*	Makes the user choose a domino.
	*/
	public Domino chooseDomino() {
		return this.pile[Main.askIntBtw(0, this.pile.length - 1, "Your selection : ")];
	}

	/**
	*	Checks if there is at least one domino that matches with the specified number.
	*/
	public boolean hasMatch(int number) {
		for (Domino d : this.pile) {
			if (d.match(number))	return true;
		}
		return false;
	}

	/**
	*	Checks if the domino pile is empty or null.
	*/
	public boolean isEmpty() {
		return (this.pile == null) || (this.pile.length == 0);
	}

	/**
	*	Returns the sum of each dots of each domino in the pile.
	*/
	public int getTotal() {
		int score = 0;
		for (Domino d : this.pile) {
			score += d.score();
		}
		return score;
	}

	/**
	*	Returns a domino from the pile that match with the number.
	* Returns null if there is none.
	*/
	public Domino findMatchFor(int last) {
		for (Domino test : this.pile) {
			if (test.match(last)) {
				return test;
			}
		}
		System.out.println("No matching domino with " + last);
		return null;
	}

	/**
	*	Returns a domino from the pile with the highest score that match with the number.
	* Returns null if there is none.
	*/
	public Domino findHighestMatchFor(int last) {
		Domino max = null;
		for (Domino test : this.pile) {
			if ( test.match(last) && (max == null || test.score() > max.score()) ) {
				max = test;
			}
		}
		if (max == null)	System.out.println("No matching domino with " + last);
		System.out.println("rrrrrrrrrrrrrrrreturning " + max);
		return max;
	}

}
