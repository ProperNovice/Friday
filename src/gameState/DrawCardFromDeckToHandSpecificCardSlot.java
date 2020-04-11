package gameState;

import card.CardSlot;

public class DrawCardFromDeckToHandSpecificCardSlot extends ADrawCardFromDeckToHand {

	private CardSlot cardSlot = null;

	public void setExchangeCardSlot(CardSlot cardSlot) {
		this.cardSlot = cardSlot;
	}

	@Override
	protected CardSlot getCardSlotToAddTheCard() {
		return this.cardSlot;
	}

}
