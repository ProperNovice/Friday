package controller;

import card.CardFighting;
import card.CardFightingAging;
import card.CardFightingHazardKnowledge;
import card.CardPirate;
import card.CardSlot;
import card.SideHazard;
import card.SideKnowledge;
import card.SidePirate;
import card.SidePirateAbility;
import enums.EAbility;
import enums.EStep;
import gameState.AGameState;
import gameState.ChooseDifficultyLevel;
import gameState.DrawHazardCards;
import gameState.DrawNextEncounter;
import gameState.DrawPirate;
import gameState.EndGame;
import gameState.EndGameWin;
import gameState.EndTurn;
import gameState.FightWon;
import gameState.HandleFightLost;
import gameState.HandleHazardCardsDrawnOne;
import gameState.HandleHazardCardsDrawnTwo;
import gameState.StartGame;
import interfaces.IAbilityAble;
import utils.ArrayList;
import utils.HashMap;
import utils.Logger;
import utils.TextIndicator;

public enum FightingPoints {

	INSTANCE;

	private TextIndicator textIndicator = new TextIndicator();
	private HashMap<Integer, ArrayList<CardFighting>> mapFightingValues = new HashMap<>();
	private HashMap<EStep, EStep> mapMinusOneStep = new HashMap<EStep, EStep>();
	private int playerFightingPointsNoDouble, playerFightingPointsWithDouble, encounterFightingPoints;
	private ArrayList<CardFighting> cardsDouble = new ArrayList<CardFighting>();
	private boolean containsPhaseMinusOne, containsHighestCardEqualsZero;
	private ArrayList<Class<? extends AGameState>> updateIndicatorGameStates = new ArrayList<Class<? extends AGameState>>();

	private FightingPoints() {

		updateIndicatorAddGameStates();

		this.textIndicator.setHeight(Credentials.INSTANCE.textHeight);
		this.textIndicator.relocateTopLeft(Credentials.INSTANCE.CoordinatesTextIndicators);

		for (int counter = -5; counter <= 4; counter++)
			this.mapFightingValues.put(counter, new ArrayList<CardFighting>());

		this.mapMinusOneStep.put(EStep.GREEN, EStep.GREEN);
		this.mapMinusOneStep.put(EStep.YELLOW, EStep.GREEN);
		this.mapMinusOneStep.put(EStep.RED, EStep.YELLOW);

	}

	public void setFightingPointsUpdateIndicator() {

		if (this.updateIndicatorGameStates.contains(Flow.INSTANCE.getCurrentGameState().getClass())) {
			this.textIndicator.setVisible(false);
			return;
		}

		this.textIndicator.setVisible(true);

		clearCredentials();
		setMapFightingValues();
		calculatePlayerFightingPoints();
		calculateEncounterFightingPoints();
		printMap();
		setText();

	}

	private void clearCredentials() {

		clearMap();
		this.playerFightingPointsNoDouble = 0;
		this.playerFightingPointsWithDouble = 0;
		this.encounterFightingPoints = 0;
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
		text += this.encounterFightingPoints;

		this.textIndicator.setText(text);

	}

	private void calculatePlayerFightingPoints() {

		calculateHighestCardZeroAbility();
		calculateFightingCardPoints();
		calculateAbilitesAfter();
		calculateDoubleAbility();

	}

	private void calculateAbilitesAfter() {

		if (!Modifiers.INSTANCE.getEStep().equals(EStep.PIRATE))
			return;

		CardPirate cardPirate = Modifiers.INSTANCE.getCardPirateAgainst();
		SidePirate sidePirate = cardPirate.getSidePirate();

		if (!(sidePirate instanceof SidePirateAbility))
			return;

		IAbilityAble iAbilityAble = (IAbilityAble) sidePirate;
		EAbility eAbility = iAbilityAble.getEAbility();

		if (eAbility.equals(EAbility.ONLY_HALF_OF_THE_FACE_UP_FIGHTING_CARDS_COUNT))
			calculateFightingAbilityOnlyHalfOfTheFaceUpCardsCount();
		else if (eAbility.equals(EAbility.EACH_FACE_UP_FIGHTING_CARD_COUNTS_PLUS_ONE_FIGHTING_POINT))
			calculateEachFaceUpFightingCardCountsPlusOneFightingPoint();

	}

	private void calculateEachFaceUpFightingCardCountsPlusOneFightingPoint() {

		for (int counter = -5; counter <= 4; counter++)
			this.playerFightingPointsNoDouble += this.mapFightingValues.get(counter).size();

	}

