package blackJack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Dimension;

/**
 * Creates the GUI for the BlackJack Game.
 * 
 * @author Mike Awada & Rashad Ramaileh
 *
 */
@SuppressWarnings("serial")
public class BlackJackGUI extends JFrame {

	private int hitIndex = 0;
	private static int playerBet;
	private static int intBalance;

	private JLabel txtBalance;
	private JLabel lblBet;
	private static JLabel lblBalance;
	final static JLabel playerCard1 = new JLabel();
	final static JLabel playerCard2 = new JLabel();
	final static JLabel playerCard3 = new JLabel();
	final static JLabel playerCard4 = new JLabel();
	final static JLabel playerCard5 = new JLabel();
	final static JLabel playerCard6 = new JLabel();
	final static JLabel dealerCard1 = new JLabel();
	final static JLabel dealerCard2 = new JLabel();
	final static JLabel dealerCard3 = new JLabel();
	final static JLabel dealerCard4 = new JLabel();
	final static JLabel dealerCard5 = new JLabel();
	final static JLabel dealerCard6 = new JLabel();
	private static final JLabel playerScore = new JLabel();
	private static final JLabel dealerScore = new JLabel();
	private static JLabel lblWinner;

	private static JButton btnStay;
	private JButton btnBet;
	private static JButton btnHit;
	private static JButton btnNewGame;

	final ImageIcon blueBack = new ImageIcon(getClass().getResource("Images/blue_Back.png"));

	static Deck deck = new Deck();
	static Hand playerHand = new Hand();
	static Hand dealerHand = new Hand();

	private JPanel contentPane;
	private JPanel controlPanel;
	private JPanel centerPanel;
	private JPanel menuPanel;
	private JButton btn$1;
	private JButton btn$5;
	private JButton btn$10;
	private JButton btn$20;
	private JButton btn$50;
	private JButton btn$100;

	/**
	 * Launches the Blackjack game.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackJackGUI frame = new BlackJackGUI();
					frame.setTitle("BlackJack");
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the Blackjack game frame. 
	 */
	public BlackJackGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		controlPanel = createControlPanel();
		contentPane.add(controlPanel, BorderLayout.WEST);
		startGame();
		
		centerPanel = createCenterPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		
		lblWinner = new JLabel("");
		lblWinner.setBackground(Color.GREEN);
		lblWinner.setBounds(139, 321, 500, 100);
		lblWinner.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinner.setForeground(Color.YELLOW);
		lblWinner.setFont(new Font("Tahoma", Font.BOLD, 64));
		centerPanel.add(lblWinner);
		
