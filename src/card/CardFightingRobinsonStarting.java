package card;

public class CardFightingRobinsonStarting extends CardFighting {

	public CardFightingRobinsonStarting(String fileName, SideKnowledge sideKnowledge) {
		super(fileName, sideKnowledge);
	}

	@Override
	protected String getFolder() {
		return "basic/";
	}

}
