package enums;

import gameState.*;

public enum EGameState {

	START_GAME(new StartGame()),
	END_GAME(new EndGame()),
	DRAW_HAZARD_CARDS(new DrawHazardCards()),
	CHOOSE_HAZARD_TO_FIGHT(new ChooseHazardToFight()),

	;

	private AGameState gameState = null;

	private EGameState(AGameState gameState) {
		this.gameState = gameState;
	}

	public AGameState getGameState() {
		return this.gameState;
	}

}
