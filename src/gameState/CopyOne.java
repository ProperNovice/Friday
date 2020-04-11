package gameState;

import card.CardFighting;
import card.SideKnowledge;
import controller.AbilitiesManager;
import controller.Modifiers;
import enums.EAbility;
import enums.EText;
import interfaces.IAbilityAble;
import utils.Text;

public class CopyOne extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.CHOOSE_CARD.showText();

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
		AbilitiesManager.INSTANCE.resolveAbilityCardProceed(cardFighting);

	}

}