		JLabel lblBlackjackTitle = createBlackjackTitle();
		centerPanel.add(lblBlackjackTitle);
		menuPanel = createMenuPanel();
		contentPane.add(menuPanel, BorderLayout.NORTH);
	}

	private JLabel createBlackjackTitle() {
		JLabel lblBlackjackTitle = new JLabel("BLACKJACK");
		lblBlackjackTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlackjackTitle.setForeground(new Color(255, 255, 255));
		lblBlackjackTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
		lblBlackjackTitle.setBounds(300, 0, 249, 80);
		return lblBlackjackTitle;
	}

	/**
	 * Creates a menu bar containing the rules and an exit button.
	 * @return menu bar
	 */
	private JPanel createMenuPanel() {
		JPanel menuPanel = new JPanel();

		menuPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(false);
		menuPanel.add(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmExit);

		JMenu mnNewMenu_1 = new JMenu("Rules");
		menuBar.add(mnNewMenu_1);
		JMenuItem mntmNewMenuItem = new JMenuItem("Rules");
		mnNewMenu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				Rules rules = new Rules();
				rules.show();
			}
		});

		return menuPanel;
	}
	
	/**
	 * Creates the center panel which contains the main game board.
	 * @return the center panel
	 */
	private JPanel createCenterPanel() {
		centerPanel = new JPanel();
		centerPanel.setBackground(new Color(34, 139, 34));
		centerPanel.setLayout(null);
		JLabel lblPlayerScore = new JLabel("Player Score");
		lblPlayerScore.setSize(141, 46);
		lblPlayerScore.setLocation(644, 499);
		lblPlayerScore.setBorder(new EmptyBorder(0, 0, 0, 10));
		lblPlayerScore.setForeground(new Color(255, 255, 255));
		lblPlayerScore.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		lblPlayerScore.setHorizontalAlignment(SwingConstants.RIGHT);
		centerPanel.add(lblPlayerScore);
		playerScore.setLocation(700, 533);
		playerScore.setSize(40, 25);
		playerScore.setFont(new Font("Segoe UI Black", Font.BOLD, 18));

		playerScore.setText("0");
		playerScore.setHorizontalAlignment(SwingConstants.CENTER);
		playerScore.setForeground(new Color(255, 255, 255));
		playerScore.setBorder(null);
		playerScore.setBackground(new Color(255, 255, 255));
		centerPanel.add(playerScore);
		dealerScore.setSize(51, 25);
		dealerScore.setLocation(97, 196);
		dealerScore.setFont(new Font("Segoe UI Black", Font.BOLD, 18));

		dealerScore.setText("0");
		dealerScore.setHorizontalAlignment(SwingConstants.CENTER);
		dealerScore.setForeground(new Color(255, 255, 255));
		dealerScore.setBorder(null);
		dealerScore.setBackground(new Color(255, 255, 255));
		centerPanel.add(dealerScore);

		JLabel lblDealerScore = new JLabel("Dealer Score");
		lblDealerScore.setSize(141, 30);
		lblDealerScore.setLocation(55, 170);
		lblDealerScore.setBorder(new EmptyBorder(0, 10, 0, 0));
		lblDealerScore.setForeground(new Color(255, 255, 255));
		lblDealerScore.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		centerPanel.add(lblDealerScore);
		
		btnStay = new JButton("Stay");
		btnStay.setEnabled(false);
		btnStay.setLocation(312, 676);
		btnStay.setSize(100, 35);
		btnStay.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		btnStay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dealerCard1.setIcon(dealerHand.hand.get(0).getIcon());
				dealerCard1.setVisible(true);
				while (dealerHand.value() <= 16) {
					dealerHand.addCard(deck.getCard(0));
				}
				staySwitch();
				dealerScore.setText(String.valueOf(dealerHand.value()));

				if (dealerHand.value() > 21) {
					playerWins();
				} else if (playerHand.value() > dealerHand.value() && playerHand.value() <= 21) {
					playerWins();
				} else if (dealerHand.value() > playerHand.value() && dealerHand.value() <= 21) {
					playerLoses();
				} else if (dealerHand.value() == playerHand.value()) {
					lblWinner.setForeground(Color.WHITE);
					lblWinner.setText("It is a tie!!!!");
					btnNewGame.setEnabled(true);
				}
				lblWinner.setVisible(true);
			}
		});
		centerPanel.add(btnStay);
		
		btnHit = new JButton("Hit");
		btnHit.setEnabled(false);
		btnHit.setLocation(422, 676);
		btnHit.setSize(100, 35);
		btnHit.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (hitIndex) {

				case 0:
					playerHand.addCard(deck.getCard(0));
					playerCard3.setIcon(playerHand.hand.get(2).getIcon());
					playerCard3.setVisible(true);
					break;
				case 1:
					playerHand.addCard(deck.getCard(0));
					playerCard4.setIcon(playerHand.hand.get(3).getIcon());
					playerCard4.setVisible(true);
					break;
				case 2:
					playerHand.addCard(deck.getCard(0));
					playerCard5.setIcon(playerHand.hand.get(4).getIcon());
					playerCard5.setVisible(true);
					break;
				case 3:
					playerHand.addCard(deck.getCard(0));
					playerCard6.setIcon(playerHand.hand.get(5).getIcon());
					playerCard6.setVisible(true);
					break;
				}
				
				if (playerHand.value() > 21) {
					playerLoses();
					dealerCard1.setIcon(dealerHand.hand.get(0).getIcon());
					dealerScore.setText(String.valueOf(dealerHand.value()));
					dealerCard1.setVisible(true);
				}

				if (playerHand.value() == 21) {
					while (dealerHand.value() <= 16) {
						dealerHand.addCard(deck.getCard(0));
					}

					dealerCard1.setIcon(dealerHand.hand.get(0).getIcon());
					dealerCard1.setVisible(true);

					staySwitch();

					if (playerHand.value() == dealerHand.value()) {
						lblWinner.setForeground(Color.WHITE);

						lblWinner.setText("It is a Tie!!!!");
					} else
						playerWins();

					dealerScore.setText(String.valueOf(dealerHand.value()));
				}
				
				playerScore.setText(String.valueOf(playerHand.value()));
				++hitIndex;
			}
		});
		centerPanel.add(btnHit);
		cardPlaceHolders();
		return centerPanel;
	}

	/**
	 * Creates the control panel. The control panel contains the players balance as well 
	 * as how much to bet on the next hand. Includes the new game button.
	 * @return control panel
	 */
	private JPanel createControlPanel() {
		JPanel controlPanel = new JPanel();
		controlPanel.setPreferredSize(new Dimension(130, 10));
		controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		controlPanel.setLayout(new GridLayout(0, 1, 0, 5));

		txtBalance = new JLabel();
		txtBalance.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		txtBalance.setForeground(Color.BLACK);
		txtBalance.setVerticalAlignment(SwingConstants.BOTTOM);
		txtBalance.setHorizontalAlignment(SwingConstants.CENTER);
		txtBalance.setText("Your Balance");
		controlPanel.add(txtBalance);

		lblBalance = new JLabel("$" + readFromFile());
		lblBalance.setBorder(new EmptyBorder(10, 0, 0, 0));
		lblBalance.setForeground(Color.BLACK);
		lblBalance.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		lblBalance.setVerticalAlignment(SwingConstants.TOP);
		lblBalance.setHorizontalAlignment(SwingConstants.CENTER);
		controlPanel.add(lblBalance);

		createTokens(controlPanel);
		
				btnBet = new JButton("Place Bet");
				btnBet.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
				btnBet.setForeground(Color.RED);
				btnBet.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						lblBet.setEnabled(false);
						btnBet.setEnabled(false);
						btnNewGame.setEnabled(false);
						btnStay.setEnabled(true);
						btnHit.setEnabled(true);
						btn$1.setVisible(false);
						btn$5.setVisible(false);
						btn$10.setVisible(false);
						btn$20.setVisible(false);
						btn$50.setVisible(false);
						btn$100.setVisible(false);
						startGame();
					}
				});
				controlPanel.add(btnBet);

		lblBet = new JLabel("");
		lblBet.setBorder(new EmptyBorder(0, 0, 10, 0));
		controlPanel.add(lblBet);

		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		btnNewGame.setBackground(Color.LIGHT_GRAY);
		btnNewGame.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerHand.hand.clear();
				dealerHand.hand.clear();
				btn$1.setVisible(true);
				btn$5.setVisible(true);
				btn$10.setVisible(true);
				btn$20.setVisible(true);
				btn$50.setVisible(true);
				btn$100.setVisible(true);

				lblBalance.setText("$" + readFromFile());
				playerBet = 0;
				
				lblBet.setEnabled(true);
				btnBet.setEnabled(true);
				lblWinner.setVisible(false);
				cardPlaceHolders();
			}
		});
		controlPanel.add(btnNewGame);

		return controlPanel;
	}
	/**
	 * Creates the bet amount buttons for the control panel. 
	 * @param controlPanel panel to have buttons added to
	 */
	private void createTokens(JPanel controlPanel) {
		btn$1 = new JButton("$10");
		btn$1.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btn$1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				playerBet += 10;
				
				btnBet.setText("Bet \n$" + playerBet);
			}
		});
		controlPanel.add(btn$1);

		btn$5 = new JButton("$50");
		btn$5.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btn$5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet += 50;
				
				btnBet.setText("Bet \n$" + playerBet);
			}
		});
		controlPanel.add(btn$5);

		btn$10 = new JButton("$100");
		btn$10.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btn$10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet += 100;
				btnBet.setText("Bet \n$" + playerBet);
			}
		});
		controlPanel.add(btn$10);

		btn$20 = new JButton("$200");
		btn$20.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btn$20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet += 200;
				btnBet.setText("Bet \n$" + playerBet);
			}
		});
		controlPanel.add(btn$20);

		btn$50 = new JButton("$500");
		btn$50.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btn$50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet += 500;
				btnBet.setText("Bet \n$" + playerBet);
			}
		});
		controlPanel.add(btn$50);

		btn$100 = new JButton("$1000");
		btn$100.setFont(new Font("Segoe UI Black", Font.PLAIN, 12));
		btn$100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerBet += 1000;
				btnBet.setText("Bet $" + playerBet);
			}
		});
		controlPanel.add(btn$100);
	}

	/**
	 * Displays a message if the player loses. Also subtracts the amount that
	 * was bet from the players current balance.
	 */
	private static void playerLoses() {
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
		lblWinner.setForeground(Color.RED);

		lblWinner.setVisible(true);
		lblWinner.setText("Dealer Wins !!!");
		intBalance = readFromFile();
		intBalance -= playerBet;
		lblBalance.setText("$" + intBalance);
		playerBet = 0;
		writeToFile(intBalance);
		lblBalance.setText("$" + readFromFile());
		btnNewGame.setEnabled(true);
		return;
	}
	
	/**
	 * Displays a message if the player wins. Also adds the amount that was bet
	 * to the players current balance.
	 */
	private static void playerWins() {
		btnHit.setEnabled(false);
		btnStay.setEnabled(false);
		lblWinner.setForeground(Color.BLUE);
		lblWinner.setVisible(true);
		lblWinner.setText("You Win !!!");
		intBalance = readFromFile();
		intBalance += playerBet;
		lblBalance.setText("$" + intBalance);
		playerBet = 0;
		writeToFile(intBalance);
		lblBalance.setText("$" + readFromFile());
		btnNewGame.setEnabled(true);
		return;
	}

	/**
	 * Place holders for the cards for both the player and the dealer.
	 */
	public void cardPlaceHolders() {
		playerCard1.setBounds(250, 430, 150, 220);
		playerCard1.setVisible(false);
		playerCard2.setBounds(275, 430, 150, 220);
		playerCard2.setVisible(false);
		playerCard3.setBounds(300, 430, 150, 220);
		playerCard3.setVisible(false);
		playerCard4.setBounds(325, 430, 150, 220);
		playerCard4.setVisible(false);
		playerCard5.setBounds(350, 430, 150, 220);
		playerCard5.setVisible(false);
		playerCard6.setBounds(375, 430, 150, 220);
		playerCard6.setVisible(false);
		centerPanel.add(playerCard6);
		centerPanel.add(playerCard5);
		centerPanel.add(playerCard4);
		centerPanel.add(playerCard3);
		centerPanel.add(playerCard2);
		centerPanel.add(playerCard1);
		dealerCard1.setVisible(false);
		dealerCard1.setBounds(250, 100, 150, 220);
		dealerCard2.setVisible(false);
		dealerCard2.setBounds(275, 100, 150, 220);
		dealerCard3.setVisible(false);
		dealerCard3.setBounds(300, 100, 150, 220);
		dealerCard4.setVisible(false);
		dealerCard4.setBounds(325, 100, 150, 220);
		dealerCard5.setVisible(false);
		dealerCard5.setBounds(350, 100, 150, 220);
		dealerCard6.setVisible(false);
		dealerCard6.setBounds(375, 100, 150, 220);
		centerPanel.add(dealerCard6);
		centerPanel.add(dealerCard5);
		centerPanel.add(dealerCard4);
		centerPanel.add(dealerCard3);
		centerPanel.add(dealerCard2);
		centerPanel.add(dealerCard1);
	}
	
	/**
	 * Starts the BlackJack game. When the game is started hands are cleared and new hands are dealt to each
	 * the player and the dealer. The cards are then displayed on the screen. If Blackjack is dealt right off
	 * the start, the game will end displaying the results of who won.
	 */
	public void startGame() {
		hitIndex = 0;
		playerHand.hand.clear();
		dealerHand.hand.clear();
		playerHand.hand.add(deck.getCard(0));
		dealerHand.hand.add(deck.getCard(0));
		playerHand.hand.add(deck.getCard(0));
		dealerHand.hand.add(deck.getCard(0));

		playerCard1.setIcon(playerHand.hand.get(0).getIcon());
		playerCard1.setVisible(true);
		playerCard2.setIcon(playerHand.hand.get(1).getIcon());
		playerCard2.setVisible(true);

		dealerCard1.setIcon(blueBack);
		dealerCard1.setVisible(true);
		dealerCard2.setIcon(dealerHand.hand.get(1).getIcon());
		dealerCard2.setVisible(true);

		playerScore.setText(String.valueOf(playerHand.value()));
		dealerScore.setText(String.valueOf(dealerHand.hand.get(1).getValue()));

		if (dealerHand.value() == 21 && playerHand.value() == 21) {
			dealerCard1.setIcon(dealerHand.hand.get(0).getIcon());
			lblWinner.setText("Tie");
			dealerCard1.setVisible(true);
			btnNewGame.setEnabled(true);
			dealerScore.setText(String.valueOf(dealerHand.value()));

		}

		else if (dealerHand.value() == 21 && playerHand.value() != 21) {
			playerLoses();
			dealerCard1.setIcon(dealerHand.hand.get(0).getIcon());
			dealerCard1.setVisible(true);
			dealerScore.setText(String.valueOf(dealerHand.value()));

		}

		else if (playerHand.value() == 21 && dealerHand.value() != 21) {
			playerWins();
			dealerCard1.setIcon(dealerHand.hand.get(0).getIcon());
			dealerCard1.setVisible(true);
			dealerScore.setText(String.valueOf(dealerHand.value()));
		}
	}
	
	/**
	 * Displays more cards if the dealer or player decides to hit.
	 */
	public void staySwitch() {
		switch (dealerHand.hand.size()) {
		case 3:
			dealerCard3.setIcon(dealerHand.hand.get(2).getIcon());
			dealerCard3.setVisible(true);
			break;
		case 4:
			dealerCard3.setIcon(dealerHand.hand.get(2).getIcon());
			dealerCard3.setVisible(true);
			dealerCard4.setIcon(dealerHand.hand.get(3).getIcon());
			dealerCard4.setVisible(true);
			break;
		case 5:
			dealerCard3.setIcon(dealerHand.hand.get(2).getIcon());
			dealerCard3.setVisible(true);
			dealerCard4.setIcon(dealerHand.hand.get(3).getIcon());
			dealerCard4.setVisible(true);
			dealerCard5.setIcon(dealerHand.hand.get(4).getIcon());
			dealerCard5.setVisible(true);
			break;
		case 6:
			dealerCard3.setIcon(dealerHand.hand.get(2).getIcon());
			dealerCard3.setVisible(true);
			dealerCard4.setIcon(dealerHand.hand.get(3).getIcon());
			dealerCard4.setVisible(true);
			dealerCard5.setIcon(dealerHand.hand.get(4).getIcon());
			dealerCard5.setVisible(true);
			dealerCard6.setIcon(dealerHand.hand.get(5).getIcon());
			dealerCard6.setVisible(true);
			break;
		}
	}

	/**
	 * Reads the players balance from a .txt file.
	 * @return players current balance
	 */
	static public int readFromFile() {
		int readBalance = 0;
		String fileName = "balance.txt";
		try (Scanner reader = new Scanner(new File(fileName))) {
			while (reader.hasNextInt())
				readBalance = reader.nextInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return readBalance;
	}
	
	/**
	 * Updates the players balance by writing to a .txt file.
	 * @param newBalance players new balance.
	 */
	static public void writeToFile(int newBalance) {
		String fileName = "balance.txt";
		try (PrintWriter writer = new PrintWriter(new File(fileName))) {
			writer.println(newBalance);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
