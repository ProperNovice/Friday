package gameState;

import controller.Credentials;

public class SortThreeCards extends AGameState {

	@Override
	public void handleGameStateChange() {

		Credentials.INSTANCE.DimensionsCardFighting.print();

	}

}
