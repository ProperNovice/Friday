package gameState;

import card.CardFightingHazardKnowledge;
import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EStep;
import enums.EText;

public class EndTurn extends AGameState {

	@Override
	public void handleGameStateChange() {

		Modifiers.INSTANCE.loadState();

		if (!Lists.INSTANCE.deckHazardKnowledge.getArrayList().isEmpty()) {
			Flow.INSTANCE.proceed();
			return;
		}

		switch (Modifiers.INSTANCE.getEStep()) {

		case PIRATE:
			Flow.INSTANCE.proceed();
			break;

		default:
			EText.ADVANCING_STEP.showText();
			EText.CONTINUE.showText();
			break;

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		Modifiers.INSTANCE.eStepAdvance();

		removeFirstEStepCard();

		if (!Modifiers.INSTANCE.getEStep().equals(EStep.PIRATE))
			shuffleDiscardPileHazardToDeck();

		Flow.INSTANCE.proceed();

	}

	private void removeFirstEStepCard() {
		Lists.INSTANCE.deckStep.getArrayList().removeFirst().getImageView().setVisible(false);
	}

	private void shuffleDiscardPileHazardToDeck() {

		Lists.INSTANCE.deckHazardKnowledge.getArrayList()
				.addAll(Lists.INSTANCE.discardPileHazardKnowledge.getArrayList());

		Lists.INSTANCE.discardPileHazardKnowledge.getArrayList().clear();

		Lists.INSTANCE.deckHazardKnowledge.animateSynchronousLock();

		for (CardFightingHazardKnowledge cardFightingHazardKnowledge : Lists.INSTANCE.deckHazardKnowledge)
			cardFightingHazardKnowledge.getImageView().flip();

		Lists.INSTANCE.deckHazardKnowledge.getArrayList().shuffle();
		Lists.INSTANCE.deckHazardKnowledge.toFront();

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
