/**
* Reprensent any players of the game.
*	@see Human
*/
public interface Player {
	/**
	*	Getter for the name.
	*/
	public String getName();

	/**
	*	Getter for the score.
	*/
	public int getScore();

	/**
	*	Makes the player play his turn.
	*/
	public void play();

	/**
	*	Checks if the domino pile is empty or null.
	*/
	public boolean canPlay();

	/**
	*	Checks if the player win according that the his/her hand is empty.
	*/
	public boolean hasWon();

	/**
	*	Returns a domino from the pile, add it to the players hand and remove it from the pile
	*/
	public void takeOneDomino();

	/**
	*	Calculates the score of the player.
	*/
	public void updateScore();
}
