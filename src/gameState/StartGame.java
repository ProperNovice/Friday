package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import interfaces.ISideHazardAble;
import model.CardFighting;
import model.CardFightingHazardKnowledge;
import model.CardSlot;
import utils.Logger;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

//		Flow.INSTANCE.addLast(EGameState.DRAW_HAZARD_CARDS);
//		Flow.INSTANCE.addLast(EGameState.CHOOSE_HAZARD_TO_FIGHT);
//
//		Flow.INSTANCE.proceed();

		addRandomHazardCardToHand();
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_START);

	}

	public void addRandomHazardCardToHand() {

		CardFighting cardFighting = Lists.INSTANCE.deckHazardKnowledge.getArrayList().removeRandom();
		cardFighting.getImageView().flip();

		CardFightingHazardKnowledge cardFightingHazardKnowledge = (CardFightingHazardKnowledge) cardFighting;
		ISideHazardAble sideHazardAble = (ISideHazardAble) cardFightingHazardKnowledge;

		int freeCardsToDraw = sideHazardAble.getSideHazard().getEHazardValue().getFreeCards();
		Lists.INSTANCE.handPlayer.getCardSlots().get(freeCardsToDraw)
				.addCardFightingRelocate(cardFightingHazardKnowledge);

		setCardSlotsAndPrint(freeCardsToDraw);
		Modifiers.INSTANCE.setCardFightingAgainst(cardFighting);

	}

	public void setCardSlotsAndPrint(int freeCardsToDraw) {

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

	public void removeHazards(int cardsToRemove) {

		for (int counter = 1; counter <= cardsToRemove; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckHazardKnowledge.getArrayList().removeRandom();
			cardFighting.getImageView().setVisible(false);

		}

	}

}
