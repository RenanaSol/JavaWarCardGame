import java.security.SecureRandom;
import java.util.ArrayList;

// DeckOfCards class models a standard deck of 52 playing cards
public class DeckOfCards {
	private static final SecureRandom randomNumbers = new SecureRandom(); // random number generator
	private static final int NUMBER_OF_CARDS = 52; // standard deck size
	
	private ArrayList<Card> deck; // Card references
	private int currentCard; // index of next card to deal
	
	// constructor fills deck of Cards
	public DeckOfCards() {
		deck = new ArrayList<>();
		currentCard = 0; // Reset deal position
		
		String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight",
				"Nine", "Ten", "Jack", "Queen", "King"};
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		
		for (int i = 0; i < faces.length; i++) {
			String face = faces[i];
			
			for (int j = 0; j < suits.length; j++) {
				String suit = suits[j];
				
				Card newCard = new Card(face, suit);
				deck.add(newCard);
			}
		}
	}
	
	// shuffle deck of Cards
	public void shuffle() {
			
		for (int first = 0; first < deck.size(); first++) {
			
			// select a random number between 0 and 51
			int second = randomNumbers.nextInt(NUMBER_OF_CARDS);
			
			// swap current Card with randomly selected Card
			Card temp = deck.get(first);
			deck.set(first,deck.get(second));
			deck.set(second, temp);
		}
	}
	
	// Deals the next card from the deck
	public Card dealCard() {
		// determine whether Cards remain to be dealt
		if (currentCard < deck.size()) {
			return deck.get(currentCard++); // return current Card in array
		}
		return null; // return null to indicate that all Cards were dealt
	}
	
	// Clears the deck and resets the state
	public void createEmptyDeck() {
	    deck.clear();  // Initialize an empty deck
	    currentCard = 0;
	}
	
	// Adds a single card to the deck
	public void addCardToDeck(Card c) {
		deck.add(c);
	}
	
	// Returns the current index for dealing
	public int getCurrentCard() {
        return currentCard;
    }
	
	// Sets the index for dealing
	public void setCurrentCard(int currentCard) {
        this.currentCard = currentCard;
    }
}
