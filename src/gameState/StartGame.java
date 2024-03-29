package gameState;

import card.CardFighting;
import card.CardFightingAging;
import card.CardFightingHazardKnowledge;
import card.CardSlot;
import card.SideKnowledge;
import card.SideKnowledgeAbility;
import controller.Flow;
import controller.Life;
import controller.Lists;
import controller.Modifiers;
import enums.EAbility;
import enums.EGameState;
import interfaces.ISideHazardAble;
import realTime.RealTimeCardsHandFlipFront;
import realTime.RealTimeDuplicateImageView;
import realTime.RealTimeListsSize;
import realTime.RealTimeListsTransferCards;
import utils.Executor;
import utils.Logger;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		RealTimeListsSize.INSTANCE.start();
		RealTimeListsTransferCards.INSTANCE.start();
		RealTimeCardsHandFlipFront.INSTANCE.start();
		RealTimeDuplicateImageView.INSTANCE.start();

		Flow.INSTANCE.executeGameState(EGameState.CHOOSE_DIFFICULTY_LEVEL);

	}

	protected void removeAgingCards(int amount) {
		for (int counter = 1; counter <= amount; counter++)
			Lists.INSTANCE.deckAging.getArrayList().removeRandom();
	}

	protected void addHazardFromDeckToDiscard(int amount) {

		for (int counter = 1; counter <= amount; counter++) {

			CardFightingHazardKnowledge card = Lists.INSTANCE.deckHazardKnowledge.getArrayList().removeFirst();
			Lists.INSTANCE.discardPileHazardKnowledge.getArrayList().addFirst(card);
			card.getImageView().flipFront();
			card.getImageView().toFront();

			Lists.INSTANCE.discardPileHazardKnowledge.animateSynchronousLock();
			Executor.INSTANCE.sleep(1000);

		}

	}

	protected void loseLife(int lifeToLose) {
		Life.INSTANCE.loseLife(lifeToLose);
	}

	protected void addCardsFromDeckToDiscardPile(int cards) {

		for (int counter = 1; counter <= cards; counter++) {

			CardFighting cardFighting = Lists.INSTANCE.deckPlayer.getArrayList().removeRandom();
			cardFighting.getImageView().flip();
			Lists.INSTANCE.discardPilePlayer.getArrayList().addLast(cardFighting);

		}

		Lists.INSTANCE.discardPilePlayer.toFront();
		Lists.INSTANCE.discardPilePlayer.relocateImageViews();

	}

	protected void addCardToHandFromDeckHazardWithAbility(EAbility eAbility, boolean flipFront) {

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

		if (flipFront)
			cardToAdd.getImageView().flip();

		Lists.INSTANCE.deckHazardKnowledge.getArrayList().remove(cardToAdd);

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (!cardSlot.containsCardFighting()) {
				cardSlot.addCardFightingRelocate(cardToAdd);
				break;
			}

		cardHandToFront();

	}

	protected void addCardToHandFromDeckAgingWithAbility(EAbility eAbility, boolean flipFront) {

		CardFightingAging cardToAdd = null;

		for (CardFightingAging cardAging : Lists.INSTANCE.deckAging) {

			SideKnowledge sideKnowledge = cardAging.getSideKnowledge();

			if (!(sideKnowledge instanceof SideKnowledgeAbility))
				continue;

			SideKnowledgeAbility sideKnowledgeAbility = (SideKnowledgeAbility) sideKnowledge;

			if (sideKnowledgeAbility.getEAbility() != eAbility)
				continue;

			cardToAdd = cardAging;
			break;

		}

		if (cardToAdd == null)
			return;

		if (flipFront)
			cardToAdd.getImageView().flip();

		Lists.INSTANCE.deckAging.getArrayList().remove(cardToAdd);

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (!cardSlot.containsCardFighting()) {
				cardSlot.addCardFightingRelocate(cardToAdd);
				break;
			}

		cardHandToFront();

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
		Modifiers.INSTANCE.setCardFightingAgainst((CardFightingHazardKnowledge) cardFighting);

		cardHandToFront();

	}

	protected void cardHandToFront() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (cardSlot.containsCardFighting())
				cardSlot.getCardFighting().getImageView().toFront();

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

	protected void eStepProceed() {

		Lists.INSTANCE.deckStep.getArrayList().addLast(Lists.INSTANCE.deckStep.getArrayList().removeFirst());
		Lists.INSTANCE.deckStep.toFront();
		Modifiers.INSTANCE.eStepAdvance();

	}

}
