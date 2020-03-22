package gameState;

import controller.Lists;
import enums.EText;
import model.CardFighting;
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

	}

	private void drawHazardCards(int cardsToDraw) {

		for (int counter = 1; counter <= cardsToDraw; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckHazardKnowledge.getArrayList().removeFirst();
			cardFighting.getImageView().flip();
			Lists.INSTANCE.cardsHazardsDrawn.getArrayList().addLast(cardFighting);

		}

		Lists.INSTANCE.cardsHazardsDrawn.relocateImageViews();

	}

}
