package gameState;

import controller.AbilityImageViewList;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EGameState;
import utils.Text;

public class RestartGame extends AGameState {

	@Override
	public void handleGameStateChange() {

		AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();
		Modifiers.INSTANCE.loadGameStart();
		Text.INSTANCE.concealText();

		Lists.INSTANCE.clearLists();
		Flow.INSTANCE.clear();
		Flow.INSTANCE.executeGameState(EGameState.CHOOSE_DIFFICULTY_LEVEL);

	}

}
