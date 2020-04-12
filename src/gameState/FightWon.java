package gameState;

import card.CardFightingHazardKnowledge;
import card.CardPirate;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import enums.EText;

public class FightWon extends AFightEnded {

	@Override
	public void handleGameStateChange() {

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

	private void handleFightAgainstPirate() {

		super.putCardsToDiscardPileFromHandPreviouslyOwned(true);
		super.removeHazardFightingAgainstFromHand(false);

		Modifiers.INSTANCE.getCardPirateAgainst().getImageView().setVisible(false);
		Modifiers.INSTANCE.getCardFightingAgainst().getImageView().setVisible(false);

		CardPirate cardPirate = Lists.INSTANCE.deckPirates.getArrayList().removeFirst();
		cardPirate.getImageView().setVisible(false);

		if (Lists.INSTANCE.deckPirates.getArrayList().isEmpty()) {

			Flow.INSTANCE.clear();
			Flow.INSTANCE.addFirst(EGameState.END_GAME_WIN);

		} else
			Lists.INSTANCE.deckPirates.relocateImageViews();

	}

}
