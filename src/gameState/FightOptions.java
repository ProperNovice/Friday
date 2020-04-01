package gameState;

import controller.AbilitiesCanBeResolved;
import controller.Flow;
import controller.Life;
import controller.Lists;
import controller.Modifiers;
import enums.EAbility;
import enums.EGameState;
import enums.EText;
import interfaces.IAbilityAble;
import model.CardFighting;
import model.CardSlot;
import model.SideKnowledge;
import utils.Text;

public class FightOptions extends AGameState {

	@Override
	public void handleGameStateChange() {

		handleETextToShow();
		Text.INSTANCE.showText(EText.RESOLVE_FIGHT);
		handleResolveAbilityText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case DRAW_CARD_FREE:
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
			break;

		case DRAW_CARD_ONE_LIFE:
			Life.INSTANCE.loseLife(1);
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
			break;

		default:
			break;

		}

	}

	private void handleETextToShow() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (cardSlot.containsCardFighting())
				continue;

			if (cardSlot.containsFreeCard())
				Text.INSTANCE.showText(EText.DRAW_CARD_FREE);
			else if (Life.INSTANCE.getLifeCurrent() > 0)
				Text.INSTANCE.showText(EText.DRAW_CARD_ONE_LIFE);

			break;

		}

	}

	private void handleResolveAbilityText() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (cardFighting == Modifiers.INSTANCE.getCardFightingAgainst())
				continue;

			SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();

			if (!(sideKnowledge instanceof IAbilityAble))
				continue;

			IAbilityAble iAbilityAble = (IAbilityAble) sideKnowledge;
			EAbility eAbility = iAbilityAble.getEAbility();

			AbilitiesCanBeResolved.INSTANCE.canBeResolved(cardFighting, eAbility);

		}

	}

}
