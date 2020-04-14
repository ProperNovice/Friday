package gameState;

import card.CardFightingHazardKnowledge;
import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import utils.Text;

public class HandleHazardCardsDrawnOne extends AHandleEncounterCardsDrawn {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.FIGHT_THE_HAZARD);
		Text.INSTANCE.showText(EText.SKIP_THE_HAZARD);

	}

	@Override
	protected void executeCardPressedHazardsDrawn(CardFightingHazardKnowledge cardFightingHazardKnowledge) {
		executeTextOption(EText.FIGHT_THE_HAZARD);
	}

	@Override
	protected void executeTextOption(EText eText) {

		Text.INSTANCE.concealText();
		CardFightingHazardKnowledge cardFightingHazardKnowledge = Lists.INSTANCE.cardsHazardsDrawn.getArrayList()
				.removeFirst();

		if (eText == EText.FIGHT_THE_HAZARD) {

			super.addCardToHand(cardFightingHazardKnowledge);
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);

		} else if (eText == EText.SKIP_THE_HAZARD) {

			super.addCardToDiscardPileHazardKnowledge(cardFightingHazardKnowledge);
			Flow.INSTANCE.clear();
			Flow.INSTANCE.executeGameState(EGameState.END_TURN);

		}

	}

}
