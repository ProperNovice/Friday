package controller;

import model.LifeToken;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.Logger;
import utils.RearrangeTypeEnum;

public enum Life {

	INSTANCE;

	private ContainerImageViewAbles<LifeToken> lifeTokens;
	private int lifeCurrent = 20, lifeTotal = 22;

	private Life() {

	}

	public void instantiate() {

		createList();
		createLifeTokens();
		handleLifeTokensFlipSide();

	}

	private void createList() {

		this.lifeTokens = new ContainerImageViewAbles<LifeToken>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsLifeToken)
						.gapX(Credentials.INSTANCE.gapBetweenLifeTokens).gapY(0)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesLifeTokensPivot)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).objectsPerRow(11).build());

	}

	private void createLifeTokens() {

		for (int counter = 1; counter <= 22; counter++)
			this.lifeTokens.getArrayList().addLast(new LifeToken());

		this.lifeTokens.relocateImageViews();

	}

	public int getLifeCurrent() {
		return this.lifeCurrent;
	}

	public int getLifeTotal() {
		return this.lifeTotal;
	}

	public void loseLife(int lifeToLose) {

		Logger.INSTANCE.log("current life -> " + this.lifeCurrent);
		Logger.INSTANCE.log("life lost -> " + lifeToLose);

		this.lifeCurrent -= lifeToLose;
		handleLifeTokensFlipSide();

		Logger.INSTANCE.logNewLine("current life -> " + this.lifeCurrent);

	}

	public void gainLife(int lifeToGain) {

		Logger.INSTANCE.log("current life -> " + this.lifeCurrent);
		Logger.INSTANCE.log("life gain -> " + lifeToGain);

		this.lifeCurrent += lifeToGain;
		handleLifeTokensFlipSide();

		Logger.INSTANCE.logNewLine("current life -> " + this.lifeCurrent);

	}

	private void handleLifeTokensFlipSide() {

		for (LifeToken lifeToken : this.lifeTokens)
			if (this.lifeTokens.getArrayList().indexOf(lifeToken) < this.lifeCurrent)
				lifeToken.getImageView().flipFront();
			else
				lifeToken.getImageView().flipBack();

	}

}
