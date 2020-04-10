package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EText;
import model.CardFightingHazardKnowledge;

public class DrawPirate extends AHandleEncounterCardsDrawn {

	@Override
	public void handleGameStateChange() {

		EText.PIRATE_ENCOUNTER.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Modifiers.INSTANCE.setCardPirateAgainst(Lists.INSTANCE.cardPiratesInPlay.getArrayList().getFirst());

		Modifiers.INSTANCE.setPirateProxyFightingAgainst();

		setFreeCardsAndCardProxy();

		Flow.INSTANCE.proceed();

	}

	private void setFreeCardsAndCardProxy() {

		int freeCards = Modifiers.INSTANCE.getCardPirateAgainst().getSidePirate().getFreeCards();

		if (freeCards == 99) {

			freeCards = 0;
			for (CardFightingHazardKnowledge cardFightingHazardKnowledge : Lists.INSTANCE.discardPileHazardKnowledge)
				freeCards += cardFightingHazardKnowledge.getSideHazard().getEHazardValue().getFreeCards();

		}

		super.setCardSlotsAndPrint(freeCards);

		Lists.INSTANCE.handPlayer.getCardSlots().get(freeCards)
				.addCardFightingRelocate(Modifiers.INSTANCE.getCardFightingAgainst());

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
