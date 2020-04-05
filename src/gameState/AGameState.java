package gameState;

import controller.Lists;
import controller.Modifiers;
import enums.EText;
import javafx.scene.input.KeyCode;
import model.Card;
import model.CardFighting;
import utils.KeyCodeHandler;
import utils.Logger;
import utils.Text;

public abstract class AGameState {

	public abstract void handleGameStateChange();

	public final void handleTextOptionPressed(EText textEnum) {

		Logger.INSTANCE.log("text executing");
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

		if (Lists.INSTANCE.cardsHazardsDrawn.getArrayList().contains(card))
			executeCardPressedHazardsDrawn((CardFighting) card);
		else if (Lists.INSTANCE.handPlayer.contains((CardFighting) card))
			executeCardFightingPressedHand((CardFighting) card);
		else if (Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().contains(card))
			executeCardFightingPressedSortCardPanel((CardFighting) card);

	}

	public final void executeCardWhenEntered(Card card) {

		if (!card.equals(Modifiers.INSTANCE.getCardFightingAgainst()))
			return;

		card.getImageView().toFront();

	}

	public final void executeCardWhenExited(Card card) {

		if (!card.equals(Modifiers.INSTANCE.getCardFightingAgainst()))
			return;

		card.getImageView().toBack();

	}

	protected void executeCardPressedHazardsDrawn(CardFighting cardFighting) {

	}

	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

	}

	protected void executeCardFightingPressedSortCardPanel(CardFighting cardFighting) {

	}

}
