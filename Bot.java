/**
* Automated player that seek the first domino that can be used.
* @see Human
*/
public class Bot extends Human {
    /**
	*	Constructs a bot player sharing the same domino pile and used dominos as other player.
    * Its name is Bot.
	*/
    public Bot(DominoPile pile, UsedDomino used) {
        super("Bot", pile, used);
    }

    /**
	*	Makes the bot play its turn.
	*/
	public void play() {
        System.out.println(this.name + "'dominos : \n" + this.hand);
        if (!this.canPlay()) {
            this.takeOneDomino();
        } else {
            Domino selection = this.hand.findHighestMatchFor(Bot.used.getLast());
            Bot.used.add(selection);
            this.hand.remove(selection);
        }
    }

}
