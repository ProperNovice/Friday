package controller;

import utils.NumbersPair;

public enum Credentials {

	INSTANCE;

	public String primaryStageTitle = "Friday";
	public NumbersPair DimensionsFrame, DimensionsGapBetweenComponents, DimensionsCardFighting,
			DimensionsCardStepPirate, DimensionsIndicatorFreeCard, DimensionsLifeToken, DimensionsAbilityImageView,
			DimensionsSortCardPanel;
	public NumbersPair CoordinatesTextPanel, CoordinatesDeckPlayer, CoordinatesDeckHazardKnowledge, CoordinatesDeckStep,
			CoordinatesDeckAging, CoordinatesDiscardPilePlayer, CoordinatesDiscardPileHazardKnowledge,
			CoordinatesIndicatorFreeCard, CoordinatesHandPlayer, CoordinatesCardsHazardsDrawn, CoordinatesHazardToFight,
			CoordinatesLifeTokensTopLeft, CoordinatesLifeTokensPivot, CoordinatesDeckPirates,
			CoordinatesAbilityImageView, CoordinatesSortCardPanelBackground, CoordinatesSortCardPanelCards,
			CoordinatesTextIndicators;
	private NumbersPair DimensionsCardFightingOriginal, DimensionsCardStepPirateOriginal;
	public double gapBetweenBorders = 25, textHeight = 50, gapBetweenLifeTokens;

	private Credentials() {

		double x = 0, y = 0;

		this.DimensionsFrame = new NumbersPair(1920, 1080);
		this.DimensionsGapBetweenComponents = new NumbersPair(8, 8);

		this.DimensionsCardFightingOriginal = new NumbersPair(200, 375);
		this.DimensionsCardStepPirateOriginal = new NumbersPair(375, 200);

		x = (this.DimensionsFrame.x - 2 * this.gapBetweenBorders - 9 * this.DimensionsGapBetweenComponents.x) / 10;
		y = this.DimensionsCardFightingOriginal.y * x / this.DimensionsCardFightingOriginal.x;
		this.DimensionsCardFighting = new NumbersPair(x, y);

		x = this.DimensionsCardFighting.x / 2;
		this.DimensionsAbilityImageView = new NumbersPair(x, x);

		x = this.DimensionsCardFighting.x / 2;
		y = this.DimensionsCardFighting.y / 4;
		this.CoordinatesAbilityImageView = new NumbersPair(x, y);

		y = (this.DimensionsCardFighting.y - this.DimensionsGapBetweenComponents.y) / 2;
		x = this.DimensionsCardStepPirateOriginal.x * y / this.DimensionsCardStepPirateOriginal.y;
		this.DimensionsCardStepPirate = new NumbersPair(x, y);

		y = 2 * this.gapBetweenBorders + this.DimensionsGapBetweenComponents.y + 6 * this.DimensionsCardFighting.y / 2;
		this.DimensionsFrame = new NumbersPair(1920, y);

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

		x = this.CoordinatesDeckAging.x + this.DimensionsCardFighting.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckAging.y;
		this.CoordinatesDeckStep = new NumbersPair(x, y);

		x = this.CoordinatesDeckStep.x + this.DimensionsCardStepPirate.x + this.DimensionsGapBetweenComponents.x;
		y = this.CoordinatesDeckStep.y;
		this.CoordinatesDeckPirates = new NumbersPair(x, y);

		x = this.CoordinatesDeckPlayer.x;
		y = this.CoordinatesDeckPlayer.y + this.DimensionsCardFighting.y + this.DimensionsGapBetweenComponents.y;
		this.CoordinatesHandPlayer = new NumbersPair(x, y);

		x = this.DimensionsCardFighting.x / 3;
		this.DimensionsIndicatorFreeCard = new NumbersPair(x, x);

		x = this.CoordinatesHandPlayer.x;
		y = this.CoordinatesHandPlayer.y;
		this.CoordinatesIndicatorFreeCard = new NumbersPair(x, y);

		x = this.CoordinatesDeckPirates.x + this.DimensionsCardStepPirate.x + this.DimensionsGapBetweenComponents.x;
		y = this.gapBetweenBorders;
		this.CoordinatesTextIndicators = new NumbersPair(x, y);

		x = this.CoordinatesTextIndicators.x;
		y = this.CoordinatesTextIndicators.y + this.textHeight;
		this.CoordinatesTextPanel = new NumbersPair(x, y);

		x = this.CoordinatesDeckHazardKnowledge.x + this.DimensionsCardFighting.x / 2;
		y = this.CoordinatesDeckHazardKnowledge.y + 3 * this.DimensionsCardFighting.y / 2
				+ this.DimensionsGapBetweenComponents.y;
		this.CoordinatesCardsHazardsDrawn = new NumbersPair(x, y);

		x = this.CoordinatesDeckHazardKnowledge.x;
		y = this.CoordinatesDeckHazardKnowledge.y + this.DimensionsCardFighting.y / 2;
		this.CoordinatesHazardToFight = new NumbersPair(x, y);

		y = this.DimensionsCardStepPirate.y / 2;
		this.DimensionsLifeToken = new NumbersPair(y, y);

		x = this.CoordinatesDeckStep.x;
		y = this.CoordinatesDeckStep.y + this.DimensionsCardStepPirate.y + this.DimensionsGapBetweenComponents.y;
		this.CoordinatesLifeTokensTopLeft = new NumbersPair(x, y);

		x = this.CoordinatesLifeTokensTopLeft.x + this.DimensionsCardStepPirate.x / 2;
		y = this.CoordinatesLifeTokensTopLeft.y + this.DimensionsCardStepPirate.y / 2;
		this.CoordinatesLifeTokensPivot = new NumbersPair(x, y);

		calculateGapBetweenLifeTokens();

		this.DimensionsSortCardPanel = new NumbersPair(656, 446);

		x = this.DimensionsFrame.x / 2;
		y = this.DimensionsFrame.y / 2;
		this.CoordinatesSortCardPanelBackground = new NumbersPair(x, y);

		x = this.CoordinatesSortCardPanelBackground.x;
		y = this.CoordinatesSortCardPanelBackground.y;
		this.CoordinatesSortCardPanelCards = new NumbersPair(x, y);

	}

	private void calculateGapBetweenLifeTokens() {

		this.gapBetweenLifeTokens = this.DimensionsCardStepPirate.x;
		this.gapBetweenLifeTokens -= this.DimensionsLifeToken.x;
		this.gapBetweenLifeTokens /= 10;
		this.gapBetweenLifeTokens = this.gapBetweenLifeTokens - this.DimensionsLifeToken.x;

	}

}
