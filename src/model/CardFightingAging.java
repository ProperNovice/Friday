package model;

public class CardFightingAging extends CardFighting {

	public CardFightingAging(String fileName, SideKnowledge sideKnowledge) {
		super(fileName, sideKnowledge);
	}

	@Override
	protected String getFolder() {
		return null;
	}

}
