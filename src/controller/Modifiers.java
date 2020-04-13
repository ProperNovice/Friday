package controller;

import card.CardFighting;
import card.CardFightingHazardKnowledge;
import card.CardFightingPirateProxy;
import card.CardPirate;
import card.SideKnowledge;
import enums.EStep;
import interfaces.ISaveLoadStateAble;
import utils.ArrayList;
import utils.ShutDown;

public enum Modifiers implements ISaveLoadStateAble {

	INSTANCE;

	private CardFighting cardFightingAgainst = null;
	private CardPirate cardPirateAgainst = null;
	private ArrayList<CardFighting> cardFightingHaveBeenResolvedThisRound = new ArrayList<CardFighting>();
	private EStep eStep = EStep.GREEN;
	private int additionalFightingCostLifeDraw = 1;
	private int agingCardsStartingAmount;
	private CardFightingPirateProxy cardFightingPirateProxy = null;

	private Modifiers() {

		this.cardFightingPirateProxy = new CardFightingPirateProxy("Ship Back", new SideKnowledge(-1, -1));
		this.cardFightingPirateProxy.getImageView().setVisible(false);

	}

	public void setPirateProxyFightingAgainst() {
		this.cardFightingAgainst = this.cardFightingPirateProxy;
		this.cardFightingPirateProxy.getImageView().setVisible(true);
	}

	public CardFightingPirateProxy getCardFightingPirateProxy() {
		return this.cardFightingPirateProxy;
	}

	public void setAgingCardsStartingAmount(int amount) {
		this.agingCardsStartingAmount = amount;
	}

	public int getAgingCardsStartingAmount() {
		return this.agingCardsStartingAmount;
	}

	public void setCardFightingAgainst(CardFightingHazardKnowledge cardFighting) {
		this.cardFightingAgainst = cardFighting;
	}

	public CardFighting getCardFightingAgainst() {
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

	public int getAdditionalFightingCostLifeDraw() {
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

		loadState();
		this.eStep = EStep.GREEN;

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
		this.cardFightingPirateProxy.getImageView().setVisible(false);

	}

}
