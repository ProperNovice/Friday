package model;

import controller.Credentials;
import enums.EStep;
import utils.Logger;

public class CardStep extends Card {

	private EStep eStep = null;

	public CardStep(String fileName, EStep eStep) {
		super(fileName);
		this.eStep = eStep;
	}

	@Override
	protected String getFolder() {
		return "step/";
	}

	@Override
	protected double getWidth() {
		return Credentials.INSTANCE.DimensionsCardFighting.y;
	}

	@Override
	protected void print() {
		Logger.INSTANCE.logNewLine("Card step -> " + this.eStep);
	}

	public EStep getEStep() {
		return this.eStep;
	}

}
