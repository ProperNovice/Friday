package gameState;

import controller.AbilitiesManager;
import controller.AbilityImageViewList;
import controller.Flow;
import controller.Lists;
import enums.EText;
import model.CardFighting;
import utils.Text;

public class SortThreeCards extends AGameState {

	private Status status = null;

	@Override
	public void handleGameStateChange() {

		this.status = Status.DISCARD;

		addCardsToPanel();
		showPanel();

	}

	@Override
	protected void executeCardFightingPressedSortCardPanel(CardFighting cardFighting) {

		Text.INSTANCE.concealText();
		Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().remove(cardFighting);

		switch (this.status) {

		case DISCARD:
			executeCardPressedDiscard(cardFighting);
			break;

		case PUT_ON_TOP_OF_THE_DECK:
			executeCardPressedSort(cardFighting);
			break;

		}

	}

	private void executeCardPressedDiscard(CardFighting cardFighting) {

		Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(cardFighting);
		Lists.INSTANCE.discardPilePlayer.relocateImageViews();
		changeStatus();

	}

	private void executeCardPressedSort(CardFighting cardFighting) {

		cardFighting.getImageView().flipBack();

		Lists.INSTANCE.deckPlayer.getArrayList().addFirst(cardFighting);
		Lists.INSTANCE.deckPlayer.relocateImageViews();

		if (Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().size() > 1)
			return;
		else if (Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().size() == 1)
			handleSortLastCard();
		else if (Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().isEmpty())
			proceedGameState();

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
			changeStatus();
			break;

		default:
			break;

		}

	}

	private void addCardsToPanel() {

		int cardsLeftInDeck = Lists.INSTANCE.deckPlayer.getArrayList().size();
		int panelCapacity = Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().getCapacity();
		int cardsToDraw = Math.min(cardsLeftInDeck, panelCapacity);

		for (int counter = 1; counter <= cardsToDraw; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckPlayer.getArrayList().removeFirst();
			cardFighting.getImageView().flipFront();

			Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().addLast(cardFighting);

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

		if (this.status.continueIsShowing)
			EText.CONTINUE.showText();

		this.status.eText.showText();

	}

	private void changeStatus() {

		this.status = Status.PUT_ON_TOP_OF_THE_DECK;

		int panelSize = Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().size();

		if (panelSize > 1)
			showPanel();
		else if (panelSize == 1)
			handleSortLastCard();
		else if (panelSize == 0)
			proceedGameState();

	}

	private void handleSortLastCard() {

		CardFighting cardFighting = Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().removeFirst();
		executeCardPressedSort(cardFighting);

	}

	private void proceedGameState() {

		Lists.INSTANCE.sortCardsPanel.showBackgroundPanelAndRelocate(false);
		Flow.INSTANCE.proceed();

	}

	private enum Status {

		DISCARD(EText.DISCARD_CARD, true), PUT_ON_TOP_OF_THE_DECK(EText.PUT_CARD_ON_TOP, false);

		private EText eText = null;
		private boolean continueIsShowing;

		private Status(EText eText, boolean continueIsShowing) {
			this.eText = eText;
			this.continueIsShowing = continueIsShowing;
		}

	}

}
