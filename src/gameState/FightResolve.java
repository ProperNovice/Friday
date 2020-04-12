package gameState;

import card.CardFighting;
import card.CardFightingAging;
import card.CardSlot;
import card.SideKnowledge;
import controller.FightingPoints;
import controller.Flow;
import controller.Life;
import controller.Lists;
import enums.EAbility;
import enums.EGameState;
import interfaces.IAbilityAble;

public class FightResolve extends AGameState {

	@Override
	public void handleGameStateChange() {

		handleAgingAbilities();

		if (FightingPoints.INSTANCE.getPlayerFightingPointsWithDouble() >= FightingPoints.INSTANCE
				.getEncounterFightingPoints())
			fightWon();

		else
			fightLost();

	}

	private void handleAgingAbilities() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = (CardFighting) cardSlot.getCardFighting();

			if (cardFighting.getImageView().isFlippedBack())
				continue;

			if (!(cardFighting instanceof CardFightingAging))
				continue;

			CardFightingAging cardFightingAging = (CardFightingAging) cardFighting;
			SideKnowledge sideKnowledge = cardFightingAging.getSideKnowledge();

			if (!(sideKnowledge instanceof IAbilityAble))
				continue;

			IAbilityAble iAbilityAble = (IAbilityAble) sideKnowledge;
			EAbility eAbility = iAbilityAble.getEAbility();

			switch (eAbility) {

			case MINUS_ONE_LIFE:
				Life.INSTANCE.loseLife(1);
				break;

			case MINUS_TWO_LIFE:
				Life.INSTANCE.loseLife(2);
				break;

			default:
				break;

			}

		}

	}

	private void fightWon() {
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_WON);
	}

	private void fightLost() {
		Flow.INSTANCE.executeGameState(EGameState.FIGHT_LOST);
	}

}
