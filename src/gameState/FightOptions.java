package gameState;

import controller.Flow;
import controller.Life;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import model.CardSlot;
import utils.Text;

public class FightOptions extends AGameState {

	@Override
	public void handleGameStateChange() {

		if (getLifeEText() != null)
			Text.INSTANCE.showText(getLifeEText());

		Text.INSTANCE.showText(EText.RESOLVE_FIGHT);

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case DRAW_CARD_FREE:
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
			break;

		case DRAW_CARD_ONE_LIFE:
			Life.INSTANCE.loseLife(1);
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
			break;

		default:
			break;

		}

	}

	private EText getLifeEText() {

		EText eText = null;

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (cardSlot.containsCardFighting())
				continue;

			if (cardSlot.containsFreeCard())
				eText = EText.DRAW_CARD_FREE;
			else if (Life.INSTANCE.getLifeCurrent() > 0)
				eText = EText.DRAW_CARD_ONE_LIFE;

			break;

		}

		return eText;

	}

}
