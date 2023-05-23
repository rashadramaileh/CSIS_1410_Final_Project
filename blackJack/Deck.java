
package blackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This class creates a Deck made up of multiple cards. 
 * 
 * @author Mike Awada & Rashad Ramaileh
 *
 */
public class Deck {
	public List<Card> deck = new ArrayList<Card>();
	public List<Card> deck2 = new ArrayList<Card>();
	public List<Card> deck3 = new ArrayList<Card>();


	/**
	 * Creates a deck containing 52 cards. Also shuffles the order of the cards at the end.
	 */
	public Deck() {
		for (Rank rank : Rank.values())
		 {
			for (Suit suit : Suit.values())
				deck.add(new Card(rank, suit));
			for (Suit suit : Suit.values())
				deck2.add(new Card(rank, suit));
		}
		deck.addAll(deck2);
		Collections.shuffle(deck);
	}	
	
	/**
	 * First adds a fresh deck to the current deck then shuffles it.
	 * Then draws a hand of cards while removing the respective card from the deck. 
	 * @param i card to be drawn
	 * @return current card drawn
	 */
	public Card getCard(int i) {
		if (deck.size() == 1 ) {
			deck.addAll(deck2);
			Collections.shuffle(deck);
		}
		Card draw = deck.get(i);
		deck.remove(i);
		return draw;
	}
}
