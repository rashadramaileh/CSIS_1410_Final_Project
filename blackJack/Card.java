package blackJack;

import javax.swing.*;

/**
 * This class creates a card. A card has a suit, rank, and a picture value to be displayed.
 * 
 * @author Mike Awada & Rashad Ramaileh
 *
 */
@SuppressWarnings("serial")
public class Card extends JLabel {

	private final Suit suit;
	private final Rank rank;
	private ImageIcon icon;

	/**
	 * Constructor creates a card with a rank, suit, and empty picture value.
	 * @param suit 
	 * @param rank
	 * @param picture
	 */
	Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.icon = new ImageIcon(getClass().getResource("Images" + this.toString() + ".png"));

	}
	
	/**
	 * Returns the rank of a card.
	 * @return cards rank
	 */
	public Rank getRank() {
		return rank;
	}
	

	public ImageIcon getIcon() {
		return icon;
	}
	
	/**
	 * Returns the suit of a card.
	 * @return cards suit
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * Returns the value of a cards rank.
	 * @return cards rank value.
	 */
	public int getValue() {
		return this.getRank().getValue();
	}
	
	@Override
	public String toString() {
		return "/" + rank + suit;
	}
}
