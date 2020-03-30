package model;

import utils.NumbersPair;

public class CardSlot {

	private CardFighting cardFighting = null;
	private boolean containsFreeCard = false;
	private NumbersPair coordinatesNumbersPair = null;

	public CardSlot(NumbersPair coordinates) {
		this.coordinatesNumbersPair = coordinates;
	}

	public void addCardFightingRelocate(CardFighting cardFighting) {
		this.cardFighting = cardFighting;
		this.cardFighting.getImageView().relocate(this.coordinatesNumbersPair);
	}

	public CardFighting getCardFighting() {
		return this.cardFighting;
	}

	public boolean containsCardFighting() {
		return this.cardFighting != null;
	}

	public CardFighting removeCardFighting() {

		CardFighting cardFighting = this.cardFighting;
		this.cardFighting = null;
		return cardFighting;

	}

	public void setContainsFreeCard(boolean containsFreeCard) {
		this.containsFreeCard = containsFreeCard;
	}

	public boolean containsFreeCard() {
		return this.containsFreeCard;
	}

}
