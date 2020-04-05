package gameState;

import controller.AbilitiesManager;
import controller.AbilityImageViewList;
import controller.Lists;
import enums.EText;
import model.CardFighting;

public class SortThreeCards extends AGameState {

	@Override
	public void handleGameStateChange() {

		int cardsLeftInDeck = Lists.INSTANCE.deckPlayer.getArrayList().size();
		int panelCapacity = Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().getCapacity();
		int cardsToDraw = Math.min(cardsLeftInDeck, panelCapacity);

		for (int counter = 1; counter <= cardsToDraw; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckPlayer.getArrayList().removeFirst();
			cardFighting.getImageView().flipFront();

			Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().addLast(cardFighting);

		}

		showPanel();

		for (CardFighting cardFighting : Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList())
			cardFighting.print();

	}

	@Override
	protected void executeCardFightingPressedSortCardPanel(CardFighting cardFighting) {

	}

	private void executeCardPressedDestroy(CardFighting cardFighting) {

	}

	private void executeCardPressedSort(CardFighting cardFighting) {

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case SHOW_BOARD:
			showBoard();
			break;

		case SHOW_PANEL:
			showPanel();
			break;

		case CONTINUE:
			break;

		default:
			break;

		}

	}

	private void showBoard() {

		Lists.INSTANCE.sortCardsPanel.showBackgroundPanelAndRelocate(false);
		AbilitiesManager.INSTANCE.setUpResolveAbilities();

		EText.SHOW_PANEL.showText();

	}

	private void showPanel() {

		Lists.INSTANCE.sortCardsPanel.showBackgroundPanelAndRelocate(true);
		AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();

		EText.SHOW_BOARD.showText();
		EText.DESTROY_CARD.showText();
		EText.CONTINUE.showText();

	}

}
