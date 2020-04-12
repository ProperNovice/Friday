package controller;

import card.Card;
import card.CardBuilder;
import card.CardFighting;
import card.CardFightingAging;
import card.CardFightingHazardKnowledge;
import card.CardPirate;
import card.CardStep;
import enums.EAbility;
import enums.ECardAgingType;
import enums.EHazardValue;
import enums.EStep;
import utils.ArrayList;

public enum CardsContainer {

	INSTANCE;

	private ArrayList<CardFighting> deckPlayer = new ArrayList<CardFighting>();
	private ArrayList<CardFightingHazardKnowledge> deckHazardKnowledge = new ArrayList<CardFightingHazardKnowledge>();
	private ArrayList<CardFightingAging> deckAging = new ArrayList<CardFightingAging>();
	private CardFightingAging cardFightingAgingVeryStupid = null;
	private ArrayList<CardPirate> deckPirates = new ArrayList<CardPirate>();
	private ArrayList<CardStep> deckStep = new ArrayList<CardStep>();

	private CardsContainer() {

		createAgingCardVeryStupid();
		createDeckHazardKnowledge();
		createDeckAging();
		createDeckPlayer();
		createDeckPirates();
		createDeckStep();

		cardsSetVisibleFalse();

	}

	protected void createDeckPlayer() {

		for (int counter = 1; counter <= 5; counter++)
			this.deckPlayer.addLast(
					new CardBuilder().fileName("Distracted1").sideKnowledge(-1).buildCardFightingRobinsonStarting());

		for (int counter = 1; counter <= 8; counter++)
			this.deckPlayer
					.addLast(new CardBuilder().fileName("Weak").sideKnowledge(0).buildCardFightingRobinsonStarting());

		for (int counter = 1; counter <= 3; counter++)
			this.deckPlayer.addLast(
					new CardBuilder().fileName("Focused").sideKnowledge(1).buildCardFightingRobinsonStarting());

		this.deckPlayer
				.addLast(new CardBuilder().fileName("Genius").sideKnowledge(2).buildCardFightingRobinsonStarting());

		this.deckPlayer.addLast(new CardBuilder().fileName("Eating").sideKnowledge(0, EAbility.PLUS_TWO_LIFE)
				.buildCardFightingRobinsonStarting());

	}

