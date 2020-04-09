package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EStep;
import enums.EText;
import model.CardFightingHazardKnowledge;

public class EndTurn extends AGameState {

	@Override
	public void handleGameStateChange() {

		Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().clear();

		if (!Lists.INSTANCE.deckHazardKnowledge.getArrayList().isEmpty())
			Flow.INSTANCE.proceed();

		else {

			if (Modifiers.INSTANCE.getEStep().equals(EStep.PIRATES))
				Flow.INSTANCE.proceed();

			else {

				EText.ADVANCING_STEP.showText();
				EText.CONTINUE.showText();

			}

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		handleAdvanceStep();
		Flow.INSTANCE.proceed();

	}

	private void handleAdvanceStep() {

		Lists.INSTANCE.deckHazardKnowledge.getArrayList()
				.addAll(Lists.INSTANCE.discardPileHazardKnowledge.getArrayList());

		Lists.INSTANCE.discardPileHazardKnowledge.getArrayList().clear();

		Lists.INSTANCE.deckHazardKnowledge.animateSynchronousLock();

		for (CardFightingHazardKnowledge cardFightingHazardKnowledge : Lists.INSTANCE.deckHazardKnowledge)
			cardFightingHazardKnowledge.getImageView().flip();

		Lists.INSTANCE.deckHazardKnowledge.getArrayList().shuffle();
		Lists.INSTANCE.deckHazardKnowledge.toFront();

		Lists.INSTANCE.deckStep.getArrayList().addLast(Lists.INSTANCE.deckStep.getArrayList().removeFirst());
		Lists.INSTANCE.deckStep.toFront();
		Modifiers.INSTANCE.eStepAdvance();

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
