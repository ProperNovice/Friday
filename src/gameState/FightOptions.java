package gameState;

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
import model.CardFighting;
import model.CardFightingAging;
import model.CardSlot;
import model.SideKnowledge;
import utils.Text;

public class FightOptions extends AGameState {

	@Override
	public void handleGameStateChange() {

		handleTextDrawToShow();
		Text.INSTANCE.showText(EText.RESOLVE_FIGHT);
		AbilitiesManager.INSTANCE.setUpResolveAbilities();

	}

	@Override
	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

		if (!AbilitiesManager.INSTANCE.getCardAbilitiesCanBeResolvedNonCopy().contains(cardFighting))
			if (!AbilitiesManager.INSTANCE.getCardAbilitiesCanBeResolvedOnlyCopy().contains(cardFighting))
				return;

		Text.INSTANCE.concealText();

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

		case DRAW_CARD_ONE_LIFE:
			Life.INSTANCE.loseLife(1);
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

				Text.INSTANCE.showText(EText.DRAW_CARD_ONE_LIFE);
				break;

			}

			if (cardSlot.containsFreeCard())
				Text.INSTANCE.showText(EText.DRAW_CARD_FREE);
			else if (Life.INSTANCE.getLifeCurrent() > 0)
				Text.INSTANCE.showText(EText.DRAW_CARD_ONE_LIFE);

			break;

		}

	}

}
