package card;

import enums.EAbility;
import interfaces.IAbilityAble;
import utils.Logger;

public class SideKnowledgeAbility extends SideKnowledge implements IAbilityAble {

	private EAbility eAbility = null;

	public SideKnowledgeAbility(int fightingValue, int destroyingValue, EAbility eAbility) {
		super(fightingValue, destroyingValue);
		this.eAbility = eAbility;
	}

	@Override
	public EAbility getEAbility() {
		return this.eAbility;
	}

	@Override
	protected void printCredentials() {
		super.printCredentials();
		Logger.INSTANCE.log("ability -> " + this.eAbility);
	}

}
