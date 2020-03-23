package model;

import utils.NumbersPair;

public class CardSlot {

	private CardFighting cardFighting = null;
	private boolean containsFreeCard = false;
	private NumbersPair coordinates = null;

	public CardSlot(NumbersPair coordinates) {
		this.coordinates = coordinates;
	}

	public void addCardFightingRelocate(CardFighting cardFighting) {
		this.cardFighting = cardFighting;
		this.cardFighting.getImageView().relocate(this.coordinates);
	}

	public CardFighting getCardFighting() {
		return this.cardFighting;
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
