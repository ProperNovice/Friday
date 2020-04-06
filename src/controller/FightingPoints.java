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
	private HashMap<Integer, Integer> mapFightingValues = new HashMap<Integer, Integer>();
	private HashMap<EStep, EStep> mapMinusOneStep = new HashMap<EStep, EStep>();
	private int playerFightingPointsNoDouble, playerFightingPointsWithDouble, hazardFightingPoints;
	private ArrayList<EAbility> listAbilitiesCurrent = new ArrayList<EAbility>(),
			listAbilitiesRelevant = new ArrayList<EAbility>();

	private FightingPoints() {

		this.textIndicator.setHeight(Credentials.INSTANCE.textHeight);
		this.textIndicator.relocateTopLeft(Credentials.INSTANCE.CoordinatesFightingIndicator);

		for (int counter = -5; counter <= 4; counter++)
			this.mapFightingValues.put(counter, 0);

		this.mapMinusOneStep.put(EStep.GREEN, EStep.GREEN);
		this.mapMinusOneStep.put(EStep.YELLOW, EStep.GREEN);
		this.mapMinusOneStep.put(EStep.RED, EStep.YELLOW);

		this.listAbilitiesRelevant.addLast(EAbility.DOUBLE_ONE);
		this.listAbilitiesRelevant.addLast(EAbility.PHASE_MINUS_ONE);
		this.listAbilitiesRelevant.addLast(EAbility.HIGHEST_CARD_EQUALS_ZERO);

		printMap();

	}

	public void setFightingPointsUpdateIndicator() {

		clearCredentials();

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
			this.mapFightingValues.put(points, this.mapFightingValues.get(points) + 1);

			if (!(sideKnowledge instanceof IAbilityAble))
				continue;

			IAbilityAble iAbilityAble = (IAbilityAble) sideKnowledge;
			EAbility eAbility = iAbilityAble.getEAbility();

			if (this.listAbilitiesRelevant.contains(eAbility))
				this.listAbilitiesCurrent.addLast(eAbility);

		}

		this.listAbilitiesCurrent.printList();

		setText();

	}

	private void clearCredentials() {

		clearMap();
		this.playerFightingPointsNoDouble = 0;
		this.playerFightingPointsWithDouble = 0;
		this.hazardFightingPoints = 0;
		this.listAbilitiesCurrent.clear();

	}

	private void printMap() {

		Logger.INSTANCE.log("/*");
		Logger.INSTANCE.log("fighting points");

		for (int counter = -5; counter <= 4; counter++)
			if (this.mapFightingValues.get(counter) > 0)
				Logger.INSTANCE.log(counter + "*" + this.mapFightingValues.get(counter) + " -> "
						+ counter * this.mapFightingValues.get(counter));

		Logger.INSTANCE.logNewLine("*/");

	}

	private void clearMap() {

		for (int counter = -5; counter <= 4; counter++)
			this.mapFightingValues.put(counter, 0);

	}

	private void setText() {

		calculatePlayerFightingPoints();
		calculateHazardFightingPoints();
		printMap();

		String text = "Fight: ";

		text += this.playerFightingPointsNoDouble;

		if (this.playerFightingPointsWithDouble > 0)
			text += "(d" + (this.playerFightingPointsNoDouble + this.playerFightingPointsWithDouble) + ")";

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

		if (!this.listAbilitiesCurrent.contains(EAbility.HIGHEST_CARD_EQUALS_ZERO))
			return;

		for (int counter = 4; counter >= 1; counter--) {

			if (this.mapFightingValues.get(counter) == 0)
				continue;

			this.mapFightingValues.put(counter, this.mapFightingValues.get(counter) - 1);
			this.mapFightingValues.put(0, this.mapFightingValues.get(0) + 1);
			break;

		}

	}

	private void calculateFightingCardPoints() {

		for (int counter = -5; counter <= 4; counter++)
			this.playerFightingPointsNoDouble += counter * this.mapFightingValues.get(counter);

	}

	private void calculateDoubleAbility() {

		int cardsDouble = 0;

		for (EAbility eAbility : this.listAbilitiesCurrent)
			if (eAbility.equals(EAbility.DOUBLE_ONE))
				cardsDouble++;

		if (cardsDouble == 0)
			return;

		for (int counter = 4; counter >= 1; counter--) {

			int cardsDoubleToPlay = Math.min(cardsDouble, this.mapFightingValues.get(counter));
			cardsDouble -= cardsDoubleToPlay;

			for (int q = 1; q <= cardsDoubleToPlay; q++)
				this.playerFightingPointsWithDouble += counter;

		}

	}

	private void calculateHazardFightingPoints() {

		EStep eStep = Modifiers.INSTANCE.getEStep();

		if (this.listAbilitiesCurrent.contains(EAbility.PHASE_MINUS_ONE))
			eStep = this.mapMinusOneStep.get(eStep);

		CardFighting cardFighting = Modifiers.INSTANCE.getCardFightingAgainst();
		CardFightingHazardKnowledge cardFightingHazardKnowledge = (CardFightingHazardKnowledge) cardFighting;
		SideHazard sideHazard = cardFightingHazardKnowledge.getSideHazard();

		this.hazardFightingPoints = sideHazard.getEHazardValue().getStepValue(eStep);

	}

}
