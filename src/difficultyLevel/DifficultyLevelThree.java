package difficultyLevel;

import controller.CardsContainer;

public class DifficultyLevelThree extends DifficultyLevelTwo {

	@Override
	protected void createDeckAging() {

		super.createDeckAging();

		super.deckAging.addLast(CardsContainer.INSTANCE.getCardFightingAgingVeryStupid());

	}

}
