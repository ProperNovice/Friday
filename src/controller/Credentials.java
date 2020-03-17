package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Friday";
	public NumbersPair DimensionsFrame, DimensionsGapBetweenBorders, DimensionsGapBetweenComponents,
			DimensionsNumberImageView;
	public NumbersPair CoordinatesTextPanel;
	public double textHeight = 50;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1920, 1080);
		this.DimensionsGapBetweenBorders = new NumbersPair(20, 20);
		this.DimensionsGapBetweenComponents = new NumbersPair(4, 4);
		this.DimensionsNumberImageView = new NumbersPair(100, 100);

		this.CoordinatesTextPanel = new NumbersPair(x, y);

	}

}
