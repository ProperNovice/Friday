package gameState;

import controller.FightingPoints;
import controller.Flow;
import enums.EGameState;

public class FightResolve extends AGameState {

	@Override
	public void handleGameStateChange() {

		handleAgingAbilities();

		if (FightingPoints.INSTANCE.getPlayerFightingPointsWithDouble() >= FightingPoints.INSTANCE
				.getEncounterFightingPoints())
			fightWon();

		else
			fightLost();

	}

	private void handleAgingAbilities() {

	}

	private void fightWon() {
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_WON);
	}

	private void fightLost() {
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_LOST);
	}

}
