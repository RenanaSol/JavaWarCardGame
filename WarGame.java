import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class WarGame {
	private static final int NUMBER_OF_CARDS = 52;
	private static final int MAX_ROUNDS = 100; // Limit to avoid infinite games

	// Handles a "War" situation when two cards have the same value
	public static void handleWar(Queue<Card> player1Deck, Queue<Card> player2Deck, ArrayList<Card> warPile) {
		// If a player can't continue the war (less than 3 cards), the game ends
		if (player1Deck.size() < 3 || player2Deck.size() < 3) {
			String message = "";
			if (player1Deck.size() > player2Deck.size()) {
				message = "Player 2 does not have enough cards for war.\nPlayer 1 wins the game!";
			} else if (player2Deck.size() > player1Deck.size()) {
				message = "Player 1 does not have enough cards for war.\nPlayer 2 wins the game!";
			}
			JOptionPane.showMessageDialog(null, message);
			System.exit(0);
		}

		// In this case- both players have enough cards, so extract the cards
		ArrayList<Card> player1WarCards = new ArrayList<>();
		ArrayList<Card> player2WarCards = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			player1WarCards.add(player1Deck.poll());
			player2WarCards.add(player2Deck.poll());
		}

		// Add war cards to the war pile
		warPile.addAll(player1WarCards);
		warPile.addAll(player2WarCards);

		// Compare the third card
		Card player1FinalCard = player1WarCards.get(player1WarCards.size() - 1);
		Card player2FinalCard = player2WarCards.get(player2WarCards.size() - 1);

		// Compare and assign cards to the winner
		String warResult;
		if (player1FinalCard.getValueOfCard() > player2FinalCard.getValueOfCard()) {
			player1Deck.addAll(warPile); // Player 1 wins the war
			warResult = "Player 1 wins the war and takes all the cards!";
		} else if (player2FinalCard.getValueOfCard() > player1FinalCard.getValueOfCard()) {
			player2Deck.addAll(warPile); // Player 2 wins the war
			warResult = "Player 2 wins the war and takes all the cards!";
		} else {
			// Tie again → Another war
			warResult = "It's a tie! Another war begins!";
			JOptionPane.showMessageDialog(null, warResult);
			handleWar(player1Deck, player2Deck, warPile); // Recursively handle war again
			return; // Exit function since war continues
		}

		// Show war result
		String message = 
				"WAR!\n\n" +
						"Player 1's cards: " + player1WarCards + "\n" +
						"Player 2's cards: " + player2WarCards + "\n\n" +
						"Player 1's deciding card: " + player1FinalCard + "\n" +
						"Player 2's deciding card: " + player2FinalCard + "\n\n" +
						warResult + "\n\n" +
						"Player 1 now have: " + player1Deck.size() + " cards." + "\n" +
						"Player 2 now have: " + player2Deck.size() + " cards.";

		JOptionPane.showMessageDialog(null, message.toString());
	}

	// Main method to run the game
	public static void main(String[] args) { 
		DeckOfCards mainDeck = new DeckOfCards();
		mainDeck.shuffle();

		Queue<Card> player1Deck = new LinkedList<>();
		Queue<Card> player2Deck = new LinkedList<>();

		// Deal cards alternately to both players
		for (int i = 0; i < NUMBER_OF_CARDS; i++) {
			Card dealtCard = mainDeck.dealCard();  // Draw a card from the shuffled deck
			if (i % 2 == 0) {
				player1Deck.add(dealtCard);  // Even index → Player 1
			} else {
				player2Deck.add(dealtCard);  // Odd index → Player 2
			}
		}

		int roundCounter = 0;

		while (!player1Deck.isEmpty() && !player2Deck.isEmpty() && roundCounter < MAX_ROUNDS) {

			roundCounter++; // Increase round count

			Card player1Card = player1Deck.poll();
			Card player2Card = player2Deck.poll();

			// Get values to compare
			int value1 = player1Card.getValueOfCard();
			int value2 = player2Card.getValueOfCard();

			String roundResult;

			if (value1 > value2) { // Player 1 wins the round
				player1Deck.add(player1Card);
				player1Deck.add(player2Card);
				roundResult = "Player 1 wins this round!";
			} else if (value1 < value2) {   // Player 2 wins the round
				player2Deck.add(player1Card);
				player2Deck.add(player2Card);
				roundResult = "Player 2 wins this round!";
			} else {
				// This is a "War" situation
				ArrayList<Card> warPile = new ArrayList<>();
				warPile.add(player1Card);
				warPile.add(player2Card);

				JOptionPane.showMessageDialog(null,
						"Both players drew: " + value1 + "\nIt's a war!");
				handleWar(player1Deck, player2Deck, warPile);
				continue;
			}	

			String message = "Player 1 drew: " + player1Card +
					"\nPlayer 2 drew: " + player2Card +
					"\n\n" + roundResult + "\n\n" +
					"Player 1 now have: " + player1Deck.size() + " cards." + "\n" +
					"Player 2 now have: " + player2Deck.size() + " cards.";

			JOptionPane.showMessageDialog(null, message);

		}

		// Game result after max rounds or empty deck
		String finalMessage;
		if (player1Deck.size() > player2Deck.size()) {
			finalMessage = "Game limit reached! Player 1 has more cards and wins!";
		} else if (player2Deck.size() > player1Deck.size()) {
			finalMessage = "Game limit reached! Player 2 has more cards and wins!";
		} else {
			finalMessage = "Game limit reached! It's a draw!";
		}
		JOptionPane.showMessageDialog(null, finalMessage);
	} 
}
