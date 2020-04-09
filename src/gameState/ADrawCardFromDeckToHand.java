package gameState;

import controller.Flow;
import controller.Lists;
import enums.ECardAgingType;
import model.CardFighting;
import model.CardFightingAging;
import model.CardSlot;

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

		if (Lists.INSTANCE.deckAging.getArrayList().isEmpty())
			return;

		CardFightingAging cardFightingAgingToAdd = null;

		for (CardFightingAging cardFightingAging : Lists.INSTANCE.deckAging) {

			if (cardFightingAging.getECardAgingType() == ECardAgingType.DIFFICULT)
				continue;

			cardFightingAgingToAdd = cardFightingAging;
			break;

		}

		if (cardFightingAgingToAdd == null)
			cardFightingAgingToAdd = Lists.INSTANCE.deckAging.getArrayList().getRandom();

		Lists.INSTANCE.deckAging.getArrayList().remove(cardFightingAgingToAdd);

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
