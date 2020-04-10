package gameState;

public abstract class EndGame extends AGameState {

	@Override
	public void handleGameStateChange() {

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
