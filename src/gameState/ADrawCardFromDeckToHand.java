package gameState;

import card.CardFighting;
import card.CardFightingAging;
import card.CardSlot;
import controller.Flow;
import controller.Lists;

public abstract class ADrawCardFromDeckToHand extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Lists.INSTANCE.deckPlayer.getArrayList().isEmpty()) {

			addDiscardPileToDeck();

			if (!Lists.INSTANCE.deckAging.getArrayList().isEmpty())
				addAgingCardToDeck();

			Lists.INSTANCE.deckPlayer.animateSynchronousLock();

			handleDeckAfterAddingCards();

		}

		drawCard();
		Flow.INSTANCE.proceed();

	}

	private void handleDeckAfterAddingCards() {

		for (CardFighting cardFighting : Lists.INSTANCE.deckPlayer.getArrayList())
			cardFighting.getImageView().flip();

		Lists.INSTANCE.deckPlayer.getArrayList().shuffle();
		Lists.INSTANCE.deckPlayer.toFront();

	}

	private void addDiscardPileToDeck() {

		Lists.INSTANCE.deckPlayer.getArrayList().addAll(Lists.INSTANCE.discardPilePlayer.getArrayList());
		Lists.INSTANCE.discardPilePlayer.getArrayList().clear();

	}

	private void addAgingCardToDeck() {

		CardFightingAging cardFightingAgingToAdd = Lists.INSTANCE.deckAging.getArrayList().removeFirst();
		cardFightingAgingToAdd.getImageView().flip();
		Lists.INSTANCE.deckPlayer.getArrayList().addFirst(cardFightingAgingToAdd);

	}

	private void drawCard() {

		CardFighting cardFighting = Lists.INSTANCE.deckPlayer.getArrayList().removeFirst();
		cardFighting.getImageView().flip();

		CardSlot cardSlot = getCardSlotToAddTheCard();
		cardSlot.addCardFightingRelocate(cardFighting);

		cardHandToFront();

	}

	private void cardHandToFront() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (cardSlot.containsCardFighting())
				cardSlot.getCardFighting().getImageView().toFront();

	}

	protected abstract CardSlot getCardSlotToAddTheCard();

}
