package gameState;

import controller.Flow;
import controller.Modifiers;
import enums.EGameState;
import enums.EText;

public abstract class EndGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		Modifiers.INSTANCE.getCardFightingPirateProxy().getImageView().setVisible(false);

		getEText().showText();
		EText.RESTART.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {
		Flow.INSTANCE.executeGameState(EGameState.RESTART_GAME);
	}

	protected abstract EText getEText();

}
