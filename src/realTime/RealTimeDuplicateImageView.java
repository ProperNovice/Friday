package realTime;

import card.Card;
import card.CardFighting;
import card.CardSlot;
import controller.Lists;
import javafx.animation.AnimationTimer;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.Logger;
import utils.ShutDown;

public enum RealTimeDuplicateImageView {

	INSTANCE;

	private RealTimeDuplicateImageView() {

	}

	public void start() {
		new Update().start();
	}

	private class Update extends AnimationTimer {

		private ArrayList<Card> cards = new ArrayList<Card>();

		@Override
		public void handle(long arg0) {

			this.cards.clear();

			checkList(Lists.INSTANCE.deckPlayer);
			checkList(Lists.INSTANCE.discardPilePlayer);
			checkList(Lists.INSTANCE.deckHazardKnowledge);
			checkList(Lists.INSTANCE.discardPileHazardKnowledge);
			checkList(Lists.INSTANCE.deckAging);
			checkList(Lists.INSTANCE.cardsHazardsDrawn);
			checkHand();

		}

		private void checkHand() {

			for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

				if (!cardSlot.containsCardFighting())
					continue;

				CardFighting cardFighting = cardSlot.getCardFighting();
				checkCard(cardFighting);

			}

		}

		private void checkList(ContainerImageViewAbles<? extends Card> list) {

			for (Card card : list.getArrayList())
				checkCard(card);

		}

		private void checkCard(Card card) {

			if (!cards.contains(card))
				this.cards.addLast(card);

			else {
				Logger.INSTANCE.newLine();
				Logger.INSTANCE.log("found duplicate card");
				Logger.INSTANCE.log(card.getClass());
				card.print();
				ShutDown.INSTANCE.execute();
			}

		}

	}

}
