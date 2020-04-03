package controller;

import model.AbilityImageView;
import model.CardFighting;
import utils.ArrayList;
import utils.ObjectPool;

public enum AbilityImageViewList {

	INSTANCE;

	private ArrayList<CardAbilityObject> list = new ArrayList<AbilityImageViewList.CardAbilityObject>();

	private AbilityImageViewList() {

	}

	public AbilityImageView getAbilityImageViewForCardFighting(CardFighting cardFighting) {

		for (CardAbilityObject cardAbilityObject : this.list)
			if (cardAbilityObject.getCardFighting() == cardFighting)
				return cardAbilityObject.getAbilityImageView();

		CardAbilityObject cardAbilityObject = new CardAbilityObject(cardFighting);
		this.list.addLast(cardAbilityObject);

		return cardAbilityObject.getAbilityImageView();

	}

	public void releaseAbilityImageView(CardFighting cardFighting) {

		for (CardAbilityObject cardAbilityObject : this.list.clone()) {

			if (cardAbilityObject.getCardFighting() != cardFighting)
				continue;

			this.list.remove(cardAbilityObject);

			AbilityImageView abilityImageView = cardAbilityObject.getAbilityImageView();
			abilityImageView.getImageView().setVisible(false);

			ObjectPool.INSTANCE.release(abilityImageView);

			break;

		}

	}

	public void releaseAllAbilitiesImageView() {

		for (CardAbilityObject cardAbilityObject : this.list.clone())
			releaseAbilityImageView(cardAbilityObject.getCardFighting());

	}

	private class CardAbilityObject {

		private CardFighting cardFighting = null;
		private AbilityImageView abilityImageView = null;

		public CardAbilityObject(CardFighting cardFighting) {

			this.cardFighting = cardFighting;
			this.abilityImageView = ObjectPool.INSTANCE.acquire(AbilityImageView.class);

		}

		public CardFighting getCardFighting() {
			return this.cardFighting;
		}

		public AbilityImageView getAbilityImageView() {
			return this.abilityImageView;
		}

	}

}
