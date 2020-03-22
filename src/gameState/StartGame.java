package gameState;

import controller.Flow;
import enums.EGameState;

public class StartGame extends AGameState {

	@Override
	public void handleGameStateChange() {
		
		Flow.INSTANCE.addLast(EGameState.DRAW_HAZARD_CARDS);
		Flow.INSTANCE.addLast(EGameState.CHOOSE_HAZARD_TO_FIGHT);
		Flow.INSTANCE.proceed();

	}

}
