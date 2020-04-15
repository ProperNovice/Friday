package gameState;

import card.CardFightingHazardKnowledge;
import card.CardPirate;
import card.SidePirate;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EAbility;
import enums.EGameState;
import enums.EText;
import interfaces.IAbilityAble;

public class DrawPirate extends AHandleEncounterCardsDrawn {

	@Override
	public void handleGameStateChange() {

		EText.PIRATE_ENCOUNTER.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Modifiers.INSTANCE.setCardPirateAgainst(Lists.INSTANCE.deckPirates.getArrayList().getFirst());
		Modifiers.INSTANCE.setPirateProxyFightingAgainst();
		setFreeCardsAndCardProxy();
		checkPirateAbility();

		Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);

	}

	private void checkPirateAbility() {

		CardPirate cardPirate = Modifiers.INSTANCE.getCardPirateAgainst();
		SidePirate sidePirate = (SidePirate) cardPirate.getSidePirate();

		if (!(sidePirate instanceof IAbilityAble))
			return;

		IAbilityAble iAbilityAble = (IAbilityAble) sidePirate;
		EAbility eAbility = iAbilityAble.getEAbility();

		if (eAbility.equals(EAbility.EACH_ADDITIONAL_FIGHTING_CARD_COSTS_TWO_LIFE_POINTS))
			Modifiers.INSTANCE.setAdditionalFightingCostLifeDraw(2);

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

}
