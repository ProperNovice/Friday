package model;

import utils.Logger;

public class SideKnowledge {

	private int fightingValue, destroyingValue;

	public SideKnowledge(int fightingValue, int destroyingValue) {
		this.fightingValue = fightingValue;
		this.destroyingValue = destroyingValue;
	}

	public int getFightingValue() {
		return this.fightingValue;
	}

	public int getDestroyingValue() {
		return this.destroyingValue;
	}

	public void print() {
		printCredentials();
	}

	protected void printCredentials() {

		Logger.INSTANCE.log("Side knowledge");
		Logger.INSTANCE.log("fighting value -> " + this.fightingValue);
		Logger.INSTANCE.log("destroying value -> " + this.destroyingValue);

	}

}
