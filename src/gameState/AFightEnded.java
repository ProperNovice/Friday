package gameState;

import controller.Lists;
import controller.Modifiers;
import model.CardFighting;
import model.CardSlot;

public abstract class AFightEnded extends AGameState {

	@Override
	public void handleGameStateChange() {

	}

	protected void handleDestroyedCards() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

			if (!(cardSlot.containsCardFighting()))
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (cardFighting.getImageView().isFlippedFront())
				continue;

			cardSlot.removeCardFighting();
			cardFighting.getImageView().setVisible(false);

		}

	}

	protected void putCardsToDiscardPileFromHandPreviouslyOwned(boolean toFront) {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

			if (!(cardSlot.containsCardFighting()))
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (cardFighting.equals(Modifiers.INSTANCE.getCardFightingAgainst()))
				continue;

			cardSlot.removeCardFighting();
			Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(cardFighting);

		}

		Lists.INSTANCE.discardPilePlayer.relocateImageViews();

		if (toFront)
			Lists.INSTANCE.discardPilePlayer.toFront();

	}

	protected void removeHazardFightingAgainstFromHand(boolean rotate) {

		CardFighting cardFightingHazardKnowledge = Modifiers.INSTANCE.getCardFightingAgainst();

		if (rotate)
			cardFightingHazardKnowledge.getImageView().setRotate(180);

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer)
			if (cardSlot.containsCardFighting())
				if (cardSlot.getCardFighting().equals(cardFightingHazardKnowledge))
					cardSlot.removeCardFighting();

	}

}
