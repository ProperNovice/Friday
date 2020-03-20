package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Friday";
	public NumbersPair DimensionsFrame, DimensionsGapBetweenComponents, DimensionsCardFighting, DimensionsCardStep;
	public NumbersPair CoordinatesTextPanel, CoordinatesDeckPlayer, CoordinatesDeckHazardKnowledge, CoordinatesDeckStep,
			CoordinatesDeckAging, CoordinatesDiscardPilePlayer, CoordinatesDiscardPileHazardKnowledge;
	private NumbersPair DimensionsCardFightingOriginal;
	public double gapBetweenBorders = 25, textHeight = 50;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1920, 1080);
		this.DimensionsGapBetweenComponents = new NumbersPair(8, 8);

		this.CoordinatesTextPanel = new NumbersPair(x, y);

		this.DimensionsCardFightingOriginal = new NumbersPair(200, 375);

		y = (this.DimensionsFrame.y - 2 * this.gapBetweenBorders - 2 * this.DimensionsGapBetweenComponents.y) / 3;
		x = this.DimensionsCardFightingOriginal.x * y / this.DimensionsCardFightingOriginal.y;
		this.DimensionsCardFighting = new NumbersPair(x, y);

		x = this.DimensionsCardFighting.y;
		y = this.DimensionsCardFighting.x;
		this.DimensionsCardStep = new NumbersPair(x, y);

		x = 10 * this.DimensionsCardFighting.x + 2 * this.gapBetweenBorders + 9 * this.DimensionsGapBetweenComponents.x;
		this.DimensionsFrame = new NumbersPair(x, 1080);

		this.CoordinatesDeckPlayer = new NumbersPair(this.gapBetweenBorders, this.gapBetweenBorders);

		x = this.CoordinatesDeckPlayer.x + this.DimensionsCardFighting.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckPlayer.y;
		this.CoordinatesDiscardPilePlayer = new NumbersPair(x, y);

		x = this.CoordinatesDiscardPilePlayer.x + this.DimensionsCardFighting.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDiscardPilePlayer.y;
		this.CoordinatesDeckHazardKnowledge = new NumbersPair(x, y);

		x = this.CoordinatesDeckHazardKnowledge.x + this.DimensionsCardFighting.x
				+ this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckHazardKnowledge.y;
		this.CoordinatesDiscardPileHazardKnowledge = new NumbersPair(x, y);

		x = this.CoordinatesDiscardPileHazardKnowledge.x + this.DimensionsCardFighting.x
				+ this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDiscardPileHazardKnowledge.y;
		this.CoordinatesDeckAging = new NumbersPair(x, y);

		this.CoordinatesDeckStep = new NumbersPair(this.gapBetweenBorders, 500);

	}

}
