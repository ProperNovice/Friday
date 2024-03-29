package gameState;

import card.CardFightingHazardKnowledge;
import card.CardSlot;
import controller.Lists;
import controller.Modifiers;
import interfaces.ISideHazardAble;
import utils.Logger;

public abstract class AHandleEncounterCardsDrawn extends AGameState {

	protected void addCardToHand(CardFightingHazardKnowledge cardFightingHazardKnowledge) {

		ISideHazardAble sideHazardAble = (ISideHazardAble) cardFightingHazardKnowledge;

		int freeCardsToDraw = sideHazardAble.getSideHazard().getEHazardValue().getFreeCards();
		Lists.INSTANCE.handPlayer.getCardSlots().get(freeCardsToDraw)
				.addCardFightingRelocate(cardFightingHazardKnowledge);

		setCardSlotsAndPrint(freeCardsToDraw);
		Modifiers.INSTANCE.setCardFightingAgainst(cardFightingHazardKnowledge);

	}

	protected void setCardSlotsAndPrint(int freeCardsToDraw) {

		boolean toPrint = true;

		Logger.INSTANCE.log("*");
		Logger.INSTANCE.log("Slots contain free cards");

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			boolean containsFreeCard = false;

			if (freeCardsToDraw > 0) {

				containsFreeCard = true;
				freeCardsToDraw--;

			}

			cardSlot.setContainsFreeCard(containsFreeCard);

			if (!toPrint)
				continue;

			if (!containsFreeCard)
				toPrint = false;

			Logger.INSTANCE.log(
					Lists.INSTANCE.handPlayer.getCardSlots().indexOf(cardSlot) + " -> " + cardSlot.containsFreeCard());

		}

		Logger.INSTANCE.logNewLine("*");

	}

	protected void addCardToDiscardPileHazardKnowledge(CardFightingHazardKnowledge cardFightingHazardKnowledge) {

		Lists.INSTANCE.discardPileHazardKnowledge.getArrayList().addFirst(cardFightingHazardKnowledge);
		Lists.INSTANCE.discardPileHazardKnowledge.relocateImageViews();

	}

}
