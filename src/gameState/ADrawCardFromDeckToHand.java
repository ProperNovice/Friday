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
			addAgingCardToDeck();
			handleDeckAfterAddingCards();

		}

		drawCard();
		Flow.INSTANCE.proceed();

	}

	private void handleDeckAfterAddingCards() {

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

		if (Lists.INSTANCE.deckAging.getArrayList().isEmpty())
			return;

		CardFightingAging cardFightingAgingToAdd = Lists.INSTANCE.deckAging.getArrayList().removeFirst();
		cardFightingAgingToAdd.getImageView().flip();
		Lists.INSTANCE.deckPlayer.getArrayList().addFirst(cardFightingAgingToAdd);
		Lists.INSTANCE.deckPlayer.animateSynchronousLock();

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
