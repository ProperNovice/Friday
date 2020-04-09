package enums;

import utils.ShutDown;

public enum EHazardValue {

	ONE(1, 0, 1, 3), TWO(2, 1, 3, 6), THREE(3, 2, 5, 8), FOUR(4, 4, 7, 11), FIVE(5, 5, 9, 14);

	private int freeCards, greenStepValue, yellowStepValue, redStepValue;

	private EHazardValue(int freeCards, int greenStepValue, int yellowStepValue, int redStepValue) {
		this.freeCards = freeCards;
		this.greenStepValue = greenStepValue;
		this.yellowStepValue = yellowStepValue;
		this.redStepValue = redStepValue;
	}

	public int getFreeCards() {
		return this.freeCards;
	}

	public int getStepValue(EStep eStep) {

		int stepValue = -1;

		switch (eStep) {

		case GREEN:
			stepValue = this.greenStepValue;
			break;

		case YELLOW:
			stepValue = this.yellowStepValue;
			break;

		case RED:
			stepValue = this.redStepValue;
			break;

		case PIRATE:
			ShutDown.INSTANCE.execute("EHazardValue, PIRATES, you shouldn't be here");
			break;

		}

		return stepValue;

	}

}
