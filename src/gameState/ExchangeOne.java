package gameState;

import controller.Modifiers;
import enums.EText;
import model.CardFighting;

public class ExchangeOne extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.CHOOSE_CARD_TO_EXCHANGE.showText();

	}

	@Override
	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

		if (Modifiers.INSTANCE.getCardFightingAgainst().equals(cardFighting))
			return;

		if (cardFighting.equals(Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().getLast()))
			return;

		if (cardFighting.getImageView().isFlippedBack())
			return;

	}

}
