package gameState;

import card.CardSlot;
import controller.Lists;
import utils.Logger;
import utils.ShutDown;

public class DrawCardFromDeckToHandFirstEmptySlot extends ADrawCardFromDeckToHand {

	@Override
	protected CardSlot getCardSlotToAddTheCard() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (!cardSlot.containsCardFighting())
				return cardSlot;

		Logger.INSTANCE.log("DrawCardFromDeckToHandFirstEmptySlot");
		Logger.INSTANCE.log("Didn't find CardSlot empty to return");
		Logger.INSTANCE.log("You shouldn't be here");
		ShutDown.INSTANCE.execute();

		return null;

	}

}
