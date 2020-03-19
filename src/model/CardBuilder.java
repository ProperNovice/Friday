package model;

import enums.EAbility;
import enums.EHazardValue;
import utils.ShutDown;

public class CardBuilder {

	private String fileName = null;
	private SideHazard sideHazard = null;
	private int fightingValue = 99;
	private EAbility eAbility = null;

	public CardBuilder() {

	}

	public CardBuilder fileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public CardBuilder sideKnowledge(int fightingValue) {
		this.fightingValue = fightingValue;
		return this;
	}

	public CardBuilder sideKnowledge(int fightingValue, EAbility eAbility) {
		this.fightingValue = fightingValue;
		this.eAbility = eAbility;
		return this;
	}

	public CardBuilder sideHazard(EHazardValue eHazardValue) {
		this.sideHazard = new SideHazard(eHazardValue);
		return this;
	}

	public CardFightingRobinsonStarting buildCardFightingRobinsonStarting() {

		if (this.fileName == null)
			ShutDown.INSTANCE.execute("not given fileName in CardFightingRobinsonStarting");
		else if (this.fightingValue == 99)
			ShutDown.INSTANCE.execute("not given fightingValue in CardFightingRobinsonStarting");

		SideKnowledge sideKnowledge = null;

		if (this.eAbility == null)
			sideKnowledge = new SideKnowledge(this.fightingValue, 1);
		else
			sideKnowledge = new SideKnowledgeAbility(this.fightingValue, 1, this.eAbility);

		return new CardFightingRobinsonStarting(this.fileName, sideKnowledge);

	}

	public CardFightingHazardKnowledge buildCardFightingHazardKnowledge() {

		if (this.fileName == null)
			ShutDown.INSTANCE.execute("not given fileName in CardFightingHazardKnowledge");
		else if (this.fightingValue == 99)
			ShutDown.INSTANCE.execute("not given fightingValue in CardFightingHazardKnowledge");
		else if (this.sideHazard == null)
			ShutDown.INSTANCE.execute("not given sideHazard in CardFightingHazardKnowledge");

		SideKnowledge sideKnowledge = null;

		if (this.eAbility == null)
			sideKnowledge = new SideKnowledge(this.fightingValue, 1);
		else
			sideKnowledge = new SideKnowledgeAbility(this.fightingValue, 1, this.eAbility);

		return new CardFightingHazardKnowledge(this.fileName, sideKnowledge, this.sideHazard);

	}

}
