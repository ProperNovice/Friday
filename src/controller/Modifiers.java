package controller;

import interfaces.ISaveLoadStateAble;
import model.Card;
import model.CardFighting;
import utils.ArrayList;

public enum Modifiers implements ISaveLoadStateAble {

	INSTANCE;

	private Card cardFightingAgainst = null;
	private ArrayList<CardFighting> cardFightingHaveBeenResolvedThisRound = new ArrayList<CardFighting>();

	private Modifiers() {

	}

	public void setCardFightingAgainst(Card card) {
		this.cardFightingAgainst = card;
	}

	public Card getCardFightingAgainst() {
		return this.cardFightingAgainst;
	}

	public ArrayList<CardFighting> getCardFightingHaveBeenResolvedThisRound() {
		return this.cardFightingHaveBeenResolvedThisRound;
	}

	@Override
	public void saveGameStart() {

	}

	@Override
	public void loadGameStart() {

	}

	@Override
	public void saveState() {

	}

	@Override
	public void loadState() {

	}

}
