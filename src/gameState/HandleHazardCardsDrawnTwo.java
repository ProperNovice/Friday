package gameState;

import controller.Lists;
import enums.EText;
import model.CardFighting;
import utils.Text;

public class HandleHazardCardsDrawnTwo extends AHandleHazardCardsDrawn {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.CHOOSE_HAZARD_TO_FIGHT);

	}

	@Override
	protected void executeCardPressedHazardsDrawn(CardFighting cardFighting) {

		Text.INSTANCE.concealText();

		Lists.INSTANCE.cardsHazardsDrawn.getArrayList().remove(cardFighting);
		addCardToHand(cardFighting);

		addCardToDiscardPileHazardKnowledge(Lists.INSTANCE.cardsHazardsDrawn.getArrayList().removeFirst());

	}

}
