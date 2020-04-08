package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import enums.EText;
import model.CardFightingHazardKnowledge;

public class FightLost extends AFightEnded {

	@Override
	public void handleGameStateChange() {

		EText.FIGHT_LOST.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		super.handleDestroyedCards();
		super.removeHazardFightingAgainstFromHand(false);

		CardFightingHazardKnowledge cardFightingHazardKnowledge = Modifiers.INSTANCE.getCardFightingAgainst();
		Lists.INSTANCE.discardPileHazardKnowledge.getArrayList().addFirst(cardFightingHazardKnowledge);
		Lists.INSTANCE.discardPileHazardKnowledge.relocateImageViews();
		Lists.INSTANCE.discardPileHazardKnowledge.toFront();

		Flow.INSTANCE.executeGameState(EGameState.HANDLE_FIGHT_LOST);

	}

}
