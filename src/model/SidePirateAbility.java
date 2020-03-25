package model;

import enums.EAbility;
import interfaces.IAbilityAble;
import utils.Logger;

public class SidePirateAbility extends SidePirate implements IAbilityAble {

	private EAbility eAbility = null;

	public SidePirateAbility(int freeCards, int fightingValue, EAbility eAbility) {
		super(freeCards, fightingValue);
		this.eAbility = eAbility;
	}

	@Override
	public void print() {

		super.print();
		Logger.INSTANCE.log("ability -> " + this.eAbility);

	}

	@Override
	public EAbility getEAbility() {
		return this.eAbility;
	}

}
