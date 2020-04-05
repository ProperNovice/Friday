package model;

import java.util.Iterator;

import controller.Credentials;
import controller.Modifiers;
import utils.ArrayList;
import utils.Coordinates;
import utils.CoordinatesBuilder;

public enum HandPlayer implements Iterable<CardSlot> {

	INSTANCE;

	private Coordinates coordinates = null;
	private ArrayList<CardSlot> cardSlots = new ArrayList<CardSlot>();

	private HandPlayer() {
		createCoordinates();
		createList();
	}

	private void createCoordinates() {

		this.coordinates = new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
				.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesHandPlayer)
				.gapY(-Credentials.INSTANCE.DimensionsCardFighting.y / 2).objectsPerRow(10).build();

	}

	private void createList() {

		for (int counter = 0; counter <= 19; counter++)
			this.cardSlots.addLast(new CardSlot(this.coordinates.getCoordinate(counter)));

	}

	public ArrayList<CardSlot> getCardSlots() {
		return this.cardSlots;
	}

	public int sizeNotDestroyed() {

		int size = 0;

		for (CardSlot cardSlot : this.cardSlots) {

			if (!cardSlot.containsCardFighting())
				continue;

			if (cardSlot.getCardFighting() == Modifiers.INSTANCE.getCardFightingAgainst())
				continue;

			if (cardSlot.getCardFighting().getImageView().isFlippedBack())
				continue;

			size++;

		}

		return size;

	}

	public boolean contains(CardFighting cardFighting) {

		for (CardSlot cardSlot : this.cardSlots)
			if (cardSlot.containsCardFighting())
				if (cardFighting.equals(cardFighting))
					return true;

		return false;

	}

	@Override
	public Iterator<CardSlot> iterator() {
		return this.cardSlots.iterator();
	}

}
