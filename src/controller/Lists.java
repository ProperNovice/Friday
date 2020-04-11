package controller;

import card.CardFighting;
import card.CardFightingAging;
import card.CardFightingHazardKnowledge;
import card.CardPirate;
import card.CardStep;
import difficultyLevel.DifficultyLevel;
import enums.EStep;
import interfaces.ISaveLoadStateAble;
import model.HandPlayer;
import model.SortCardsPanel;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.DirectionEnum;
import utils.Logger;
import utils.RearrangeTypeEnum;

public enum Lists implements ISaveLoadStateAble {

	INSTANCE;

	public ArrayList<ISaveLoadStateAble> iSaveLoadStateAbles = new ArrayList<ISaveLoadStateAble>();
	public ContainerImageViewAbles<CardFighting> deckPlayer, discardPilePlayer;
	public ContainerImageViewAbles<CardStep> deckStep;
	public ContainerImageViewAbles<CardFightingAging> deckAging;
	public ContainerImageViewAbles<CardFightingHazardKnowledge> deckHazardKnowledge, discardPileHazardKnowledge,
			cardsHazardsDrawn;
	public ContainerImageViewAbles<CardPirate> cardPiratesInPlay;
	public HandPlayer handPlayer = HandPlayer.INSTANCE;
	public SortCardsPanel sortCardsPanel;

	public void instantiate() {

		createLists();
		ListsCheckRealTime.INSTANCE.start();

	}

	public void populateLists(DifficultyLevel difficultyLevel) {

		populateDeckPlayer(difficultyLevel);
		populateDeckHazardKnowledge(difficultyLevel);
		populateDeckStep();

		populateDeckAging(difficultyLevel);
		Modifiers.INSTANCE.setAgingCardsStartingAmount(this.deckAging.getSize());

		populateDeckPirates(difficultyLevel);

		this.sortCardsPanel = SortCardsPanel.INSTANCE;

		this.deckPlayer.relocateImageViews();
		this.deckHazardKnowledge.relocateImageViews();
		this.deckStep.relocateImageViews();
		this.deckAging.relocateImageViews();

		Logger.INSTANCE.logNewLine("lists instantiated -> " + this.iSaveLoadStateAbles.size());

	}

	private void createLists() {

		// deckPlayer

		this.deckPlayer = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckPlayer)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deckHazardKnowledge

		this.deckHazardKnowledge = new ContainerImageViewAbles<CardFightingHazardKnowledge>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckHazardKnowledge)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deckStep

		this.deckStep = new ContainerImageViewAbles<CardStep>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckStep)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deckAging

		this.deckAging = new ContainerImageViewAbles<CardFightingAging>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckAging)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// discardPilePlayer

		this.discardPilePlayer = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDiscardPilePlayer)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// discardPileHazardKnowledge

		this.discardPileHazardKnowledge = new ContainerImageViewAbles<CardFightingHazardKnowledge>(
				new CoordinatesBuilder()
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDiscardPileHazardKnowledge)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// cardsHazardDrawn

		this.cardsHazardsDrawn = new ContainerImageViewAbles<CardFightingHazardKnowledge>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesCardsHazardsDrawn)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).build());

		// deckPirates

		this.cardPiratesInPlay = new ContainerImageViewAbles<CardPirate>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardStepPirate)
						.directionEnumVertical(DirectionEnum.UP)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckPirates).objectsPerRow(1).build());

	}

	private void populateDeckPlayer(DifficultyLevel difficultyLevel) {

		this.deckPlayer.getArrayList().addAll(difficultyLevel.getDeckPlayer());

		this.deckPlayer.getArrayList().shuffle();
		this.deckPlayer.toFront();

		for (CardFighting cardFighting : this.deckPlayer) {
			cardFighting.getImageView().setVisible(true);
			cardFighting.getImageView().flipBack();
		}

	}

	private void populateDeckHazardKnowledge(DifficultyLevel difficultyLevel) {

		this.deckHazardKnowledge.getArrayList().addAll(difficultyLevel.getDeckHazardKnowledge());

		this.deckHazardKnowledge.getArrayList().shuffle();
		this.deckHazardKnowledge.toFront();

		for (CardFighting cardFighting : this.deckHazardKnowledge) {
			cardFighting.getImageView().setVisible(true);
			cardFighting.getImageView().flipBack();
		}

	}

	private void populateDeckStep() {

		this.deckStep.getArrayList().addLast(new CardStep("Green Card", EStep.GREEN));
		this.deckStep.getArrayList().addLast(new CardStep("Yellow Card", EStep.YELLOW));
		this.deckStep.getArrayList().addLast(new CardStep("Red Card", EStep.RED));

		this.deckStep.toFront();

	}

	private void populateDeckAging(DifficultyLevel difficultyLevel) {

		this.deckAging.getArrayList().addAll(difficultyLevel.getDeckAging());

		this.deckAging.getArrayList().shuffle();
		this.deckAging.toFront();

		for (CardFighting cardFighting : this.deckAging) {
			cardFighting.getImageView().setVisible(true);
			cardFighting.getImageView().flipBack();
		}

	}

	private void populateDeckPirates(DifficultyLevel difficultyLevel) {

		this.cardPiratesInPlay.getArrayList().addAll(difficultyLevel.getTwoPirates());
		this.cardPiratesInPlay.relocateImageViews();

		for (CardPirate cardPirate : this.cardPiratesInPlay)
			cardPirate.getImageView().setVisible(true);

	}

	@Override
	public void saveGameStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.saveGameStart();

	}

	@Override
	public void loadGameStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.loadGameStart();

	}

	@Override
	public void saveState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.saveState();

	}

	@Override
	public void loadState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.loadState();

	}

}
