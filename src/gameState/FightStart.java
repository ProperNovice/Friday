package gameState;

import controller.Flow;
import enums.EGameState;
import enums.EText;
import utils.Text;

public class FightStart extends AGameState {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.DRAW_CARD_FREE);

	}

	@Override
	protected void executeTextOption(EText eText) {
		Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
	}

}
