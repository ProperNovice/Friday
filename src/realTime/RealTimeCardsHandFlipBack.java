package realTime;

import card.CardFighting;
import controller.Flow;
import controller.Lists;
import gameState.ADrawCardFromDeckToHand;
import gameState.AGameState;
import gameState.DrawCardFromDeckToHandFirstEmptySlot;
import gameState.DrawCardFromDeckToHandRightSide;
import gameState.DrawCardFromDeckToHandSpecificCardSlot;
import javafx.animation.AnimationTimer;
import utils.ArrayList;
import utils.ShutDown;

public enum RealTimeCardsHandFlipBack {

	INSTANCE;

	private ArrayList<Class<? extends AGameState>> updateList = new ArrayList<Class<? extends AGameState>>();

	private RealTimeCardsHandFlipBack() {

		this.updateList.addLast(ADrawCardFromDeckToHand.class);
		this.updateList.addLast(DrawCardFromDeckToHandFirstEmptySlot.class);
		this.updateList.addLast(DrawCardFromDeckToHandRightSide.class);
		this.updateList.addLast(DrawCardFromDeckToHandSpecificCardSlot.class);

	}

	public void start() {
		new Update().start();
	}

	private class Update extends AnimationTimer {

		@Override
		public void handle(long arg0) {

			if (updateList.contains(Flow.INSTANCE.getCurrentGameState().getClass()))
				return;

			for (CardFighting cardFighting : Lists.INSTANCE.deckPlayer) {

				if (cardFighting.getImageView().isFlippedBack())
					continue;

				cardFighting.print();
				ShutDown.INSTANCE.execute("found card in hand flip front");

			}

		}

	}

}
