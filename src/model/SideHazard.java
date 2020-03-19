package model;

import enums.EHazardValue;
import enums.EStep;
import utils.Logger;

public class SideHazard {

	private EHazardValue eHazardValue = null;

	public SideHazard(EHazardValue eHazardValue) {
		this.eHazardValue = eHazardValue;
	}

	public EHazardValue getEHazardValue() {
		return this.eHazardValue;
	}

	public void print() {

		Logger.INSTANCE.newLine();
		Logger.INSTANCE.log("Side hazard");
		Logger.INSTANCE.log("Free cards -> " + this.eHazardValue.getFreeCards());
		Logger.INSTANCE.log("Green step -> " + this.eHazardValue.getStepValue(EStep.GREEN));
		Logger.INSTANCE.log("Yellow step -> " + this.eHazardValue.getStepValue(EStep.YELLOW));
		Logger.INSTANCE.log("Red step -> " + this.eHazardValue.getStepValue(EStep.RED));

	}

}
