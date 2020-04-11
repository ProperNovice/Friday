package gameState;

import card.CardFightingHazardKnowledge;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import enums.EText;

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

		switch (Modifiers.INSTANCE.getEStep()) {

		case PIRATE:
			handleLoseAgainstPirate();
			Flow.INSTANCE.clear();
			Flow.INSTANCE.executeGameState(EGameState.END_GAME_LOSE);
			break;

		default:
			handleLoseAgainstHazard();
			Flow.INSTANCE.executeGameState(EGameState.HANDLE_FIGHT_LOST);
			break;

		}

	}

	private void handleLoseAgainstHazard() {

		CardFightingHazardKnowledge cardFightingHazardKnowledge = (CardFightingHazardKnowledge) Modifiers.INSTANCE
				.getCardFightingAgainst();
		Lists.INSTANCE.discardPileHazardKnowledge.getArrayList().addFirst(cardFightingHazardKnowledge);
		Lists.INSTANCE.discardPileHazardKnowledge.relocateImageViews();
		Lists.INSTANCE.discardPileHazardKnowledge.toFront();

	}

	private void handleLoseAgainstPirate() {
		Modifiers.INSTANCE.getCardPirateAgainst().getImageView().setVisible(false);
	}

}
