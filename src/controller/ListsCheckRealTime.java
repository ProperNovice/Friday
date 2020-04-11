package controller;

import card.Card;
import card.CardSlot;
import javafx.animation.AnimationTimer;
import utils.ArrayList;
import utils.ContainerImageViewAbles;

public enum ListsCheckRealTime {

	INSTANCE;

	private ArrayList<ListSize> listsContainer = new ArrayList<ListsCheckRealTime.ListSize>();

	private ListsCheckRealTime() {

		this.listsContainer.addLast(new ListSize("deck player", Lists.INSTANCE.deckPlayer));
		this.listsContainer.addLast(new ListSize("discard pile player", Lists.INSTANCE.discardPilePlayer));
		this.listsContainer.addLast(new ListSize("deck haz", Lists.INSTANCE.deckHazardKnowledge));
		this.listsContainer.addLast(new ListSize("discard pile haz", Lists.INSTANCE.discardPileHazardKnowledge));
		this.listsContainer.addLast(new ListSize("deck aging", Lists.INSTANCE.deckAging));
		this.listsContainer.addLast(new ListSize("haz drawn", Lists.INSTANCE.cardsHazardsDrawn));

	}

	public void start() {
		new Timer().start();
	}

	private class ListSize {

		private String name;
		private int size;
		private ContainerImageViewAbles<? extends Card> container;

		public ListSize(String name, ContainerImageViewAbles<? extends Card> container) {
			this.name = name;
			this.container = container;
		}

		public void handle() {

			int newSize = this.container.getArrayList().size();

			if (newSize == this.size)
				return;

			System.out.println(this.name + ": " + this.size + " -> " + newSize);
			this.size = newSize;

		}

	}

	private class Timer extends AnimationTimer {

		private int size = 0;

		@Override
		public void handle(long now) {

			for (ListSize listSize : listsContainer)
				listSize.handle();

			printHand();

		}

		private void printHand() {

			int newSize = 0;

			for (CardSlot cardSlot : Lists.INSTANCE.handPlayer)
				if (cardSlot.containsCardFighting())
					newSize++;

			if (newSize == this.size)
				return;

			System.out.println("hand player: " + this.size + " -> " + newSize);
			this.size = newSize;

		}

	}

}
