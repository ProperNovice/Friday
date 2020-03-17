package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Friday";
	public NumbersPair DimensionsFrame, DimensionsInsets, DimensionsGapBetweenBorders, DimensionsGapBetweenComponents,
			DimensionsNumberImageView;
	public NumbersPair CoordinatesTextPanel;
	public double textHeight;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1366, 788);
		this.DimensionsFrame = new NumbersPair(1920, 1080);
		this.DimensionsInsets = new NumbersPair(7, 29);
		this.DimensionsGapBetweenBorders = new NumbersPair(20, 20);
		this.DimensionsGapBetweenComponents = new NumbersPair(4, 4);
		this.DimensionsNumberImageView = new NumbersPair(100, 100);

		this.CoordinatesTextPanel = new NumbersPair(x, y);

		this.textHeight = 50;

	}

}
