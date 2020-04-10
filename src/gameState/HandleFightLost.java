package gameState;

import controller.AbilityImageViewList;
import controller.DestroyCardLifeIndicator;
import controller.FightingPoints;
import controller.Flow;
import controller.Life;
import controller.Lists;
import enums.EGameState;
import enums.EText;
import model.CardFighting;
import model.CardSlot;
import model.SideKnowledge;
import utils.ArrayList;

public class HandleFightLost extends AGameState {

	private ArrayList<CardFighting> cardsToBeDestroyed = new ArrayList<CardFighting>();
	private int lifeRemaining;

	@Override
	public void handleGameStateChange() {

		this.cardsToBeDestroyed.clear();
		handleLifeLost();

		if (Life.INSTANCE.getLifeCurrent() < 0) {

			Flow.INSTANCE.clear();
			Flow.INSTANCE.executeGameState(EGameState.END_GAME_LOSE);

		} else {

			EText.DESTROY_CARD.showText();
			EText.CONTINUE.showText();

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		DestroyCardLifeIndicator.INSTANCE.setVisibleIndicatorFalse();

		AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();
			cardSlot.removeCardFighting();

			if (this.cardsToBeDestroyed.contains(cardFighting))
				cardFighting.getImageView().setVisible(false);
			else
				Lists.INSTANCE.discardPilePlayer.getArrayList().addFirst(cardFighting);

		}

		DestroyCardLifeIndicator.INSTANCE.setIndicatorLifeToLoseSetText(this.lifeRemaining);

		Lists.INSTANCE.discardPilePlayer.relocateImageViews();
		Lists.INSTANCE.discardPilePlayer.toFront();
		Flow.INSTANCE.proceed();

	}

	@Override
	protected void executeCardFightingPressedHand(CardFighting cardFighting) {

		if (this.cardsToBeDestroyed.contains(cardFighting)) {

			this.cardsToBeDestroyed.remove(cardFighting);
			AbilityImageViewList.INSTANCE.releaseAbilityImageView(cardFighting);

			SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();
			this.lifeRemaining += sideKnowledge.getDestroyingValue();

		} else {

			SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();
			int lifeToLose = sideKnowledge.getDestroyingValue();

			if (lifeToLose > this.lifeRemaining)
				return;

			this.lifeRemaining -= sideKnowledge.getDestroyingValue();

			this.cardsToBeDestroyed.addLast(cardFighting);
			AbilityImageViewList.INSTANCE.setAbilityImageViewForCardFighting(cardFighting);

		}

		DestroyCardLifeIndicator.INSTANCE.setIndicatorLifeToLoseSetText(this.lifeRemaining);

	}

	private void handleLifeLost() {

		int fightingValue = FightingPoints.INSTANCE.getPlayerFightingPointsWithDouble();
		int hazardValue = FightingPoints.INSTANCE.getEncounterFightingPoints();
		this.lifeRemaining = hazardValue - fightingValue;

		Life.INSTANCE.loseLife(this.lifeRemaining);

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
