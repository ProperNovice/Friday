package gameState;

import controller.Flow;
import enums.EGameState;
import enums.EText;

public abstract class EndGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		getEText().showText();
		EText.RESTART.showText();
		
	}
	
	

	@Override
	protected void executeTextOption(EText eText) {

		Flow.INSTANCE.clear();
		Flow.INSTANCE.executeGameState(EGameState.CHOOSE_DIFFICULTY_LEVEL);
		
	}



	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}
	
	protected abstract EText getEText();

}
