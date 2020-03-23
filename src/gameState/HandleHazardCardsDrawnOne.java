package gameState;

import controller.Lists;
import enums.EText;
import model.CardFighting;
import utils.Text;

public class HandleHazardCardsDrawnOne extends AHandleHazardCardsDrawn {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.FIGHT_THE_HAZARD);
		Text.INSTANCE.showText(EText.SKIP_THE_HAZARD);

	}

	@Override
	protected void executeTextOption(EText eText) {

		Text.INSTANCE.concealText();
		CardFighting cardFighting = Lists.INSTANCE.cardsHazardsDrawn.getArrayList().removeFirst();

		if (eText == EText.FIGHT_THE_HAZARD) {
			super.addCardToHand(cardFighting);
		} else if (eText == EText.SKIP_THE_HAZARD) {
			super.addCardToDiscardPileHazardKnowledge(cardFighting);
		}

	}

	@Override
	protected void executeCardPressedHazardsDrawn(CardFighting cardFighting) {

		Text.INSTANCE.concealText();
		Lists.INSTANCE.cardsHazardsDrawn.getArrayList().removeFirst();
		super.addCardToHand(cardFighting);

	}

}
