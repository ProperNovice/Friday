package gameState;

import controller.Flow;
import controller.Modifiers;
import enums.EGameState;

public class DrawNextEncounter extends AGameState {

	@Override
	public void handleGameStateChange() {

		EGameState eGameState = null;

		switch (Modifiers.INSTANCE.getEStep()) {

		case PIRATE:
			eGameState = EGameState.DRAW_PIRATE;
			break;

		default:
			eGameState = EGameState.DRAW_HAZARD_CARDS;
			break;

		}

		Flow.INSTANCE.executeGameState(eGameState);

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
