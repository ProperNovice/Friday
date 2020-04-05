package gameState;

import controller.Flow;
import enums.EText;

public class ExchangeOneSecond extends ExchangeOneFirst {

	@Override
	public void handleGameStateChange() {

		super.handleGameStateChange();
		EText.CONTINUE.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {
		Flow.INSTANCE.proceed();
	}

}
