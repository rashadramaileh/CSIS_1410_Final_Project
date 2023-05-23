package blackJack;
/**
 * Contains each of the cards rank value. 
 * 
 * @author Mike Awada & Rashad Ramaieh
 *
 */
public enum Rank {

	A(11), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10), J(10), Q(10),
	K(10);
	private int value;

	/**
	 * Sets the ranks value. 
	 * @param value the value to set
	 */
	private Rank(int value) {
		this.value = value;
	}
	
	/**
	 * Gets the ranks value.
	 * @return value current value of card
	 */
	public int getValue() {
		return value;
	}

}