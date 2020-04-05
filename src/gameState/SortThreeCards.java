package gameState;

import controller.AbilityImageViewList;
import controller.Lists;
import model.CardFighting;

public class SortThreeCards extends AGameState {

	@Override
	public void handleGameStateChange() {

		AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();

		int cardsLeftInDeck = Lists.INSTANCE.deckPlayer.getArrayList().size();
		int panelCapacity = Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().getCapacity();
		int cardsToDraw = Math.min(cardsLeftInDeck, panelCapacity);

		for (int counter = 1; counter <= cardsToDraw; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckPlayer.getArrayList().removeFirst();
			cardFighting.getImageView().flipFront();

			Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().addLast(cardFighting);

		}

		Lists.INSTANCE.sortCardsPanel.showBackgroundPanelAndRelocate(true);

	}

}
