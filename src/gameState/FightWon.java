package gameState;

import card.CardFightingHazardKnowledge;
import card.CardPirate;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import enums.EStep;
import enums.EText;

public class FightWon extends AFightEnded {

	@Override
	public void handleGameStateChange() {

		if (gameWon()) {

			Flow.INSTANCE.clear();
			Flow.INSTANCE.executeGameState(EGameState.END_GAME_WIN);
			return;

		}

		EText.FIGHT_WON.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		super.handleDestroyedCards();

		switch (Modifiers.INSTANCE.getEStep()) {

		case PIRATE:
			handleFightAgainstPirate();
			break;

		default:
			handleFightAgainstHazard();
			break;

		}

		Flow.INSTANCE.proceed();

	}

	private void handleFightAgainstHazard() {

		super.putCardsToDiscardPileFromHandPreviouslyOwned(false);
		super.removeHazardFightingAgainstFromHand(true);

		CardFightingHazardKnowledge cardFightingHazardKnowledge = (CardFightingHazardKnowledge) Modifiers.INSTANCE
				.getCardFightingAgainst();
		Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(cardFightingHazardKnowledge);
		Lists.INSTANCE.discardPilePlayer.relocateImageViews();
		Lists.INSTANCE.discardPilePlayer.toFront();

	}

	private boolean gameWon() {

		if (!Modifiers.INSTANCE.getEStep().equals(EStep.PIRATE))
			return false;

		if (Lists.INSTANCE.deckPirates.getArrayList().size() > 1)
			return false;

		return true;

	}

	private void handleFightAgainstPirate() {

		super.putCardsToDiscardPileFromHandPreviouslyOwned(true);
		super.removeHazardFightingAgainstFromHand(false);

		Modifiers.INSTANCE.getCardPirateAgainst().getImageView().setVisible(false);
		Modifiers.INSTANCE.getCardFightingAgainst().getImageView().setVisible(false);

		CardPirate cardPirate = Lists.INSTANCE.deckPirates.getArrayList().removeFirst();
		cardPirate.getImageView().setVisible(false);

		Lists.INSTANCE.deckPirates.relocateImageViews();

	}

}
