package gameState;

import controller.Flow;
import controller.Lists;
import controller.Modifiers;
import enums.EStep;
import enums.EText;
import model.CardFightingHazardKnowledge;
import model.CardStep;

public class EndTurn extends AGameState {

	@Override
	public void handleGameStateChange() {

		Modifiers.INSTANCE.loadState();

		if (!Lists.INSTANCE.deckHazardKnowledge.getArrayList().isEmpty()
				|| Modifiers.INSTANCE.getEStep().equals(EStep.PIRATE))
			Flow.INSTANCE.proceed();

		else {

			EText.ADVANCING_STEP.showText();
			EText.CONTINUE.showText();

		}

	}

	@Override
	protected void executeTextOption(EText eText) {

		handleAdvanceStep();
		Flow.INSTANCE.proceed();

	}

	private void handleAdvanceStep() {

		Modifiers.INSTANCE.eStepAdvance();

		if (Modifiers.INSTANCE.getEStep().equals(EStep.PIRATE))
			return;

		Lists.INSTANCE.deckHazardKnowledge.getArrayList()
				.addAll(Lists.INSTANCE.discardPileHazardKnowledge.getArrayList());

		Lists.INSTANCE.discardPileHazardKnowledge.getArrayList().clear();

		Lists.INSTANCE.deckHazardKnowledge.animateSynchronousLock();

		for (CardFightingHazardKnowledge cardFightingHazardKnowledge : Lists.INSTANCE.deckHazardKnowledge)
			cardFightingHazardKnowledge.getImageView().flip();

		Lists.INSTANCE.deckHazardKnowledge.getArrayList().shuffle();
		Lists.INSTANCE.deckHazardKnowledge.toFront();

		CardStep cardStep = Lists.INSTANCE.deckStep.getArrayList().removeFirst();
		cardStep.getImageView().setVisible(false);

		Lists.INSTANCE.deckStep.toFront();

	}

	@Override
	public boolean fightingPointsCalculate() {
		return false;
	}

}
