package controller;

import enums.EAbility;
import enums.ECardAgingType;
import enums.EHazardValue;
import enums.EStep;
import interfaces.ISaveLoadStateAble;
import model.CardBuilder;
import model.CardFighting;
import model.CardPirate;
import model.CardStep;
import model.HandPlayer;
import model.LifeToken;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.Logger;
import utils.RearrangeTypeEnum;

public enum Lists implements ISaveLoadStateAble {

	INSTANCE;

	public ArrayList<ISaveLoadStateAble> iSaveLoadStateAbles = new ArrayList<ISaveLoadStateAble>();
	public ContainerImageViewAbles<CardFighting> deckPlayer, deckHazardKnowledge, deckAging, discardPilePlayer,
			discardPileHazardKnowledge, cardsHazardsDrawn, hazardToFight;
	public ContainerImageViewAbles<CardStep> deckStep;
	public ContainerImageViewAbles<LifeToken> lifeTokens;
	public ArrayList<CardPirate> deckPirates = new ArrayList<CardPirate>();
	public ContainerImageViewAbles<CardPirate> cardPiratesInPlay;
	public HandPlayer handPlayer = HandPlayer.INSTANCE;

	public void instantiate() {

		createLists();
		createDeckPlayer();
		createDeckHazardKnowledge();
		createDeckStep();
		createDeckAging();
		createLifeTokens();
		createDeckPirates();

		this.deckPlayer.relocateImageViews();
		this.deckHazardKnowledge.relocateImageViews();
		this.deckStep.relocateImageViews();
		this.deckAging.relocateImageViews();
		this.lifeTokens.relocateImageViews();

		Logger.INSTANCE.logNewLine("lists instantiated -> " + this.iSaveLoadStateAbles.size());

	}

	private void createLists() {

		// deckPlayer

		this.deckPlayer = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckPlayer)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deckHazardKnowledge

