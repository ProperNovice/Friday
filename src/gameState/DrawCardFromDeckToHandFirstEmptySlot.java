package gameState;

import controller.Flow;
import controller.Lists;
import enums.ECardAgingType;
import enums.EGameState;
import model.CardFighting;
import model.CardFightingAging;
import model.CardSlot;

public class DrawCardFromDeckToHandFirstEmptySlot extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Lists.INSTANCE.deckPlayer.getArrayList().isEmpty()) {

			addDiscardPileToDeck();
			addAgingCardToDeck();
			handleDeckAfterAddingCards();

		}

		drawCard();

	}

	private void handleDeckAfterAddingCards() {

		Lists.INSTANCE.deckPlayer.animateSynchronousLock();
		Lists.INSTANCE.deckPlayer.getArrayList().shuffle();
		Lists.INSTANCE.deckPlayer.toFront();

		for (CardFighting cardFighting : Lists.INSTANCE.deckPlayer.getArrayList())
			cardFighting.getImageView().flip();

	}

	private void addDiscardPileToDeck() {

		Lists.INSTANCE.deckPlayer.getArrayList().addAll(Lists.INSTANCE.discardPilePlayer.getArrayList());
		Lists.INSTANCE.discardPilePlayer.getArrayList().clear();

	}

	private void addAgingCardToDeck() {

		CardFightingAging cardFightingAgingToAdd = null;

		for (CardFightingAging cardFightingAging : Lists.INSTANCE.deckAging) {

			if (cardFightingAging.getECardAgingType() == ECardAgingType.DIFFICULT)
				continue;

			cardFightingAgingToAdd = cardFightingAging;
			break;

		}

		if (cardFightingAgingToAdd == null)
			cardFightingAgingToAdd = Lists.INSTANCE.deckAging.getArrayList().removeRandom();

		cardFightingAgingToAdd.getImageView().flip();

		Lists.INSTANCE.deckPlayer.getArrayList().addFirst(cardFightingAgingToAdd);

	}

	private void drawCard() {

		CardFighting cardFighting = Lists.INSTANCE.deckPlayer.getArrayList().removeFirst();
		cardFighting.getImageView().flip();

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (!cardSlot.containsCardFighting()) {
				cardSlot.addCardFightingRelocate(cardFighting);
				break;
			}

		cardFighting.getImageView().toFront();

		Flow.INSTANCE.executeGameState(EGameState.FIGHT_OPTIONS);

	}

}
