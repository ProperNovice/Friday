package gameState;

import enums.EText;

public class EndGameWin extends EndGame {

	@Override
	protected EText getEText() {
		return EText.YOU_WON;
	}

}
