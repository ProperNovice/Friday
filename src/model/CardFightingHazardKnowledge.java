package model;

import interfaces.ISideHazardAble;

public class CardFightingHazardKnowledge extends CardFighting implements ISideHazardAble {

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

	@Override
	public SideHazard getSideHazard() {
		return this.sideHazard;
	}

}
