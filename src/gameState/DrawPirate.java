package gameState;

import enums.EText;

public class DrawPirate extends AGameState {

	@Override
	public void handleGameStateChange() {

		EText.PIRATE_ENCOUNTER.showText();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
