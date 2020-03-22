package controller;

import enums.EAbility;
import enums.ECardAgingType;
import enums.EHazardValue;
import enums.EStep;
import interfaces.ISaveLoadStateAble;
import model.CardBuilder;
import model.CardFighting;
import model.CardStep;
import model.IndicatorFreeCard;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.Logger;
import utils.RearrangeTypeEnum;

public enum Lists implements ISaveLoadStateAble {

	INSTANCE;

	public ArrayList<ISaveLoadStateAble> iSaveLoadStateAbles = new ArrayList<ISaveLoadStateAble>();
	public ContainerImageViewAbles<CardFighting> deckPlayer, deckHazardKnowledge, deckAging, discardPilePlayer,
			discardPileHazardKnowledge, handPlayer, cardsHazardsDrawn, hazardToFight;
	public ContainerImageViewAbles<CardStep> deckStep;
	public ContainerImageViewAbles<IndicatorFreeCard> indicatorFreeCards;

	public void instantiate() {

		createLists();
		createDeckPlayer();
		createDeckHazardKnowledge();
		createDeckStep();
		createDeckAging();
		createindicatorFreeCards();

		this.deckPlayer.relocateImageViews();
		this.deckHazardKnowledge.relocateImageViews();
		this.deckStep.relocateImageViews();
		this.deckAging.relocateImageViews();
		this.indicatorFreeCards.relocateImageViews();

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

		// handPlayer

		this.handPlayer = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesHandPlayer)
						.gapY(-Credentials.INSTANCE.DimensionsCardFighting.y / 2).objectsPerRow(10).build());

		// indicatorFreeCards

		this.indicatorFreeCards = new ContainerImageViewAbles<IndicatorFreeCard>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesIndicatorFreeCard)
						.gapY(-Credentials.INSTANCE.DimensionsCardFighting.y / 2).objectsPerRow(10).build());

		// cardsHazardDrawn

		this.cardsHazardsDrawn = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesCardsHazardsDrawn)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT).build());

		// hazardToFight

		this.hazardToFight = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesHazardToFight)
						.rearrangeTypeEnum(RearrangeTypeEnum.STATIC).build());

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

	private void createindicatorFreeCards() {

		for (int counter = 1; counter <= 20; counter++)
			this.indicatorFreeCards.getArrayList().addLast(new IndicatorFreeCard());

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
