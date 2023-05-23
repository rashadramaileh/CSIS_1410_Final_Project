package blackJack;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Rules extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Rules dialog = new Rules();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Rules() {
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextPane txtpnRulesOfThis = new JTextPane();
			txtpnRulesOfThis.setText(
					"Basic Blackjack Rules:\r\n"
					+ "\r\n"
					+ "1. The goal of blackjack is to beat the dealer's hand without going over 21.\r\n"
					
					+ "2. Face cards are worth 10. Aces are worth 1 or 11, whichever makes a better hand.\r\n"
					
					+ "3. Each player starts with two cards, one of the dealer's cards is hidden until the end.\r\n"
					
					+ "4. To 'Hit' is to ask for another card. To 'Stand' is to hold your total and end your turn.\r\n"
					
					+ "5. If you go over 21 you bust, and the dealer wins regardless of the dealer's hand.\r\n"
					
					+ "6. If you are dealt 21 from the start (Ace & 10), you got a blackjack.\r\n"
					
					+ "7. Blackjack usually means you win 1.5 the amount of your bet. Depends on the casino.\r\n"
					
					+ "8. Dealer will hit until his/her cards total 17 or higher.\r\n"
					
					+ "9. Doubling is like a hit, only the bet is doubled and you only get one more card.\r\n"
					
					+ "10. Split can be done when you have two of the same card - the pair is split into two hands.\r\n"
					
					+ "11. Splitting also doubles the bet, because each new hand is worth the original bet.\r\n"
					
					+ "12. You can only double/split on the first move, or first move of a hand created by a split.\r\n"
					
					+ "13. You cannot play on two aces after they are split.\r\n"
					
					+ "14. You can double on a hand resulting from a split, tripling or quadrupling you bet.");
			
			contentPanel.add(txtpnRulesOfThis);
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
		}
	}

}
