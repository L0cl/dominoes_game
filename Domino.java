/**
* Represents a domino with two faces.
*/
public class Domino {
	private int left;
	private int right;

	/**
	* Constructs a domino with the specified faces.
	*/
	public Domino(int left, int right) {
		this.left = left;
		this.right = right;
	}

	/**
	* Returns the domino as a String with the value of the two faces.
	*/
	@Override
	public String toString() {
		String str = "[" + this.left + " | " + this.right + "]";
		return str;
	}

	/**
	* Check if the domino has the same values as the specified domino.
	*/
	public boolean equals(Domino d) {
		return (this.left == d.left && this.right == d.right) || (this.left == d.right && this.right == d.left);
	}

	/**
	* Switch the domino's faces.
	*/
	public void flip() {
		int aux = this.left;
		this.left = this.right;
		this.right = aux;
	}

	/**
	* Getter for the left face.
	*/
	public int getLeft() {
		return this.left;
	}

	/**
	* Getter for the right face.
	*/
	public int getRight() {
		return this.right;
	}

	public int score() {
		return (this.left + this.right);
	}

	/**
	* Check if the domino matches the specified number.
	*/
	public boolean match(int number) {
		return (this.right == number || this.left == number);
	}

}
