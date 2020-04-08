package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EText;
import model.CardFightingHazardKnowledge;

public class FightWon extends AFightEnded {

	@Override
	public void handleGameStateChange() {

		EText.FIGHT_WON.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		super.handleDestroyedCards();
		super.putCardsToDiscardPileFromHandPreviouslyOwned(false);
		super.removeHazardFightingAgainstFromHand(true);

		CardFightingHazardKnowledge cardFightingHazardKnowledge = Modifiers.INSTANCE.getCardFightingAgainst();
		Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(cardFightingHazardKnowledge);
		Lists.INSTANCE.discardPilePlayer.relocateImageViews();
		Lists.INSTANCE.discardPilePlayer.toFront();

		Flow.INSTANCE.proceed();

	}

}
