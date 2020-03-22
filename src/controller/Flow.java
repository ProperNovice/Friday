package controller;

import enums.EGameState;
import gameState.AGameState;
import utils.ArrayList;
import utils.Logger;

public enum Flow {

	INSTANCE;

	private EGameState currentGameState = null;
	private ArrayList<EGameState> flow = new ArrayList<>();

	private Flow() {
		createTurns();
	}

	public void proceed() {

		this.currentGameState = this.flow.removeFirst();
		executeGameState();

	}

	public void executeGameState(EGameState eGameState) {

		this.currentGameState = eGameState;
		executeGameState();

	}

	private void createTurns() {

	}

	private void executeGameState() {

		Logger.INSTANCE.log("executing gamestate");
		Logger.INSTANCE.logNewLine(this.currentGameState);

		this.currentGameState.getGameState().handleGameStateChange();

	}

	public AGameState getCurrentGameState() {
		return this.currentGameState.getGameState();
	}

	public void addLast(EGameState eGameState) {
		this.flow.addLast(eGameState);
	}

}
