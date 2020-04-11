package gameState;

import enums.EText;

public class EndGameLose extends EndGame {

	@Override
	protected EText getEText() {
		return EText.YOU_LOST;
	}

}
