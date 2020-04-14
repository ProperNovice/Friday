package gameState;

import card.Card;
import card.CardFighting;
import card.CardFightingHazardKnowledge;
import card.CardSlot;
import controller.Flow;
import controller.Lists;
import enums.EText;
import javafx.scene.input.KeyCode;
import model.SortCardsPanel;
import utils.KeyCodeHandler;
import utils.Logger;
import utils.Text;

public abstract class AGameState {

	public abstract void handleGameStateChange();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text option executing");
		Logger.INSTANCE.logNewLine(textEnum);

		Text.INSTANCE.concealText();
		executeTextOption(textEnum);

	}

	public final void executeKeyPressed(KeyCode keyCode) {

		int textOptionToHandle = KeyCodeHandler.INSTANCE.getKeyCodeInt(keyCode);

		EText textEnumPressed = Text.INSTANCE.getTextEnumOptionShowing(textOptionToHandle);

		if (textEnumPressed == null)
			return;

		Logger.INSTANCE.log("executing key pressed -> " + keyCode);
		handleTextOptionPressed(textEnumPressed);

	}

	protected void executeTextOption(EText eText) {

	}

	public final void executeCardPressed(Card card) {

		if (!(card instanceof CardFighting))
			return;

		if (Lists.INSTANCE.cardsHazardsDrawn.getArrayList().contains(card)) {

			executeCardPressedHazardsDrawn((CardFightingHazardKnowledge) card);
			card.print();

		} else if (Lists.INSTANCE.handPlayer.contains((CardFighting) card)) {

			card.print();
			executeCardFightingPressedHand((CardFighting) card);

		} else if (Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().contains(card)) {

			card.print();
			executeCardFightingPressedSortCardPanel((CardFighting) card);

		}

	}

	public final void executeCardWhenEntered(Card card) {

		if (!(card instanceof CardFighting))
			return;

		CardFighting cardFighting = (CardFighting) card;

		if (!Lists.INSTANCE.handPlayer.contains(cardFighting))
			return;

		if (cardFighting.getImageView().isFlippedBack())
			return;

		card.getImageView().toFront();

	}

	public final void executeCardWhenExited(Card card) {

		if (!(card instanceof CardFighting))
			return;

		CardFighting cardFighting = (CardFighting) card;

		if (!Lists.INSTANCE.handPlayer.contains(cardFighting))
			return;

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer)
			if (cardSlot.containsCardFighting())
				cardSlot.getCardFighting().getImageView().toFront();

		if (Flow.INSTANCE.getCurrentGameState() instanceof SortThreeCards)
			SortCardsPanel.INSTANCE.showBackgroundPanelAndRelocate(true);

	}

	protected void executeCardPressedHazardsDrawn(CardFightingHazardKnowledge cardFightingHazardKnowledge) {

	}

	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

	}

	protected void executeCardFightingPressedSortCardPanel(CardFighting cardFighting) {

	}

}