		this.deckHazardKnowledge = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckHazardKnowledge)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deckStep

		this.deckStep = new ContainerImageViewAbles<CardStep>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckStep)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// deckAging

		this.deckAging = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckAging)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// discardPilePlayer

		this.discardPilePlayer = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDiscardPilePlayer)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// discardPileHazardKnowledge

		this.discardPileHazardKnowledge = new ContainerImageViewAbles<CardFighting>(new CoordinatesBuilder()
				.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDiscardPileHazardKnowledge)
				.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// cardsHazardDrawn

		this.cardsHazardsDrawn = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesCardsHazardsDrawn)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).build());

		// hazardToFight

		this.hazardToFight = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesHazardToFight)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

		// lifeTokens

		this.lifeTokens = new ContainerImageViewAbles<LifeToken>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsLifeToken)
						.gapX(Credentials.INSTANCE.gapBetweenLifeTokens).gapY(0)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesLifeTokens).objectsPerRow(11).build());

		// deckPirates

		this.cardPiratesInPlay = new ContainerImageViewAbles<CardPirate>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardStepPirate)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesDeckPirates).objectsPerRow(1).build());

	}

	private void createDeckPlayer() {

		for (int counter = 1; counter <= 5; counter++)
			this.deckPlayer.getArrayList().addLast(
					new CardBuilder().fileName("Distracted1").sideKnowledge(-1).buildCardFightingRobinsonStarting());

		for (int counter = 1; counter <= 8; counter++)
			this.deckPlayer.getArrayList()
					.addLast(new CardBuilder().fileName("Weak").sideKnowledge(0).buildCardFightingRobinsonStarting());

		for (int counter = 1; counter <= 3; counter++)
			this.deckPlayer.getArrayList().addLast(
					new CardBuilder().fileName("Focused").sideKnowledge(1).buildCardFightingRobinsonStarting());

		this.deckPlayer.getArrayList()
				.addLast(new CardBuilder().fileName("Genius").sideKnowledge(2).buildCardFightingRobinsonStarting());

		this.deckPlayer.getArrayList().addLast(new CardBuilder().fileName("Eating")
				.sideKnowledge(0, EAbility.PLUS_TWO_LIFE).buildCardFightingRobinsonStarting());

		this.deckPlayer.getArrayList().shuffle();
		this.deckPlayer.toFront();

		for (CardFighting cardFighting : this.deckPlayer)
			cardFighting.getImageView().flipBack();

	}

	private void createDeckHazardKnowledge() {

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.getArrayList()
					.addLast(new CardBuilder().fileName("With - Strategy").sideKnowledge(0, EAbility.EXCHANGE_TWO)
							.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.getArrayList()
					.addLast(new CardBuilder().fileName("With - Equipment").sideKnowledge(0, EAbility.PLUS_TWO_CARDS)
							.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.getArrayList()
					.addLast(new CardBuilder().fileName("With - Food").sideKnowledge(0, EAbility.PLUS_ONE_LIFE)
							.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("With - Realization").sideKnowledge(0, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("With - Deception").sideKnowledge(0, EAbility.BELOW_THE_PILE_ONE)
						.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList().addLast(new CardBuilder().fileName("With - Mimicry")
				.sideKnowledge(0, EAbility.COPY_ONE).sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("With - Books").sideKnowledge(0, EAbility.PHASE_MINUS_ONE)
						.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.getArrayList()
					.addLast(new CardBuilder().fileName("Exploring - Food").sideKnowledge(1, EAbility.PLUS_ONE_LIFE)
							.sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Exploring - Realization").sideKnowledge(1, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Exploring - Deception")
						.sideKnowledge(1, EAbility.BELOW_THE_PILE_ONE).sideHazard(EHazardValue.TWO)
						.buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList().addLast(new CardBuilder().fileName("Exploring - Mimicry")
				.sideKnowledge(1, EAbility.COPY_ONE).sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList().addLast(new CardBuilder().fileName("Exploring - Repeat")
				.sideKnowledge(1, EAbility.DOUBLE_ONE).sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.getArrayList()
					.addLast(new CardBuilder().fileName("Exploring - Weapon").sideKnowledge(2, EAbility.DOUBLE_ONE)
							.sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Further - Strategy").sideKnowledge(2, EAbility.EXCHANGE_ONE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Further - Food").sideKnowledge(2, EAbility.PLUS_ONE_LIFE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Further - Realization").sideKnowledge(2, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Further - Repeat").sideKnowledge(2, EAbility.DOUBLE_ONE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Further - Experience").sideKnowledge(2, EAbility.PLUS_ONE_CARD)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Further - Vision").sideKnowledge(2, EAbility.SORT_THREE_CARDS)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Wild - Strategy").sideKnowledge(3, EAbility.EXCHANGE_ONE)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Wild - Vision").sideKnowledge(3, EAbility.SORT_THREE_CARDS)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Wild - Experience").sideKnowledge(3, EAbility.PLUS_ONE_CARD)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList()
				.addLast(new CardBuilder().fileName("Wild - Realization").sideKnowledge(3, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.getArrayList().addLast(new CardBuilder().fileName("Cannibals").sideKnowledge(4)
					.sideHazard(EHazardValue.FIVE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.getArrayList().shuffle();
		this.deckHazardKnowledge.toFront();

		for (CardFighting cardFighting : this.deckHazardKnowledge)
			cardFighting.getImageView().flipBack();

	}

	private void createDeckStep() {

		this.deckStep.getArrayList().addLast(new CardStep("Green Card", EStep.GREEN));
		this.deckStep.getArrayList().addLast(new CardStep("Yellow Card", EStep.YELLOW));
		this.deckStep.getArrayList().addLast(new CardStep("Red Card", EStep.RED));

		this.deckStep.toFront();

	}

	private void createDeckAging() {

		for (int counter = 1; counter <= 2; counter++)
			this.deckAging.getArrayList()
					.addLast(new CardBuilder().fileName("Scared").sideKnowledge(0, EAbility.HIGHEST_CARD_EQUALS_ZERO)
							.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.getArrayList()
				.addLast(new CardBuilder().fileName("Hungry").sideKnowledge(0, EAbility.MINUS_ONE_LIFE)
						.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.getArrayList().addLast(new CardBuilder().fileName("Very Tired").sideKnowledge(0, EAbility.STOP)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.getArrayList().addLast(new CardBuilder().fileName("Distracted2").sideKnowledge(-1)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.getArrayList().addLast(new CardBuilder().fileName("Stupid").sideKnowledge(-2)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.getArrayList().addLast(new CardBuilder().fileName("Very Stupid").sideKnowledge(-3)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.getArrayList()
				.addLast(new CardBuilder().fileName("Very Hungry").sideKnowledge(0, EAbility.MINUS_TWO_LIFE)
						.eCardAgingType(ECardAgingType.DIFFICULT).buildCardFightingAging());

		this.deckAging.getArrayList().addLast(new CardBuilder().fileName("Moronic").sideKnowledge(-4)
				.eCardAgingType(ECardAgingType.DIFFICULT).buildCardFightingAging());

		this.deckAging.getArrayList().addLast(new CardBuilder().fileName("Suicidal").sideKnowledge(-5)
				.eCardAgingType(ECardAgingType.DIFFICULT).buildCardFightingAging());

		this.deckAging.getArrayList().shuffle();
		this.deckAging.toFront();

		for (CardFighting cardFighting : this.deckAging)
			cardFighting.getImageView().flipBack();

	}

	private void createDeckPirates() {

		String filename = "Ship-";
		int pirateNumber = 0;

		// 1

		pirateNumber++;
		this.deckPirates
				.addLast(new CardBuilder().fileName(filename + pirateNumber).sidePirate(9, 35).buildCardPirate());

		// 2

		pirateNumber++;
		this.deckPirates.addLast(new CardBuilder().fileName(filename + pirateNumber)
				.sidePirate(7, 16, EAbility.EACH_ADDITIONAL_FIGHTING_CARD_COSTS_TWO_LIFE_POINTS).buildCardPirate());

		// 3

		pirateNumber++;
		this.deckPirates
				.addLast(new CardBuilder().fileName(filename + pirateNumber).sidePirate(7, 25).buildCardPirate());

		// 4

		pirateNumber++;
		this.deckPirates.addLast(new CardBuilder().fileName(filename + pirateNumber)
				.sidePirate(5, 0, EAbility.PLUS_TWO_HAZARD_POINTS_FOR_EACH_FIGHTING_CARD).buildCardPirate());

		// 5

		pirateNumber++;
		this.deckPirates.addLast(new CardBuilder().fileName(filename + pirateNumber)
				.sidePirate(10, 52, EAbility.EACH_FACE_UP_FIGHTING_CARD_COUNTS_PLUS_ONE_FIGHTING_POINT)
				.buildCardPirate());

		// 6

		pirateNumber++;
		this.deckPirates
				.addLast(new CardBuilder().fileName(filename + pirateNumber).sidePirate(10, 40).buildCardPirate());

		// 7

		pirateNumber++;
		this.deckPirates.addLast(new CardBuilder().fileName(filename + pirateNumber)
				.sidePirate(9, 22, EAbility.ONLY_HALF_OF_THE_FACE_UP_FIGHTING_CARDS_COUNT).buildCardPirate());

		// 8

		pirateNumber++;
		this.deckPirates.addLast(new CardBuilder().fileName(filename + pirateNumber)
				.sidePirate(EAbility.FIGHT_AGAINST_ALL_REMAINING_HAZARD_CARDS).buildCardPirate());

		// 9

		pirateNumber++;
		this.deckPirates
				.addLast(new CardBuilder().fileName(filename + pirateNumber).sidePirate(6, 20).buildCardPirate());

		// 10

		pirateNumber++;
		this.deckPirates
				.addLast(new CardBuilder().fileName(filename + pirateNumber).sidePirate(8, 30).buildCardPirate());

		for (CardPirate cardPirate : this.deckPirates)
			cardPirate.getImageView().setVisible(false);

		System.out.println(this.deckPirates.size());

		this.cardPiratesInPlay.getArrayList().addLast(this.deckPirates.removeRandom());
		this.cardPiratesInPlay.getArrayList().addLast(this.deckPirates.removeRandom());

		this.cardPiratesInPlay.relocateImageViews();

		for (CardPirate cardPirate : this.cardPiratesInPlay)
			cardPirate.getImageView().setVisible(true);

	}

	private void createLifeTokens() {

		for (int counter = 1; counter <= 22; counter++)
			this.lifeTokens.getArrayList().addLast(new LifeToken());

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