	private void calculateFightingAbilityOnlyHalfOfTheFaceUpCardsCount() {

		int totalHandCards = 0;

		for (int counter = -5; counter <= 4; counter++)
			totalHandCards += this.mapFightingValues.get(counter).size();

		int cardsNotCounting = totalHandCards / 2;

		for (int counter = -5; counter <= 4; counter++) {

			ArrayList<CardFighting> list = this.mapFightingValues.get(counter);
			int cardsToRemove = Math.min(cardsNotCounting, list.size());

			cardsNotCounting -= cardsToRemove;

			for (int ctr = 1; ctr <= cardsToRemove; ctr++) {

				for (CardFighting cardFighting : list.clone())
					if (!(cardFighting instanceof CardFightingAging))
						list.remove(cardFighting);

			}

		}

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

				for (CardFighting cardDouble : this.cardsDouble) {

					if (cardDouble.equals(cardFighting))
						continue;

					this.playerFightingPointsWithDouble += counter;
					cardsDoubleToResolve--;

					if (cardsDoubleToResolve == 0)
						break;

				}

				if (cardsDoubleToResolve == 0)
					break;

			}

			if (cardsDoubleToResolve == 0)
				break;

		}

	}

	private void calculateEncounterFightingPoints() {

		EStep eStep = Modifiers.INSTANCE.getEStep();

		switch (eStep) {

		case PIRATE:
			calculatePirateFightingPoints();
			break;

		default:
			calculateHazardFightingPoints(eStep);
			break;

		}

	}

	private void calculateHazardFightingPoints(EStep eStep) {

		if (this.containsPhaseMinusOne)
			eStep = this.mapMinusOneStep.get(eStep);

		CardFightingHazardKnowledge cardFightingHazardKnowledge = (CardFightingHazardKnowledge) Modifiers.INSTANCE
				.getCardFightingAgainst();
		SideHazard sideHazard = cardFightingHazardKnowledge.getSideHazard();

		this.encounterFightingPoints = sideHazard.getEHazardValue().getStepValue(eStep);

	}

	private void calculatePirateFightingPoints() {

		CardPirate cardPirate = Modifiers.INSTANCE.getCardPirateAgainst();
		SidePirate sidePirate = cardPirate.getSidePirate();

		if (!(sidePirate instanceof SidePirateAbility)) {
			this.encounterFightingPoints = sidePirate.getFightingValue();
			return;
		}

		IAbilityAble iAbilityAble = (IAbilityAble) sidePirate;
		EAbility eAbility = iAbilityAble.getEAbility();

		switch (eAbility) {

		case PLUS_TWO_HAZARD_POINTS_FOR_EACH_AGING_CARD:

			int agingCardsCurrent = Lists.INSTANCE.deckAging.getSize();
			int agingCardsUsed = Modifiers.INSTANCE.getAgingCardsStartingAmount() - agingCardsCurrent;
			this.encounterFightingPoints = 2 * agingCardsUsed;

			break;

		case FIGHT_AGAINST_ALL_REMAINING_HAZARD_CARDS:

			this.encounterFightingPoints = 0;

			for (CardFightingHazardKnowledge cardFightingHazardKnowledge : Lists.INSTANCE.discardPileHazardKnowledge)
				this.encounterFightingPoints += cardFightingHazardKnowledge.getSideHazard().getEHazardValue()
						.getStepValue(EStep.RED);

			break;

		default:
			this.encounterFightingPoints = sidePirate.getFightingValue();
			break;

		}

	}

	public int getPlayerFightingPointsNoDouble() {
		return this.playerFightingPointsNoDouble;
	}

	public int getPlayerFightingPointsWithDouble() {
		return this.playerFightingPointsWithDouble;
	}

	public int getEncounterFightingPoints() {
		return this.encounterFightingPoints;
	}

	public void setVisibleIndicatorFalse() {
		this.textIndicator.setVisible(false);
	}

	private void updateIndicatorAddGameStates() {

//		this.updateIndicatorGameStates.addLast(AHandleEncounterCardsDrawn.class);
		this.updateIndicatorGameStates.addLast(DrawPirate.class);
		this.updateIndicatorGameStates.addLast(HandleHazardCardsDrawnOne.class);
		this.updateIndicatorGameStates.addLast(HandleHazardCardsDrawnTwo.class);

		this.updateIndicatorGameStates.addLast(ChooseDifficultyLevel.class);
		this.updateIndicatorGameStates.addLast(DrawHazardCards.class);
		this.updateIndicatorGameStates.addLast(DrawNextEncounter.class);
		this.updateIndicatorGameStates.addLast(DrawPirate.class);
		this.updateIndicatorGameStates.addLast(EndGame.class);
		this.updateIndicatorGameStates.addLast(EndTurn.class);
		this.updateIndicatorGameStates.addLast(FightWon.class);
		this.updateIndicatorGameStates.addLast(HandleFightLost.class);
		this.updateIndicatorGameStates.addLast(StartGame.class);
		this.updateIndicatorGameStates.addLast(EndGameWin.class);

	}

}
