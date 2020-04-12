package gameState;

import controller.Flow;
import controller.Lists;
import enums.EGameState;
import enums.EText;

public abstract class EndGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		Lists.INSTANCE.clearLists();

		getEText().showText();
		EText.RESTART.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		Flow.INSTANCE.clear();
		Flow.INSTANCE.executeGameState(EGameState.CHOOSE_DIFFICULTY_LEVEL);

	}

	protected abstract EText getEText();

}
