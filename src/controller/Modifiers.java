package controller;

import enums.EStep;
import interfaces.ISaveLoadStateAble;
import model.CardFighting;
import model.CardFightingHazardKnowledge;
import model.CardPirate;
import utils.ArrayList;
import utils.ShutDown;

public enum Modifiers implements ISaveLoadStateAble {

	INSTANCE;

	private CardFightingHazardKnowledge cardFightingAgainst = null;
	private CardPirate cardPirateAgainst = null;
	private ArrayList<CardFighting> cardFightingHaveBeenResolvedThisRound = new ArrayList<CardFighting>();
	private EStep eStep = EStep.GREEN;
	private int additionalFightingCostLifeDraw = 1;

	private Modifiers() {

	}

	public void setCardFightingAgainst(CardFightingHazardKnowledge cardFighting) {
		this.cardFightingAgainst = cardFighting;
	}

	public CardFightingHazardKnowledge getCardFightingAgainst() {
		return this.cardFightingAgainst;
	}

	public void setCardPirateAgainst(CardPirate cardPirate) {
		this.cardPirateAgainst = cardPirate;
	}

	public CardPirate getCardPirateAgainst() {
		return this.cardPirateAgainst;
	}

	public void setAdditionalFightingCostLifeDraw(int additionalFightingCostLifeDraw) {
		this.additionalFightingCostLifeDraw = additionalFightingCostLifeDraw;
	}

	public int getAAdditionalFightingCostLifeDraw() {
		return this.additionalFightingCostLifeDraw;
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
			this.eStep = EStep.PIRATE;
			break;

		case PIRATE:
			ShutDown.INSTANCE.execute("Modifiers, PIRATES_SECOND, shutting down");
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
		this.cardPirateAgainst = null;
		this.additionalFightingCostLifeDraw = 1;

	}

}
