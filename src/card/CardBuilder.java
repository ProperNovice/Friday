package card;

import enums.EAbility;
import enums.ECardAgingType;
import enums.EHazardValue;
import utils.ShutDown;

public class CardBuilder {

	private String fileName = null;
	private SideHazard sideHazard = null;
	private SidePirate sidePirate = null;
	private int fightingValue = 99;
	private EAbility eAbility = null;
	private ECardAgingType eCardAgingType = null;

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

	public CardBuilder sidePirate(int freeCards, int fightingValue) {
		return sidePirate(freeCards, fightingValue, null);
	}

	public CardBuilder sidePirate(int freeCards, EAbility eAbility) {
		return sidePirate(freeCards, 99, eAbility);
	}

	public CardBuilder sidePirate(EAbility eAbility) {
		return sidePirate(99, 99, eAbility);
	}

	public CardBuilder sidePirate(int freeCards, int fightingValue, EAbility eAbility) {

		if (eAbility == null)
			this.sidePirate = new SidePirate(freeCards, fightingValue);
		else
			this.sidePirate = new SidePirateAbility(freeCards, fightingValue, eAbility);

		return this;

	}

	public CardBuilder eCardAgingType(ECardAgingType eCardAgingType) {
		this.eCardAgingType = eCardAgingType;
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

	public CardFightingAging buildCardFightingAging() {

		if (this.fileName == null)
			ShutDown.INSTANCE.execute("not given fileName in CardFightingAging");
		else if (this.fightingValue == 99)
			ShutDown.INSTANCE.execute("not given fightingValue in CardFightingAging");
		else if (this.eCardAgingType == null)
			ShutDown.INSTANCE.execute("not given eCardAgingType in CardFightingAging");

		SideKnowledge sideKnowledge = null;

		if (this.eAbility == null)
			sideKnowledge = new SideKnowledge(this.fightingValue, 2);
		else
			sideKnowledge = new SideKnowledgeAbility(this.fightingValue, 2, this.eAbility);

		return new CardFightingAging(this.fileName, sideKnowledge, this.eCardAgingType);

	}

	public CardPirate buildCardPirate() {

		if (this.fileName == null)
			ShutDown.INSTANCE.execute("not given fileName in CardPirate");

		return new CardPirate(this.fileName, this.sidePirate);

	}

}
