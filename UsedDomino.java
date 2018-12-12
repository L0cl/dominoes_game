/**
* Represents a sequence of dominos where two consecutive dominos share the same value on one of their faces.
* It stores all used dominos in the domino game.
*/
public class UsedDomino {
	private Domino [] pile;
	private int last;

	/**
	*	Constructs an empty domino pile.
	*/
	public UsedDomino() {
		this.pile = new Domino[0];
		this.last = -1;
	}

	/**
	*	Returns the last number of the last domino in the pile.
	*/
	public int getLast() {
		return this.last;
	}

	/**
	*	Instantiates a String to display each domino of the pile side by side.
	*/
	@Override
	public String toString() {
		StringBuilder strb = new StringBuilder();
		for (int i = 0; i < this.pile.length; i++) {
			strb.append(this.pile[i]);
			strb.append(" ");
		}
		return strb.toString();
	}

	/**
	*	Checks if the domino pile contains the specified domino.
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
	*	Adds the specified domino in the pile if it matches with the last domino.
	* Returns true if it worked, false if it couldn't happen.
	* It affects the method int getLast().
	*/
	public boolean add(Domino next) {
		if (this.contains(next)) {
			return false;

		} else if (this.last == next.getLeft() || this.last == next.getRight() || this.last == -1) {
			Domino [] anotherArray = new Domino[this.pile.length + 1];
			System.arraycopy(this.pile, 0, anotherArray, 0, anotherArray.length - 1);

			if (this.last != next.getLeft())	next.flip();	// Check if a flip is needed
			anotherArray[this.pile.length] = next;
			this.pile = anotherArray;
			this.last = next.getRight();
			return true;

		} else {
			return false;
		}
	}

	/**
	* Checks if the domino pile is empty or null.
	*/
	public boolean isEmpty() {
		return (this.pile == null || this.pile.length == 0);
	}

}
