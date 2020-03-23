package model;

public class CardPirate extends Card {

	public CardPirate(String fileName) {
		super(fileName);
	}

	@Override
	protected String getFolder() {
		return "pirates/";
	}

	@Override
	public void print() {

	}

}
