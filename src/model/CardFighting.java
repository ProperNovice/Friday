package model;

import interfaces.ISideKnowledgeAble;
import utils.Logger;

public abstract class CardFighting extends Card implements ISideKnowledgeAble {

	private SideKnowledge sideKnowledge = null;

	public CardFighting(String fileName, SideKnowledge sideKnowledge) {
		super(fileName);
		this.sideKnowledge = sideKnowledge;
	}

	@Override
	public SideKnowledge getSideKnowledge() {
		return this.sideKnowledge;
	}

	@Override
	public final void print() {

		String seperator = "*************";

		Logger.INSTANCE.log(seperator);
		Logger.INSTANCE.logNewLine("printing card");
		printCredentials();
		Logger.INSTANCE.log(seperator);
		Logger.INSTANCE.newLine();

	}

	protected void printCredentials() {
		this.sideKnowledge.print();
	}

	@Override
	protected void createImage(String fileName) {

		super.createImage(fileName);
		this.getImageView().setBack("Card Back.png");

	}

}
