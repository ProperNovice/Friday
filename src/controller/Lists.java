package controller;

import card.CardFighting;
import card.CardFightingAging;
import card.CardFightingHazardKnowledge;
import card.CardPirate;
import card.CardSlot;
import card.CardStep;
import difficultyLevel.DifficultyLevel;
import interfaces.ISaveLoadStateAble;
import model.DeckPanel;
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
	public ContainerImageViewAbles<CardPirate> deckPirates;
	public HandPlayer handPlayer = HandPlayer.INSTANCE;
	public SortCardsPanel sortCardsPanel;
	public DeckPanel deckPanel;

	public void instantiate() {

		createLists();

	}

	public void clearLists() {

		this.deckPlayer.getArrayList().clear();
		this.discardPilePlayer.getArrayList().clear();
		this.deckHazardKnowledge.getArrayList().clear();
		this.discardPileHazardKnowledge.getArrayList().clear();
		this.deckAging.getArrayList().clear();
		this.cardsHazardsDrawn.getArrayList().clear();
		this.deckPirates.getArrayList().clear();
		this.deckStep.getArrayList().clear();

		for (CardSlot cardSlot : this.handPlayer)
			cardSlot.removeCardFighting();

	}

	public void populateLists(DifficultyLevel difficultyLevel) {

		this.deckPlayer.getArrayList().clear();
		this.deckHazardKnowledge.getArrayList().clear();
		this.deckStep.getArrayList().clear();
		this.deckAging.getArrayList().clear();
		this.deckPirates.getArrayList().clear();

		populateDeckPlayer(difficultyLevel);
		populateDeckHazardKnowledge(difficultyLevel);
		populateDeckStep(difficultyLevel);

		populateDeckAging(difficultyLevel);
		Modifiers.INSTANCE.setAgingCardsStartingAmount(this.deckAging.getSize());

		populateDeckPirates(difficultyLevel);

		this.sortCardsPanel = SortCardsPanel.INSTANCE;
		this.deckPanel = DeckPanel.INSTANCE;

		this.deckPlayer.relocateImageViews();
		this.deckHazardKnowledge.relocateImageViews();
		this.deckStep.relocateImageViews();
		this.deckAging.relocateImageViews();
		this.deckPirates.relocateImageViews();

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

		this.deckPirates = new ContainerImageViewAbles<CardPirate>(
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

	private void populateDeckStep(DifficultyLevel difficultyLevel) {

		this.deckStep.getArrayList().addAll(difficultyLevel.getDeckStep());

		for (CardStep cardStep : this.deckStep)
			cardStep.getImageView().setVisible(true);

		this.deckStep.toFront();

	}

	private void populateDeckAging(DifficultyLevel difficultyLevel) {

		this.deckAging.getArrayList().addAll(difficultyLevel.getDeckAging());

		this.deckAging.toFront();

		for (CardFightingAging cardFightingAging : this.deckAging) {

			cardFightingAging.getImageView().setVisible(true);
			cardFightingAging.getImageView().flipBack();

		}

	}

	private void populateDeckPirates(DifficultyLevel difficultyLevel) {

		this.deckPirates.getArrayList().addAll(difficultyLevel.getTwoPirates());

		for (CardPirate cardPirate : this.deckPirates)
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
