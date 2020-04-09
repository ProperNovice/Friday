package gameState;

import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import model.CardFightingHazardKnowledge;
import utils.Text;

public class HandleHazardCardsDrawnTwo extends AHandleHazardCardsDrawn {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.CHOOSE_HAZARD_TO_FIGHT);

	}

	@Override
	protected void executeCardPressedHazardsDrawn(CardFightingHazardKnowledge cardFightingHazardKnowledge) {

		Text.INSTANCE.concealText();

		Lists.INSTANCE.cardsHazardsDrawn.getArrayList().remove(cardFightingHazardKnowledge);
		addCardToHand(cardFightingHazardKnowledge);

		addCardToDiscardPileHazardKnowledge(Lists.INSTANCE.cardsHazardsDrawn.getArrayList().removeFirst());

		Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);

	}

}
