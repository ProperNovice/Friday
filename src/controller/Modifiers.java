package controller;

import enums.EStep;
import interfaces.ISaveLoadStateAble;
import model.CardFighting;
import utils.ArrayList;
import utils.ShutDown;

public enum Modifiers implements ISaveLoadStateAble {

	INSTANCE;

	private CardFighting cardFightingAgainst = null;
	private ArrayList<CardFighting> cardFightingHaveBeenResolvedThisRound = new ArrayList<CardFighting>();
	private EStep eStep = EStep.GREEN;

	private Modifiers() {

	}

	public void setCardFightingAgainst(CardFighting cardFighting) {
		this.cardFightingAgainst = cardFighting;
	}

	public CardFighting getCardFightingAgainst() {
		return this.cardFightingAgainst;
	}

	public ArrayList<CardFighting> getCardFightingHaveBeenResolvedThisRound() {
		return this.cardFightingHaveBeenResolvedThisRound;
	}

	public void eStepAdvance() {

		switch (this.eStep) {

		case GREEN:
			this.eStep = EStep.YELLOW;
			break;

		case YELLOW:
			this.eStep = EStep.RED;
			break;

		case RED:
			this.eStep = EStep.PIRATES;
			break;

		case PIRATES:
			ShutDown.INSTANCE.execute("Modifiers, eStepAdvance, PIRATES, you shouldn't be here");
			break;

		}

	}

	public EStep getEStep() {
		return this.eStep;
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
		this.cardFightingHaveBeenResolvedThisRound.clear();
		this.cardFightingAgainst = null;
	}

}
