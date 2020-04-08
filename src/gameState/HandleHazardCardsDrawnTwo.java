package gameState;

import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import model.CardFighting;
import model.CardFightingHazardKnowledge;
import utils.Text;

public class HandleHazardCardsDrawnTwo extends AHandleHazardCardsDrawn {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.CHOOSE_HAZARD_TO_FIGHT);

	}

	@Override
	protected void executeCardPressedHazardsDrawn(CardFighting cardFighting) {

		Text.INSTANCE.concealText();

		CardFightingHazardKnowledge cardFightingHazardKnowledge = (CardFightingHazardKnowledge) cardFighting;

		Lists.INSTANCE.cardsHazardsDrawn.getArrayList().remove(cardFightingHazardKnowledge);
		addCardToHand(cardFighting);

		addCardToDiscardPileHazardKnowledge(Lists.INSTANCE.cardsHazardsDrawn.getArrayList().removeFirst());

		Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);

	}

}
