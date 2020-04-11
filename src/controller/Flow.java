package controller;

import enums.EGameState;
import gameState.AGameState;
import utils.ArrayList;
import utils.Logger;

public enum Flow {

	INSTANCE;

	private EGameState currentGameState = null;
	private ArrayList<EGameState> flow = new ArrayList<>();
	private ArrayList<EGameState> turn = new ArrayList<>();

	private Flow() {
		createTurns();
		this.flow.addFirst(EGameState.CHOOSE_DIFFICULTY_LEVEL);
	}

	public void proceed() {

		if (this.flow.isEmpty())
			this.flow.addAll(this.turn);

		this.currentGameState = this.flow.removeFirst();
		executeGameState();

	}

	public void executeGameState(EGameState eGameState) {

		this.currentGameState = eGameState;
		executeGameState();

	}

	private void createTurns() {

		this.turn.addLast(EGameState.DRAW_NEXT_ENCOUNTER);
		this.turn.addLast(EGameState.FIGHT_OPTIONS);
		this.turn.addLast(EGameState.FIGHT_RESOLVE);
		this.turn.addLast(EGameState.END_TURN);
		
	}

	private void executeGameState() {

		Logger.INSTANCE.log("executing gamestate");
		Logger.INSTANCE.logNewLine(this.currentGameState);

		this.currentGameState.getGameState().handleGameStateChange();

		boolean fightingPointCalculate = this.currentGameState.getGameState().fightingPointsCalculate();

		if (fightingPointCalculate)
			FightingPoints.INSTANCE.setFightingPointsUpdateIndicator();
		else
			FightingPoints.INSTANCE.setVisibleIndicatorFalse();

	}

	public AGameState getCurrentGameState() {
		return this.currentGameState.getGameState();
	}

	public void addFirst(EGameState eGameState) {
		this.flow.addFirst(eGameState);
	}

	public void addLast(EGameState eGameState) {
		this.flow.addLast(eGameState);
	}

	public void clear() {
		this.flow.clear();
	}

}
