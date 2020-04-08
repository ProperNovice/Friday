package gameState;

import controller.FightingPoints;
import controller.Flow;
import enums.EGameState;

public class FightResolve extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (FightingPoints.INSTANCE.getPlayerFightingPointsWithDouble() >= FightingPoints.INSTANCE
				.getHazardFightingPoints())
			fightWon();

		else
			fightLost();

	}

	private void fightWon() {
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_WON);
	}

	private void fightLost() {
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_LOST);
	}

}
