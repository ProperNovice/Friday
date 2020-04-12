package gameState;

import card.CardFighting;
import card.CardSlot;
import controller.AbilityImageViewList;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EText;
import utils.Text;

public class BelowThePileOne extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.CHOOSE_CARD.showText();

	}

	@Override
	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

		if (Modifiers.INSTANCE.getCardFightingAgainst().equals(cardFighting))
			return;

		if (cardFighting.equals(Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().getLast()))
			return;

		if (cardFighting.getImageView().isFlippedBack())
			return;

		Text.INSTANCE.concealText();

		AbilityImageViewList.INSTANCE.releaseAbilityImageView(cardFighting);

		if (Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().contains(cardFighting))
			Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().remove(cardFighting);

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (!cardSlot.containsCardFighting())
				continue;

			if (!cardSlot.getCardFighting().equals(cardFighting))
				continue;

			cardFighting.getImageView().flip();

			cardSlot.removeCardFighting();

			Lists.INSTANCE.deckPlayer.getArrayList().addLast(cardFighting);
			Lists.INSTANCE.deckPlayer.relocateImageViews();
			Lists.INSTANCE.deckPlayer.toFront();

			break;

		}

		Flow.INSTANCE.proceed();

	}

}
