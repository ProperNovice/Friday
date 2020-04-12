package gameState;

import card.CardFighting;
import card.CardFightingHazardKnowledge;
import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import utils.Text;

public class DrawHazardCards extends AGameState {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.DRAW_HAZARD_CARDS);
		Text.INSTANCE.showText(EText.CONTINUE);

	}

	@Override
	protected void executeTextOption(EText eText) {

		int cardsToDraw = Math.min(2, Lists.INSTANCE.deckHazardKnowledge.getArrayList().size());
		drawHazardCards(cardsToDraw);

		EGameState eGameState = null;

		if (cardsToDraw == 1)
			eGameState = EGameState.HANDLE_HAZARD_CARDS_DRAWN_ONE;
		else
			eGameState = EGameState.HANDLE_HAZARD_CARDS_DRAWN_TWO;

		Flow.INSTANCE.executeGameState(eGameState);

	}

	private void drawHazardCards(int cardsToDraw) {

		for (int counter = 1; counter <= cardsToDraw; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckHazardKnowledge.getArrayList().removeFirst();
			cardFighting.getImageView().flip();
			Lists.INSTANCE.cardsHazardsDrawn.getArrayList().addLast((CardFightingHazardKnowledge) cardFighting);

		}

		Lists.INSTANCE.cardsHazardsDrawn.relocateImageViews();

	}

}
