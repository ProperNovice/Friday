package controller;

import enums.EAbility;
import model.CardFighting;
import model.CardSlot;
import utils.Logger;

public enum AbilitiesCanBeResolved {

	INSTANCE;

	public boolean canBeResolved(CardFighting cardFighting, EAbility eAbility) {

		boolean canBeResolved = false;

		switch (eAbility) {

		case BELOW_THE_PILE_ONE:
			canBeResolved = belowThePileOne();
			break;

		case COPY_ONE:
			break;

		case DESTROY_ONE:
			canBeResolved = destroyOne(cardFighting);
			break;

		case DOUBLE_ONE:
			canBeResolved = false;
			break;

		case EACH_ADDITIONAL_FIGHTING_CARD_COSTS_TWO_LIFE_POINTS:
			canBeResolved = false;
			break;

		case EACH_FACE_UP_FIGHTING_CARD_COUNTS_PLUS_ONE_FIGHTING_POINT:
			canBeResolved = false;
			break;

		case EXCHANGE_ONE:
			break;

		case EXCHANGE_TWO:
			break;

		case FIGHT_AGAINST_ALL_REMAINING_HAZARD_CARDS:
			canBeResolved = false;
			break;

		case HIGHEST_CARD_EQUALS_ZERO:
			canBeResolved = false;
			break;

		case MINUS_ONE_LIFE:
			canBeResolved = false;
			break;

		case MINUS_TWO_LIFE:
			canBeResolved = false;
			break;

		case ONLY_HALF_OF_THE_FACE_UP_FIGHTING_CARDS_COUNT:
			canBeResolved = false;
			break;

		case PHASE_MINUS_ONE:
			canBeResolved = false;
			break;

		case PLUS_ONE_CARD:
			canBeResolved = true;
			break;

		case PLUS_ONE_LIFE:
			canBeResolved = true;
			break;

		case PLUS_TWO_CARDS:
			canBeResolved = true;
			break;

		case PLUS_TWO_HAZARD_POINTS_FOR_EACH_FIGHTING_CARD:
			canBeResolved = false;
			break;

		case PLUS_TWO_LIFE:
			canBeResolved = true;
			break;

		case SORT_THREE_CARDS:
			break;

		case STOP:
			canBeResolved = false;
			break;

		}

		Logger.INSTANCE.log("can be resolved");
		Logger.INSTANCE.log(eAbility);
		Logger.INSTANCE.logNewLine(canBeResolved);

		return false;

	}

	private boolean belowThePileOne() {
		return !Lists.INSTANCE.deckPlayer.getArrayList().isEmpty();
	}

	private boolean destroyOne(CardFighting cardFightingDestroy) {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFightingContaining = cardSlot.getCardFighting();

			if (cardFightingContaining == cardFightingDestroy)
				continue;

			if (cardFightingContaining == Modifiers.INSTANCE.getCardFightingAgainst())
				continue;

			return true;

		}

		return false;

	}

}
