// Card class represent a playing card.
public class Card {
	private String face; // face of card ("Ace", "Deuce", ...)
	private String suit; // suit of card ("Hearts", "Diamonds", ...)

	// Two-argument constructor initializes card's face and suit
	public Card(String cardFace, String cardSuit) {
		this.face = cardFace; // initialize face of card
		this.suit = cardSuit; // initialize suit of card
	}
	
	// Returns the numeric value of the card
	// Returns 0 for unrecognized face values (should not occur in a valid deck)
	public int getValueOfCard() {
		switch (face) {
		case "Ace": return 14;  // Ace is usually the highest in War
		case "King": return 13;
		case "Queen": return 12;
		case "Jack": return 11;
		case "Ten": return 10;
		case "Nine": return 9;
		case "Eight": return 8;
		case "Seven": return 7;
		case "Six": return 6;
		case "Five": return 5;
		case "Four": return 4;
		case "Three": return 3;
		case "Deuce": return 2; // "Deuce" is another name for 2
		default: return 0; // Should never happen with a valid deck
		}
	}
	
	// Returns String representation of Card's face
	public String getFace() {
		return this.face;
	}
	
	// Returns String representation of Card's suit
	public String getSuit() {
		return this.suit;
	}
	
	// Sets the face value of the card
	public void setFace(String face) {
		this.face = face;
	}
	
	// Sets the suit of the card
	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	// Returns String representation of Card
	public String toString() {
		return face + " of " + suit;
	}
}