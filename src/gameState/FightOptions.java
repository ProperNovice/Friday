package gameState;

import controller.AbilitiesCanBeResolved;
import controller.AbilityImageViewList;
import controller.Flow;
import controller.Life;
import controller.Lists;
import controller.Modifiers;
import enums.EAbility;
import enums.EGameState;
import enums.EText;
import interfaces.IAbilityAble;
import model.AbilityImageView;
import model.CardFighting;
import model.CardSlot;
import model.SideKnowledge;
import utils.ArrayList;
import utils.Text;

public class FightOptions extends AGameState {

	private ArrayList<CardFighting> cardAbilitiesCompleteList = new ArrayList<CardFighting>();
	private ArrayList<CardFighting> cardAbilitiesCanBeResolvedNonCopy = new ArrayList<CardFighting>();
	private ArrayList<CardFighting> cardAbilitiesCanBeResolvedOnlyCopy = new ArrayList<CardFighting>();

	@Override
	public void handleGameStateChange() {

		AbilityImageViewList.INSTANCE.releaseAllAbilitiesImageView();

		this.cardAbilitiesCompleteList.clear();
		this.cardAbilitiesCanBeResolvedNonCopy.clear();
		this.cardAbilitiesCanBeResolvedOnlyCopy.clear();

		handleETextToShow();
		Text.INSTANCE.showText(EText.RESOLVE_FIGHT);
		handleResolveAbilityText();

	}

	@Override
	protected void executeTextOption(EText eText) {

		switch (eText) {

		case DRAW_CARD_FREE:
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
			break;

		case DRAW_CARD_ONE_LIFE:
			Life.INSTANCE.loseLife(1);
			Flow.INSTANCE.executeGameState(EGameState.DRAW_CARD_FROM_DECK_TO_HAND_FIRST_EMPTY_SLOT);
			break;

		default:
			break;

		}

	}

	private void handleETextToShow() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (cardSlot.containsCardFighting())
				continue;

			if (cardSlot.containsFreeCard())
				Text.INSTANCE.showText(EText.DRAW_CARD_FREE);
			else if (Life.INSTANCE.getLifeCurrent() > 0)
				Text.INSTANCE.showText(EText.DRAW_CARD_ONE_LIFE);

			break;

		}

	}

	private void handleResolveAbilityText() {

		createListAbilitesComplete();
		createListAbilitiesCanBeResolved();
		setAbilityImageViewsToCardsThatCanBeResolved();

	}

	private void createListAbilitesComplete() {

		for (CardSlot cardSlot : Lists.INSTANCE.handPlayer.getCardSlots()) {

			if (!cardSlot.containsCardFighting())
				continue;

			CardFighting cardFighting = cardSlot.getCardFighting();

			if (cardFighting == Modifiers.INSTANCE.getCardFightingAgainst())
				continue;

			SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();

			if (!(sideKnowledge instanceof IAbilityAble))
				continue;

			this.cardAbilitiesCompleteList.addLast(cardFighting);

		}

	}

	private void createListAbilitiesCanBeResolved() {

		for (CardFighting cardFighting : this.cardAbilitiesCompleteList) {

			if (Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().contains(cardFighting))
				continue;

			IAbilityAble iAbilityAble = (IAbilityAble) cardFighting.getSideKnowledge();
			EAbility eAbility = iAbilityAble.getEAbility();

			if (eAbility == EAbility.COPY_ONE)
				this.cardAbilitiesCanBeResolvedOnlyCopy.addLast(cardFighting);

			else if (AbilitiesCanBeResolved.INSTANCE.canBeResolved(cardFighting, eAbility))
				this.cardAbilitiesCanBeResolvedNonCopy.addLast(cardFighting);

		}

	}

	private void setAbilityImageViewsToCardsThatCanBeResolved() {

		if (!this.cardAbilitiesCanBeResolvedNonCopy.isEmpty())
			setListAbilityImageViewsTrue(this.cardAbilitiesCanBeResolvedNonCopy);

		int cardAbilitiesInHand = 0;
		cardAbilitiesInHand += this.cardAbilitiesCanBeResolvedNonCopy.size();
		cardAbilitiesInHand += Modifiers.INSTANCE.getCardFightingHaveBeenResolvedThisRound().size();

		if (cardAbilitiesInHand > 0)
			setListAbilityImageViewsTrue(this.cardAbilitiesCanBeResolvedOnlyCopy);

	}

	private void setListAbilityImageViewsTrue(ArrayList<CardFighting> list) {

		for (CardFighting cardFighting : list) {

			AbilityImageView abilityImageView = AbilityImageViewList.INSTANCE
					.getAbilityImageViewForCardFighting(cardFighting);

			abilityImageView.setCanBeUsedVisibleTrue();
			abilityImageView.relocate(cardFighting);

		}

	}

}
