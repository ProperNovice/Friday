package gameState;

import controller.Credentials;
import controller.Lists;
import enums.EText;
import model.CardFighting;
import utils.Text;

public class ChooseHazardToFight extends AGameState {

	@Override
	public void handleGameStateChange() {

		Text.INSTANCE.showText(EText.CHOOSE_HAZARD_TO_FIGHT);

	}

	@Override
	protected void executeCardPressedHazardsDrawn(CardFighting cardFighting) {
		
		Text.INSTANCE.concealText();

		Lists.INSTANCE.cardsHazardsDrawn.getArrayList().remove(cardFighting);
		cardFighting.getImageView().relocateTopLeft(Credentials.INSTANCE.CoordinatesHazardToFight);

		Lists.INSTANCE.discardPileHazardKnowledge.getArrayList()
				.addFirst(Lists.INSTANCE.cardsHazardsDrawn.getArrayList().removeFirst());
		Lists.INSTANCE.discardPileHazardKnowledge.relocateImageViews();
		Lists.INSTANCE.discardPileHazardKnowledge.toBack();

	}

}
