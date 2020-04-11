package card;

public class CardFightingPirateProxy extends CardFighting {

	public CardFightingPirateProxy(String fileName, SideKnowledge sideKnowledge) {
		super(fileName, sideKnowledge);
	}

	@Override
	protected String getFolder() {
		return "";
	}

}
