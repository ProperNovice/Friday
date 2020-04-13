package gameState;

import card.CardFighting;
import card.CardSlot;
import card.SideKnowledge;
import controller.AbilitiesManager;
import controller.AbilityImageViewList;
import controller.Lists;
import controller.Modifiers;
import enums.EAbility;
import enums.EText;
import interfaces.IAbilityAble;
import utils.Text;

public class CopyOne extends AGameState {

	@Override
	public void handleGameStateChange() {

		setAbilitiesImageViews();
		EText.CHOOSE_CARD.showText();

	}

	private void setAbilitiesImageViews() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (Modifiers.INSTANCE.getCardFightingAgainst().equals(cardFighting))
				return;

			SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();

			if (!(sideKnowledge instanceof IAbilityAble))
				return;

			IAbilityAble iAbilityAble = (IAbilityAble) sideKnowledge;
			EAbility eAbility = iAbilityAble.getEAbility();

			if (eAbility.equals(EAbility.COPY_ONE))
				return;

			if (!AbilitiesManager.INSTANCE.canBeResolved(cardFighting, eAbility))
				return;

			AbilityImageViewList.INSTANCE.setAbilityImageViewForCardFighting(cardFighting);

		}

	}

	@Override
	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

		if (Modifiers.INSTANCE.getCardFightingAgainst().equals(cardFighting))
			return;

		SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();

		if (!(sideKnowledge instanceof IAbilityAble))
			return;

		IAbilityAble iAbilityAble = (IAbilityAble) sideKnowledge;
		EAbility eAbility = iAbilityAble.getEAbility();

		if (eAbility.equals(EAbility.COPY_ONE))
			return;

		if (!AbilitiesManager.INSTANCE.canBeResolved(cardFighting, eAbility))
			return;

		Text.INSTANCE.concealText();
		AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();
		AbilitiesManager.INSTANCE.resolveAbilityCardProceed(cardFighting);

	}

}
