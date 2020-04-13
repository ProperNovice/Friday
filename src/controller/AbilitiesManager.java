package controller;

import card.CardFighting;
import card.CardSlot;
import card.SideKnowledge;
import card.SideKnowledgeAbility;
import enums.EAbility;
import enums.EGameState;
import interfaces.IAbilityAble;
import model.HandPlayer;
import utils.ArrayList;
import utils.ShutDown;

public enum AbilitiesManager {

	INSTANCE;

	private ArrayList<CardFighting> cardAbilitiesCompleteList = new ArrayList<CardFighting>();
	private ArrayList<CardFighting> cardAbilitiesCanBeResolvedNonCopy = new ArrayList<CardFighting>();
	private ArrayList<CardFighting> cardAbilitiesCanBeResolvedOnlyCopy = new ArrayList<CardFighting>();

	public void setUpResolveAbilities() {

		AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();

		this.cardAbilitiesCompleteList.clear();
		this.cardAbilitiesCanBeResolvedNonCopy.clear();
		this.cardAbilitiesCanBeResolvedOnlyCopy.clear();

		createListAbilitesComplete();
		createListAbilitiesCanBeResolved();
		setAbilityImageViewsToCardsThatCanBeResolved();

	}

	private void createListAbilitesComplete() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (cardFighting.getImageView().isFlippedBack())
				continue;

			if (cardFighting == Modifiers.INSTANCE.getCardFightingAgainst())
				continue;

			SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();

			if (!(sideKnowledge instanceof IAbilityAble))
				continue;

			this.cardAbilitiesCompleteList.addLast(cardFighting);

		}

	}

	private void createListAbilitiesCanBeResolved() {

		for (CardFighting cardFighting : this.cardAbilitiesCompleteList) {

			if (Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().contains(cardFighting))
				continue;

			IAbilityAble iAbilityAble = (IAbilityAble) cardFighting.getSideKnowledge();
			EAbility eAbility = iAbilityAble.getEAbility();

			if (eAbility == EAbility.COPY_ONE)
				this.cardAbilitiesCanBeResolvedOnlyCopy.addLast(cardFighting);

			else if (canBeResolved(cardFighting, eAbility))
				this.cardAbilitiesCanBeResolvedNonCopy.addLast(cardFighting);

		}

	}

	private void setAbilityImageViewsToCardsThatCanBeResolved() {

		if (!this.cardAbilitiesCanBeResolvedNonCopy.isEmpty())
			setListAbilityImageViewsTrue(this.cardAbilitiesCanBeResolvedNonCopy);

		int cardAbilitiesInHand = 0;
		cardAbilitiesInHand += this.cardAbilitiesCanBeResolvedNonCopy.size();
		cardAbilitiesInHand += Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().size();

		if (cardAbilitiesInHand > 0)
			setListAbilityImageViewsTrue(this.cardAbilitiesCanBeResolvedOnlyCopy);

	}

	private void setListAbilityImageViewsTrue(ArrayList<CardFighting> list) {

		for (CardFighting cardFighting : list)
			AbilityImageViewList.INSTANCE.setAbilityImageViewForCardFighting(cardFighting);

	}

	public ArrayList<CardFighting> getCardAbilitiesCanBeResolvedNonCopy() {
		return this.cardAbilitiesCanBeResolvedNonCopy;
	}

	public ArrayList<CardFighting> getCardAbilitiesCanBeResolvedOnlyCopy() {
		return this.cardAbilitiesCanBeResolvedOnlyCopy;
	}

	public boolean canBeResolved(CardFighting cardFighting, EAbility eAbility) {

		boolean canBeResolved = false;

		switch (eAbility) {

		case BELOW_THE_PILE_ONE:
			canBeResolved = belowThePileOne();
			break;

		case COPY_ONE:
			ShutDown.INSTANCE.execute("AbilitiesCanBeResolved, COPY_ONE, you shouldn't be here");
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
			canBeResolved = exchangeOne();
			break;

		case EXCHANGE_TWO:
			canBeResolved = exchangeOne();
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

		case PLUS_TWO_HAZARD_POINTS_FOR_EACH_AGING_CARD:
			canBeResolved = false;
			break;

		case PLUS_TWO_LIFE:
			canBeResolved = true;
			break;

		case SORT_THREE_CARDS:
			canBeResolved = sortThreeCards();
			break;

		case STOP:
			canBeResolved = false;
			break;

		}

//		Logger.INSTANCE.log("can be resolved");
//		Logger.INSTANCE.log(eAbility);
//		Logger.INSTANCE.logNewLine(canBeResolved);

		return canBeResolved;

	}

	private boolean belowThePileOne() {

		if (Lists.INSTANCE.deckPlayer.getArrayList().isEmpty())
			return false;

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (Modifiers.INSTANCE.getCardFightingAgainst().equals(cardFighting))
				continue;

			if (Lists.INSTANCE.handPlayer.sizeNotDestroyed() == 1)
				continue;

			if (cardFighting.getImageView().isFlippedBack())
				continue;

			return true;

		}

		return false;

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

	private boolean exchangeOne() {
		return HandPlayer.INSTANCE.sizeNotDestroyed() > 1;
	}

	private boolean sortThreeCards() {
		return Lists.INSTANCE.deckPlayer.getArrayList().size() > 0;
	}

	public void resolveAbilityCardProceed(CardFighting cardFighting) {

		IAbilityAble iAbilityAble = (SideKnowledgeAbility) cardFighting.getSideKnowledge();
		EAbility eAbility = iAbilityAble.getEAbility();

		switch (eAbility) {

		case PLUS_ONE_LIFE:
			Flow.INSTANCE.addFirst(EGameState.PLUS_ONE_LIFE);
			break;

		case PLUS_TWO_LIFE:
			Flow.INSTANCE.addFirst(EGameState.PLUS_ONE_LIFE);
			Flow.INSTANCE.addFirst(EGameState.PLUS_ONE_LIFE);
			break;

		case PLUS_ONE_CARD:
			Flow.INSTANCE.addFirst(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_RIGHT_SIDE);
			break;

		case PLUS_TWO_CARDS:
			Flow.INSTANCE.addFirst(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_RIGHT_SIDE);
			Flow.INSTANCE.addFirst(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_RIGHT_SIDE);
			break;

		case DESTROY_ONE:
			Flow.INSTANCE.addFirst(EGameState.DESTROY_ONE);
			break;

		case COPY_ONE:
			Flow.INSTANCE.addFirst(EGameState.COPY_ONE);
			break;

		case SORT_THREE_CARDS:
			Flow.INSTANCE.addFirst(EGameState.SORT_THREE_CARDS);
			break;

		case EXCHANGE_ONE:
			Flow.INSTANCE.addFirst(EGameState.EXCHANGE_ONE_FIRST);
			break;

		case EXCHANGE_TWO:
			Flow.INSTANCE.addFirst(EGameState.EXCHANGE_ONE_SECOND);
			Flow.INSTANCE.addFirst(EGameState.EXCHANGE_ONE_FIRST);
			break;

		case BELOW_THE_PILE_ONE:
			Flow.INSTANCE.addFirst(EGameState.BELOW_THE_PILE_ONE);
			break;

		default:
			ShutDown.INSTANCE.execute("FightOptions, executeCardFightingPressedHand, you shouldn't be here");
			break;

		}

		Flow.INSTANCE.proceed();

	}

}
