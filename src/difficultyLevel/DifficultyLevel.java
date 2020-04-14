package difficultyLevel;

import card.CardFighting;
import card.CardFightingAging;
import card.CardFightingHazardKnowledge;
import card.CardPirate;
import card.CardStep;
import controller.CardsContainer;
import enums.ECardAgingType;
import utils.ArrayList;

public abstract class DifficultyLevel {

	protected ArrayList<CardFighting> deckPlayer = new ArrayList<CardFighting>();
	private ArrayList<CardFightingHazardKnowledge> deckHazardKnowledge = new ArrayList<CardFightingHazardKnowledge>();
	protected ArrayList<CardFightingAging> deckAging = new ArrayList<CardFightingAging>();
	private ArrayList<CardStep> deckStep = new ArrayList<CardStep>();

	public DifficultyLevel() {

	}

	public final void createDecks() {

		this.deckHazardKnowledge.clear();
		this.deckAging.clear();
		this.deckPlayer.clear();
		this.deckStep.clear();

		createDeckHazardKnowledge();
		createDeckAging();
		sortDeckAging();
		createDeckPlayer();
		createDeckStep();

	}

	protected void createDeckAging() {
		this.deckAging.addAll(CardsContainer.INSTANCE.getDeckAgingLevelOne());
	}

	private void sortDeckAging() {

		ArrayList<CardFightingAging> deckAgingTemp = this.deckAging.clone();
		this.deckAging.clear();

		for (CardFightingAging cardFightingAging : deckAgingTemp.clone())
			if (cardFightingAging.getECardAgingType().equals(ECardAgingType.NORMAL)) {

				this.deckAging.addLast(cardFightingAging);
				deckAgingTemp.remove(cardFightingAging);

			}

		this.deckAging.shuffle();
		deckAgingTemp.shuffle();
		this.deckAging.addAll(deckAgingTemp);

	}

	protected void createDeckPlayer() {
		this.deckPlayer.addAll(CardsContainer.INSTANCE.getDeckPlayerLevelOne());
	}

	private void createDeckHazardKnowledge() {
		this.deckHazardKnowledge.addAll(CardsContainer.INSTANCE.getDeckHazardKnowledge());
	}

	private void createDeckStep() {
		this.deckStep.addAll(CardsContainer.INSTANCE.getDeckStep());
	}

	public ArrayList<CardFighting> getDeckPlayer() {
		return this.deckPlayer;
	}

	public final ArrayList<CardFightingHazardKnowledge> getDeckHazardKnowledge() {
		return this.deckHazardKnowledge;
	}

	public ArrayList<CardFightingAging> getDeckAging() {
		return this.deckAging;
	}

	public final ArrayList<CardStep> getDeckStep() {
		return this.deckStep;
	}

	public int getLife() {
		return 20;
	}

	public final ArrayList<CardPirate> getTwoPirates() {

		ArrayList<CardPirate> list = CardsContainer.INSTANCE.getDeckPirate();

		while (list.size() > 2)
			list.removeRandom();

		return list;

	}

}
