package controller;

import utils.TextIndicator;

public enum DestroyCardLifeIndicator {

	INSTANCE;

	private TextIndicator textIndicator = new TextIndicator();
	private int life;

	private DestroyCardLifeIndicator() {

		this.textIndicator.setHeight(Credentials.INSTANCE.textHeight);
		this.textIndicator.relocateTopLeft(Credentials.INSTANCE.CoordinatesTextIndicators);
		this.textIndicator.setVisible(false);

	}

	public void setIndicatorLifeToLoseSetText(int life) {
		this.life = life;
		setText();
	}

	public int getLifeRemaining() {
		return this.life;
	}

	private void setText() {

		this.textIndicator.setVisible(true);
		this.textIndicator.setText("Life remaining: " + this.life);

	}

	public void setVisibleIndicatorFalse() {
		this.textIndicator.setVisible(false);
	}

}
