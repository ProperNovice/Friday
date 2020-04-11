package card;

import utils.Logger;

public class SidePirate {

	private int freeCards, fightingValue;

	public SidePirate(int freeCards, int fightingValue) {
		this.freeCards = freeCards;
		this.fightingValue = fightingValue;
	}

	public void print() {

		Logger.INSTANCE.log("Side pirate");

		if (this.freeCards != 99)
			Logger.INSTANCE.log("Free cards -> " + this.freeCards);

		if (this.fightingValue != 99)
			Logger.INSTANCE.log("Fighting value -> " + this.fightingValue);

	}

	public int getFreeCards() {
		return this.freeCards;
	}

	public int getFightingValue() {
		return this.fightingValue;
	}

}
