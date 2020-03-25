package model;

import controller.Credentials;
import utils.Logger;

public class CardPirate extends Card {

	private SidePirate sidePirate = null;

	public CardPirate(String fileName, SidePirate sidePirate) {
		super(fileName);
		this.sidePirate = sidePirate;
	}

	@Override
	protected String getFolder() {
		return "pirates/";
	}

	@Override
	protected double getWidth() {
		return Credentials.INSTANCE.DimensionsCardStepPirate.x;
	}

	@Override
	public void print() {

		String seperator = "*************";

		Logger.INSTANCE.log(seperator);
		Logger.INSTANCE.logNewLine("printing card");
		this.sidePirate.print();
		Logger.INSTANCE.log(seperator);
		Logger.INSTANCE.newLine();

	}

	public SidePirate getSidePirate() {
		return this.sidePirate;
	}

}
