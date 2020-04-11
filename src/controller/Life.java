package controller;

import difficultyLevel.DifficultyLevel;
import model.LifeToken;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.Logger;
import utils.RearrangeTypeEnum;

public enum Life {

	INSTANCE;

	private ContainerImageViewAbles<LifeToken> lifeTokensCurrent, lifeTokens20life, lifeTokens18life;
	private ArrayList<LifeToken> lifeTokens = new ArrayList<LifeToken>();
	private int lifeCurrent = 20, lifeTotal = 22;

	private Life() {

	}

	public void instantiate() {

		createLists();
		createLifeTokens();

	}

	public void setMaximumLife(DifficultyLevel difficultyLevel) {

		this.lifeCurrent = difficultyLevel.getLife();
		this.lifeTotal = this.lifeCurrent + 2;

		if (lifeCurrent == 20)
			this.lifeTokensCurrent = this.lifeTokens20life;
		else if (lifeCurrent == 18)
			this.lifeTokensCurrent = this.lifeTokens18life;

		this.lifeTokensCurrent.getArrayList().clear();

		for (int counter = 0; counter < this.lifeTotal; counter++)
			this.lifeTokensCurrent.getArrayList().addLast(this.lifeTokens.get(counter));

		this.lifeTokensCurrent.relocateImageViews();

		for (LifeToken lifeToken : this.lifeTokensCurrent.getArrayList())
			lifeToken.getImageView().setVisible(true);

		handleLifeTokensFlipSide();

	}

	private void createLists() {

		this.lifeTokens20life = new ContainerImageViewAbles<LifeToken>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsLifeToken)
						.gapX(Credentials.INSTANCE.gapBetweenLifeTokens).gapY(0)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesLifeTokensPivot)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).objectsPerRow(11).build());

		this.lifeTokens18life = new ContainerImageViewAbles<LifeToken>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsLifeToken)
						.gapX(Credentials.INSTANCE.gapBetweenLifeTokens).gapY(0)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesLifeTokensPivot)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).objectsPerRow(10).build());

		this.lifeTokensCurrent = this.lifeTokens20life;

	}

	private void createLifeTokens() {

		for (int counter = 1; counter <= 22; counter++)
			this.lifeTokens.addLast(new LifeToken());

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

		for (LifeToken lifeToken : this.lifeTokensCurrent)
			if (this.lifeTokensCurrent.getArrayList().indexOf(lifeToken) < this.lifeCurrent)
				lifeToken.getImageView().flipFront();
			else
				lifeToken.getImageView().flipBack();

	}

	public void setLifeTokensVisibleFalse() {
		for (LifeToken lifeToken : this.lifeTokens)
			lifeToken.getImageView().setVisible(false);
	}

}
