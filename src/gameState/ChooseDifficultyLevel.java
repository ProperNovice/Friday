package gameState;

import controller.CardsContainer;
import controller.Lists;
import difficultyLevel.DifficultyLevel;
import enums.EDifficultyLevel;
import enums.EText;
import utils.HashMap;

public class ChooseDifficultyLevel extends AGameState {

	private HashMap<EText, EDifficultyLevel> map = new HashMap<EText, EDifficultyLevel>();

	public ChooseDifficultyLevel() {

		this.map.put(EText.ONE, EDifficultyLevel.ONE);
		this.map.put(EText.TWO, EDifficultyLevel.TWO);
		this.map.put(EText.THREE, EDifficultyLevel.THREE);
		this.map.put(EText.FOUR, EDifficultyLevel.FOUR);

	}

	@Override
	public void handleGameStateChange() {

		CardsContainer.INSTANCE.setCardsVisibleFalse();

		EText.DIFFICULTY_LEVEL.showText();
		EText.ONE.showText();
		EText.TWO.showText();
		EText.THREE.showText();
		EText.FOUR.showText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		EDifficultyLevel eDfficultyLevel = this.map.get(eText);
		DifficultyLevel difficultyLevel = eDfficultyLevel.getDifficultyLevel();

		difficultyLevel.createDecks();
		Lists.INSTANCE.populateLists(difficultyLevel);

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
