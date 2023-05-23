package blackJack;

import java.util.*;

/**
 * Creates a hand using cards from a deck.
 * 
 * @author Mike Awada & Rashad Ramaileh
 *
 */
public class Hand {
	public List<Card> hand = new ArrayList<Card>();
	
	/**
	 * Adds a card to a hand.
	 * @param c card to be added
	 */
	public void addCard(Card c) {
		hand.add(c);
	}
	
	 /**
     * Dictates whether or not the ace value should be used as 1 or 11.
     * @return value of the ace card.
     */
	public int value() {
		int handValue = 0;
		int count = 0;

		for (Card card : hand) {
			if (card.getRank().getValue() == 11) {
				count++;
			}
			handValue += card.getRank().getValue();
		}

		if (handValue > 21 && count == 1) {
			handValue -= 10;
		}

		if (handValue > 21 && count == 2) {
			handValue -= 10;
			if (handValue > 21) {
				handValue -= 10;
			}
		}
		return handValue;
	}
}