package controller;

import enums.EAbility;
import enums.EStep;
import interfaces.IAbilityAble;
import model.CardFighting;
import model.CardFightingHazardKnowledge;
import model.CardSlot;
import model.SideHazard;
import model.SideKnowledge;
import utils.ArrayList;
import utils.HashMap;
import utils.Logger;
import utils.TextIndicator;

public enum FightingPoints {

	INSTANCE;

	private TextIndicator textIndicator = new TextIndicator();
	private HashMap<Integer, ArrayList<CardFighting>> mapFightingValues = new HashMap<>();
	private HashMap<EStep, EStep> mapMinusOneStep = new HashMap<EStep, EStep>();
	private int playerFightingPointsNoDouble, playerFightingPointsWithDouble, hazardFightingPoints;
	private ArrayList<CardFighting> cardsDouble = new ArrayList<CardFighting>();
	private boolean containsPhaseMinusOne, containsHighestCardEqualsZero;

	private FightingPoints() {

		this.textIndicator.setHeight(Credentials.INSTANCE.textHeight);
		this.textIndicator.relocateTopLeft(Credentials.INSTANCE.CoordinatesTextIndicators);

		for (int counter = -5; counter <= 4; counter++)
			this.mapFightingValues.put(counter, new ArrayList<CardFighting>());

		this.mapMinusOneStep.put(EStep.GREEN, EStep.GREEN);
		this.mapMinusOneStep.put(EStep.YELLOW, EStep.GREEN);
		this.mapMinusOneStep.put(EStep.RED, EStep.YELLOW);

	}

	public void setFightingPointsUpdateIndicator() {

		this.textIndicator.setVisible(true);

		clearCredentials();
		setMapFightingValues();
		calculatePlayerFightingPoints();
		calculateHazardFightingPoints();
		printMap();
		setText();

	}

	private void clearCredentials() {

		clearMap();
		this.playerFightingPointsNoDouble = 0;
		this.playerFightingPointsWithDouble = 0;
		this.hazardFightingPoints = 0;
		this.cardsDouble.clear();
		this.containsPhaseMinusOne = false;
		this.containsHighestCardEqualsZero = false;

	}

	private void setMapFightingValues() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (cardFighting.equals(Modifiers.INSTANCE.getCardFightingAgainst()))
				continue;

			if (cardFighting.getImageView().isFlippedBack())
				continue;

			SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();

			int points = sideKnowledge.getFightingValue();
			this.mapFightingValues.get(points).addLast(cardFighting);

			if (!(sideKnowledge instanceof IAbilityAble))
				continue;

			IAbilityAble iAbilityAble = (IAbilityAble) sideKnowledge;
			EAbility eAbility = iAbilityAble.getEAbility();

			switch (eAbility) {

			case DOUBLE_ONE:
				this.cardsDouble.addLast(cardFighting);
				break;

			case HIGHEST_CARD_EQUALS_ZERO:
				this.containsHighestCardEqualsZero = true;
				break;

			case PHASE_MINUS_ONE:
				this.containsPhaseMinusOne = true;
				break;

			default:
				break;

			}

		}

	}

	private void printMap() {

		Logger.INSTANCE.log("/*");
		Logger.INSTANCE.log("fighting points");

		for (int counter = -5; counter <= 4; counter++)
			if (this.mapFightingValues.get(counter).size() > 0)
				Logger.INSTANCE.log(counter + "*" + this.mapFightingValues.get(counter).size() + " -> "
						+ counter * this.mapFightingValues.get(counter).size());

		Logger.INSTANCE.logNewLine("*/");

	}

	private void clearMap() {

		for (int counter = -5; counter <= 4; counter++)
			this.mapFightingValues.get(counter).clear();

	}

	private void setText() {

		String text = "";

		text += this.playerFightingPointsNoDouble;

		if (this.playerFightingPointsWithDouble > this.playerFightingPointsNoDouble)
			text += "(d" + this.playerFightingPointsWithDouble + ")";

		text += "/";
		text += this.hazardFightingPoints;

		this.textIndicator.setText(text);

	}

	private void calculatePlayerFightingPoints() {

		calculateHighestCardZeroAbility();
		calculateFightingCardPoints();
		calculateDoubleAbility();

	}

	private void calculateHighestCardZeroAbility() {

		if (!this.containsHighestCardEqualsZero)
			return;

		for (int counter = 4; counter >= 1; counter--) {

			if (this.mapFightingValues.get(counter).isEmpty())
				continue;

			CardFighting cardFighting = this.mapFightingValues.get(counter).removeFirst();
			this.mapFightingValues.get(0).addLast(cardFighting);
			break;

		}

	}

	private void calculateFightingCardPoints() {

		for (int counter = -5; counter <= 4; counter++)
			this.playerFightingPointsNoDouble += counter * this.mapFightingValues.get(counter).size();

	}

	private void calculateDoubleAbility() {

		this.playerFightingPointsWithDouble = this.playerFightingPointsNoDouble;

		int cardsDoubleToResolve = this.cardsDouble.size();

		if (cardsDoubleToResolve == 0)
			return;

		for (int counter = 4; counter >= 1; counter--) {

			for (CardFighting cardFighting : this.mapFightingValues.get(counter)) {

				if (this.cardsDouble.contains(cardFighting))
					continue;

				this.playerFightingPointsWithDouble += counter;
				cardsDoubleToResolve--;

				if (cardsDoubleToResolve == 0)
					break;

			}

			if (cardsDoubleToResolve == 0)
				break;

		}

	}

	private void calculateHazardFightingPoints() {

		EStep eStep = Modifiers.INSTANCE.getEStep();

		if (this.containsPhaseMinusOne)
			eStep = this.mapMinusOneStep.get(eStep);

		CardFightingHazardKnowledge cardFightingHazardKnowledge = Modifiers.INSTANCE.getCardFightingAgainst();
		SideHazard sideHazard = cardFightingHazardKnowledge.getSideHazard();

		this.hazardFightingPoints = sideHazard.getEHazardValue().getStepValue(eStep);

	}

	public int getPlayerFightingPointsNoDouble() {
		return this.playerFightingPointsNoDouble;
	}

	public int getPlayerFightingPointsWithDouble() {
		return this.playerFightingPointsWithDouble;
	}

	public int getHazardFightingPoints() {
		return this.hazardFightingPoints;
	}

	public void setVisibleIndicatorFalse() {
		this.textIndicator.setVisible(false);
	}

}
