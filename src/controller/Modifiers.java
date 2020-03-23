package controller;

import model.Card;

public enum Modifiers {

	INSTANCE;

	private Card cardFightingAgainst = null;

	private Modifiers() {

	}

	public void setCardFightingAgainst(Card card) {
		this.cardFightingAgainst = card;
	}

	public Card getCardFightingAgainst() {
		return this.cardFightingAgainst;
	}

}
