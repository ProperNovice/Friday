package model;

public class CardFightingHazardKnowledge extends CardFighting {

	private SideHazard sideHazard = null;

	public CardFightingHazardKnowledge(String fileName, SideKnowledge sideKnowledge, SideHazard sideHazard) {
		super(fileName, sideKnowledge);
		this.sideHazard = sideHazard;
	}

	@Override
	protected void printCredentials() {
		super.printCredentials();
		this.sideHazard.print();
	}

	@Override
	protected String getFolder() {
		return "hazardKnowledge/";
	}

	public SideHazard getSideHazard() {
		return this.sideHazard;
	}

}
