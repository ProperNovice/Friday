package gameState;

import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;

public class FightLost extends FightEnded {

	@Override
	public void handleGameStateChange() {

		EText.FIGHT_LOST.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		super.handleDestroyedCards();
		super.addHazardFightingAgainstToDiscardPile(Lists.INSTANCE.discardPileHazardKnowledge);
		Flow.INSTANCE.executeGameState(EGameState.HANDLE_FIGHT_LOST);

	}

}