	private void createDeckHazardKnowledge() {

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge
					.addLast(new CardBuilder().fileName("With - Strategy").sideKnowledge(0, EAbility.EXCHANGE_TWO)
							.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge
					.addLast(new CardBuilder().fileName("With - Equipment").sideKnowledge(0, EAbility.PLUS_TWO_CARDS)
							.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge
					.addLast(new CardBuilder().fileName("With - Food").sideKnowledge(0, EAbility.PLUS_ONE_LIFE)
							.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("With - Realization").sideKnowledge(0, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("With - Deception").sideKnowledge(0, EAbility.BELOW_THE_PILE_ONE)
						.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.addLast(new CardBuilder().fileName("With - Mimicry")
				.sideKnowledge(0, EAbility.COPY_ONE).sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("With - Books").sideKnowledge(0, EAbility.PHASE_MINUS_ONE)
						.sideHazard(EHazardValue.ONE).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge
					.addLast(new CardBuilder().fileName("Exploring - Food").sideKnowledge(1, EAbility.PLUS_ONE_LIFE)
							.sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Exploring - Realization").sideKnowledge(1, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.addLast(
				new CardBuilder().fileName("Exploring - Deception").sideKnowledge(1, EAbility.BELOW_THE_PILE_ONE)
						.sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.addLast(new CardBuilder().fileName("Exploring - Mimicry")
				.sideKnowledge(1, EAbility.COPY_ONE).sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge.addLast(new CardBuilder().fileName("Exploring - Repeat")
				.sideKnowledge(1, EAbility.DOUBLE_ONE).sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.addLast(new CardBuilder().fileName("Exploring - Weapon").sideKnowledge(2)
					.sideHazard(EHazardValue.TWO).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Further - Strategy").sideKnowledge(2, EAbility.EXCHANGE_ONE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Further - Food").sideKnowledge(2, EAbility.PLUS_ONE_LIFE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Further - Realization").sideKnowledge(2, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Further - Repeat").sideKnowledge(2, EAbility.DOUBLE_ONE)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Further - Experience").sideKnowledge(2, EAbility.PLUS_ONE_CARD)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Further - Vision").sideKnowledge(2, EAbility.SORT_THREE_CARDS)
						.sideHazard(EHazardValue.THREE).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Wild - Strategy").sideKnowledge(3, EAbility.EXCHANGE_ONE)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Wild - Vision").sideKnowledge(3, EAbility.SORT_THREE_CARDS)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Wild - Experience").sideKnowledge(3, EAbility.PLUS_ONE_CARD)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		this.deckHazardKnowledge
				.addLast(new CardBuilder().fileName("Wild - Realization").sideKnowledge(3, EAbility.DESTROY_ONE)
						.sideHazard(EHazardValue.FOUR).buildCardFightingHazardKnowledge());

		for (int counter = 1; counter <= 2; counter++)
			this.deckHazardKnowledge.addLast(new CardBuilder().fileName("Cannibals").sideKnowledge(4)
					.sideHazard(EHazardValue.FIVE).buildCardFightingHazardKnowledge());

	}

	protected void createDeckAging() {

		for (int counter = 1; counter <= 2; counter++)
			this.deckAging
					.addLast(new CardBuilder().fileName("Scared").sideKnowledge(0, EAbility.HIGHEST_CARD_EQUALS_ZERO)
							.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.addLast(new CardBuilder().fileName("Hungry").sideKnowledge(0, EAbility.MINUS_ONE_LIFE)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.addLast(new CardBuilder().fileName("Very Tired").sideKnowledge(0, EAbility.STOP)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.addLast(new CardBuilder().fileName("Distracted2").sideKnowledge(-1)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		for (int counter = 1; counter <= 2; counter++)
			this.deckAging.addLast(new CardBuilder().fileName("Stupid").sideKnowledge(-2)
					.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging());

		this.deckAging.addLast(new CardBuilder().fileName("Very Hungry").sideKnowledge(0, EAbility.MINUS_TWO_LIFE)
				.eCardAgingType(ECardAgingType.DIFFICULT).buildCardFightingAging());

		this.deckAging.addLast(new CardBuilder().fileName("Moronic").sideKnowledge(-4)
				.eCardAgingType(ECardAgingType.DIFFICULT).buildCardFightingAging());

		this.deckAging.addLast(new CardBuilder().fileName("Suicidal").sideKnowledge(-5)
				.eCardAgingType(ECardAgingType.DIFFICULT).buildCardFightingAging());

	}

	private void createAgingCardVeryStupid() {

		this.cardFightingAgingVeryStupid = new CardBuilder().fileName("Very Stupid").sideKnowledge(-3)
				.eCardAgingType(ECardAgingType.NORMAL).buildCardFightingAging();

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
				.sidePirate(5, 0, EAbility.PLUS_TWO_HAZARD_POINTS_FOR_EACH_AGING_CARD).buildCardPirate());

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

	}

	private void createDeckStep() {

		this.deckStep.addLast(new CardStep("Green", EStep.GREEN));
		this.deckStep.addLast(new CardStep("Yellow", EStep.YELLOW));
		this.deckStep.addLast(new CardStep("Red", EStep.RED));
		this.deckStep.addLast(new CardStep("Pirate", EStep.PIRATE));

	}

	private void cardsSetVisibleFalse() {

		for (Card card : this.deckPlayer)
			card.getImageView().setVisible(false);

		for (Card card : this.deckHazardKnowledge)
			card.getImageView().setVisible(false);

		for (Card card : this.deckAging)
			card.getImageView().setVisible(false);

		this.cardFightingAgingVeryStupid.getImageView().setVisible(false);

	}

	public ArrayList<CardFighting> getDeckPlayerLevelOne() {
		return this.deckPlayer.clone();
	}

	public ArrayList<CardFightingHazardKnowledge> getDeckHazardKnowledge() {
		return this.deckHazardKnowledge.clone();
	}

	public ArrayList<CardFightingAging> getDeckAgingLevelOne() {
		return this.deckAging.clone();
	}

	public CardFightingAging getCardFightingAgingVeryStupid() {
		return this.cardFightingAgingVeryStupid;
	}

	public ArrayList<CardPirate> getDeckPirate() {
		return this.deckPirates.clone();
	}

	public ArrayList<CardStep> getDeckStep() {
		return this.deckStep.clone();
	}

	public void setCardsVisibleFalse() {

		setListVisibleFalse(this.deckAging);
		setListVisibleFalse(this.deckHazardKnowledge);
		setListVisibleFalse(this.deckPirates);
		setListVisibleFalse(this.deckPlayer);
		setListVisibleFalse(this.deckStep);
		this.cardFightingAgingVeryStupid.getImageView().setVisible(false);
		
		for (CardFightingHazardKnowledge cardFightingHazardKnowledge : this.deckHazardKnowledge)
			cardFightingHazardKnowledge.getImageView().setRotate(0);

	}

	private void setListVisibleFalse(ArrayList<? extends Card> list) {

		for (Card card : list)
			card.getImageView().setVisible(false);

	}

}
