package gameState;

import controller.Lists;
import model.CardSlot;
import utils.Logger;
import utils.ShutDown;

public class DrawCardFromDeckToHandRightSide extends ADrawCardFromDeckToHand {

	@Override
	protected CardSlot getCardSlotToAddTheCard() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots())
			if (!cardSlot.containsFreeCard())
				if (!cardSlot.containsCardFighting())
					return cardSlot;

		Logger.INSTANCE.log("DrawCardFromDeckToHandRightSide");
		Logger.INSTANCE.log("Didn't find CardSlot empty to return");
		Logger.INSTANCE.log("You shouldn't be here");
		ShutDown.INSTANCE.execute();

		return null;

	}

}
