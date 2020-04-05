package gameState;

import controller.Flow;
import controller.Modifiers;
import enums.EText;
import model.CardFighting;
import utils.Text;

public class DestroyOne extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.CHOOSE_CARD.showText();

	}

	@Override
	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

		if (cardFighting.equals(Modifiers.INSTANCE.getCardFightingAgainst()))
			return;

		if (cardFighting.equals(Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().getLast()))
			return;

		if (cardFighting.getImageView().isFlippedBack())
			return;

		Text.INSTANCE.concealText();

		cardFighting.getImageView().flipBack();

		Flow.INSTANCE.proceed();

	}

}
