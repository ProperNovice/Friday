package gameState;

import card.CardFighting;
import card.CardFightingAging;
import card.CardSlot;
import card.SideKnowledge;
import controller.AbilitiesManager;
import controller.AbilityImageViewList;
import controller.Flow;
import controller.Life;
import controller.Lists;
import controller.Modifiers;
import enums.EAbility;
import enums.EGameState;
import enums.EText;
import interfaces.IAbilityAble;
import utils.Text;

public class FightOptions extends AGameState {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.RESOLVE_FIGHT);
		handleTextDrawToShow();
		AbilitiesManager.INSTANCE.setUpResolveAbilities();

	}

	@Override
	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

		if (!AbilitiesManager.INSTANCE.getCardAbilitiesCanBeResolvedNonCopy().contains(cardFighting))
			if (!AbilitiesManager.INSTANCE.getCardAbilitiesCanBeResolvedOnlyCopy().contains(cardFighting))
				return;

		Text.INSTANCE.concealText();

		Flow.INSTANCE.addFirst(EGameState.FIGHT_OPTIONS);

		AbilityImageViewList.INSTANCE.releaseAbilityImageView(cardFighting);
		Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().addLast(cardFighting);
		AbilitiesManager.INSTANCE.resolveAbilityCardProceed(cardFighting);

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case DRAW_CARD_FREE:
			Flow.INSTANCE.addFirst(EGameState.FIGHT_OPTIONS);
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
			break;

		case DRAW_CARD_LOSE_LIFE:
			Life.INSTANCE.loseLife(Modifiers.INSTANCE.getAdditionalFightingCostLifeDraw());
			Flow.INSTANCE.addFirst(EGameState.FIGHT_OPTIONS);
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_RIGHT_SIDE);
			break;

		case RESOLVE_FIGHT:
			AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();
			Flow.INSTANCE.proceed();
			break;

		default:
			break;

		}

	}

	private void handleTextDrawToShow() {

		if (Lists.INSTANCE.deckPlayer.getArrayList().isEmpty())
			if (Lists.INSTANCE.discardPilePlayer.getArrayList().isEmpty())
				if (Lists.INSTANCE.deckAging.getArrayList().isEmpty())
					return;

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (cardSlot.containsCardFighting()) {

				CardFighting cardFighting = cardSlot.getCardFighting();

				if (cardFighting.getImageView().isFlippedBack())
					continue;

				if (!(cardFighting instanceof CardFightingAging))
					continue;

				SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();

				if (!(sideKnowledge instanceof IAbilityAble))
					continue;

				IAbilityAble iAbilityAble = (IAbilityAble) sideKnowledge;
				EAbility eAbility = iAbilityAble.getEAbility();

				if (!eAbility.equals(EAbility.STOP))
					continue;

				Text.INSTANCE.showText(EText.DRAW_CARD_LOSE_LIFE);
				break;

			}

			if (cardSlot.containsFreeCard())
				Text.INSTANCE.showText(EText.DRAW_CARD_FREE);
			else if (Life.INSTANCE.getLifeCurrent() >= Modifiers.INSTANCE.getAdditionalFightingCostLifeDraw())
				Text.INSTANCE.showText(EText.DRAW_CARD_LOSE_LIFE);

			break;

		}

	}

}
