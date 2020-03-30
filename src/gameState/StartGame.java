package gameState;

import controller.Flow;
import controller.Life;
import controller.Lists;
import controller.Modifiers;
import enums.EAbility;
import enums.EGameState;
import interfaces.ISideHazardAble;
import model.CardFighting;
import model.CardFightingHazardKnowledge;
import model.CardSlot;
import model.SideKnowledge;
import model.SideKnowledgeAbility;
import utils.Logger;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

//		Flow.INSTANCE.addLast(EGameState.DRAW_HAZARD_CARDS);
//		Flow.INSTANCE.addLast(EGameState.CHOOSE_HAZARD_TO_FIGHT);
//
//		Flow.INSTANCE.proceed();

		addRandomHazardCardToHand();
		
		addCardToHandWithAbility(EAbility.PLUS_TWO_CARDS);
		addCardToHandWithAbility(EAbility.PLUS_TWO_CARDS);
		
		addCardsFromDeckToDiscardPile(16);
		loseLife(18);
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_START);

	}

	protected void loseLife(int lifeToLose) {
		Life.INSTANCE.loseLife(lifeToLose);
	}

	protected void addCardsFromDeckToDiscardPile(int cards) {

		for (int counter = 1; counter <= cards; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckPlayer.getArrayList().removeFirst();
			cardFighting.getImageView().flip();
			Lists.INSTANCE.discardPilePlayer.getArrayList().addLast(cardFighting);

		}

		Lists.INSTANCE.discardPilePlayer.toFront();
		Lists.INSTANCE.discardPilePlayer.relocateImageViews();

	}

	protected void addCardToHandWithAbility(EAbility eAbility) {

		CardFightingHazardKnowledge cardToAdd = null;

		for (CardFightingHazardKnowledge cardFightingHazardKnowledge : Lists.INSTANCE.deckHazardKnowledge) {

			SideKnowledge sideKnowledge = cardFightingHazardKnowledge.getSideKnowledge();

			if (!(sideKnowledge instanceof SideKnowledgeAbility))
				continue;

			SideKnowledgeAbility sideKnowledgeAbility = (SideKnowledgeAbility) sideKnowledge;

			if (sideKnowledgeAbility.getEAbility() != eAbility)
				continue;

			cardToAdd = cardFightingHazardKnowledge;
			break;

		}

		if (cardToAdd == null)
			return;
		
		cardToAdd.getImageView().setRotate(180);
		cardToAdd.getImageView().flip();
		Lists.INSTANCE.deckHazardKnowledge.getArrayList().remove(cardToAdd);

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (!cardSlot.containsCardFighting()) {
				cardSlot.addCardFightingRelocate(cardToAdd);
				break;
			}

	}

	protected void addRandomHazardCardToHand() {

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

	protected void removeHazards(int cardsToRemove) {

		for (int counter = 1; counter <= cardsToRemove; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckHazardKnowledge.getArrayList().removeRandom();
			cardFighting.getImageView().setVisible(false);

		}

	}

}
