package gameState;

import controller.Flow;
import controller.Life;

public class PlusOneLife extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (Life.INSTANCE.getLifeCurrent() < Life.INSTANCE.getLifeTotal())
			Life.INSTANCE.gainLife(1);

		Flow.INSTANCE.proceed();

	}

}
