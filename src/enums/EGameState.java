package enums;

import gameState.*;

public enum EGameState {

	START_GAME(new StartGame()),
	END_TURN(new EndTurn()),
	END_GAME(new EndGame()),
	DRAW_HAZARD_CARDS(new DrawHazardCards()),
	HANDLE_HAZARD_CARDS_DRAWN_ONE(new HandleHazardCardsDrawnOne()),
	HANDLE_HAZARD_CARDS_DRAWN_TWO(new HandleHazardCardsDrawnTwo()),
	CHOOSE_HAZARD_TO_FIGHT(new ChooseHazardToFight()),
	FIGHT_START(new FightStart()),

	;

	private AGameState gameState = null;

	private EGameState(AGameState gameState) {
		this.gameState = gameState;
	}

	public AGameState getGameState() {
		return this.gameState;
	}

}
