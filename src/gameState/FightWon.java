package gameState;

import controller.Flow;
import controller.Lists;
import enums.EText;

public class FightWon extends FightEnded {

	@Override
	public void handleGameStateChange() {

		EText.FIGHT_WON.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		super.handleDestroyedCards();
		super.putCardsToDeckPreviouslyOwned(false);
		super.addHazardFightingAgainstToDiscardPile(Lists.INSTANCE.discardPilePlayer);
		Flow.INSTANCE.proceed();

	}

}
