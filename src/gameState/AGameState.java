package gameState;

import card.Card;
import card.CardFighting;
import card.CardFightingHazardKnowledge;
import card.CardFightingPirateProxy;
import card.CardSlot;
import controller.Lists;
import controller.Modifiers;
import enums.EText;
import javafx.scene.input.KeyCode;
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

		card.print();

		if (!(card instanceof CardFighting))
			return;

		if (Lists.INSTANCE.cardsHazardsDrawn.getArrayList().contains(card))
			executeCardPressedHazardsDrawn((CardFightingHazardKnowledge) card);
		else if (Lists.INSTANCE.handPlayer.contains((CardFighting) card))
			executeCardFightingPressedHand((CardFighting) card);
		else if (Lists.INSTANCE.sortCardsPanel.getPanel().getArrayList().contains(card))
			executeCardFightingPressedSortCardPanel((CardFighting) card);

	}

	public final void executeCardWhenEntered(Card card) {

		if (!card.equals(Modifiers.INSTANCE.getCardFightingAgainst()))
			return;

		if (card instanceof CardFightingPirateProxy)
			return;

		card.getImageView().toFront();

	}

	public final void executeCardWhenExited(Card card) {

		if (!card.equals(Modifiers.INSTANCE.getCardFightingAgainst()))
			return;
		
		if (card instanceof CardFightingPirateProxy)
			return;

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer)
			if (cardSlot.containsCardFighting())
				cardSlot.getCardFighting().getImageView().toFront();

	}

	protected void executeCardPressedHazardsDrawn(CardFightingHazardKnowledge cardFightingHazardKnowledge) {

	}

	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

	}

	protected void executeCardFightingPressedSortCardPanel(CardFighting cardFighting) {

	}

	public boolean fightingPointsCalculate() {
		return true;
	}
	
	public boolean updateListsSizeIndicators() {
		return true;
	}

}
