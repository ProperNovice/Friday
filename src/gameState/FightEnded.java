package gameState;

import controller.Lists;
import controller.Modifiers;
import model.CardFighting;
import model.CardSlot;
import utils.ContainerImageViewAbles;

public abstract class FightEnded extends AGameState {

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

	protected void putCardsToDeckPreviouslyOwned(boolean toFront) {

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

	protected void addHazardFightingAgainstToDiscardPile(ContainerImageViewAbles<CardFighting> list) {

		CardFighting cardFighting = Modifiers.INSTANCE.getCardFightingAgainst();
		cardFighting.getImageView().setRotate(180);

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer)
			if (cardSlot.containsCardFighting())
				if (cardSlot.getCardFighting().equals(cardFighting))
					cardSlot.removeCardFighting();

		list.getArrayList().addFirst(cardFighting);
		list.relocateImageViews();
		list.toFront();

	}

}
