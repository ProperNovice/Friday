package model;

import enums.ECardAgingType;
import utils.Logger;

public class CardFightingAging extends CardFighting {

	private ECardAgingType eCardAgingType = null;

	public CardFightingAging(String fileName, SideKnowledge sideKnowledge, ECardAgingType eCardAgingType) {
		super(fileName, sideKnowledge);
		this.eCardAgingType = eCardAgingType;
	}

	@Override
	protected String getFolder() {
		return "aging/";
	}

	public ECardAgingType getECardAgingType() {
		return this.eCardAgingType;
	}

	@Override
	protected void printCredentials() {
		super.printCredentials();
		Logger.INSTANCE.log("Type -> " + this.eCardAgingType);
	}

}
