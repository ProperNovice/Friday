package difficultyLevel;

import card.CardFighting;

public class DifficultyLevelTwo extends DifficultyLevelOne {

	@Override
	protected void createDeckPlayer() {

		super.createDeckPlayer();

		CardFighting cardFighting = super.deckAging.removeRandom();
		super.deckPlayer.addLast(cardFighting);

	}

}
