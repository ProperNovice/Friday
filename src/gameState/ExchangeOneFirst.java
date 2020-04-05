package gameState;

import controller.AbilityImageViewList;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import enums.EText;
import model.CardFighting;
import model.CardSlot;
import utils.Text;

public class ExchangeOneFirst extends AGameState {

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

			cardSlot.removeCardFighting();

			Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(cardFighting);
			Lists.INSTANCE.discardPilePlayer.relocateImageViews();
			Lists.INSTANCE.discardPilePlayer.toFront();

			DrawCardFromDeckToHandSpecificCardSlot gameState = (DrawCardFromDeckToHandSpecificCardSlot) EGameState.DRAW_CARD_FROM_DECK_TO_HAND_SPECIFIC_SLOT
					.getGameState();
			gameState.setExchangeCardSlot(cardSlot);

			break;

		}

		Flow.INSTANCE.addFirst(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_SPECIFIC_SLOT);
		Flow.INSTANCE.proceed();

	}

}
