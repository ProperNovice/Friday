package gameState;

import controller.Flow;
import controller.Modifiers;
import enums.EGameState;

public class HandleNextEncounter extends AGameState {

	@Override
	public void handleGameStateChange() {

		EGameState eGameState = null;

		switch (Modifiers.INSTANCE.getEStep()) {

		case PIRATES:
			eGameState = null;
			break;

		default:
			eGameState = EGameState.DRAW_HAZARD_CARDS;
			break;

		}

		Flow.INSTANCE.executeGameState(eGameState);

	}

}
