package enums;

import difficultyLevel.*;

public enum EDifficultyLevel {

	ONE(new DifficultyLevelOne()),
	TWO(new DifficultyLevelTwo()),
	THREE(new DifficultyLevelThree()),
	FOUR(new DifficultyLevelFour()),
	FIVE(new DifficultyLevelFive()),
	
	;
	
	private DifficultyLevel difficultyLevel = null;
	
	private EDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	
	public DifficultyLevel getDifficultyLevel() {
		return this.difficultyLevel;
	}

}
