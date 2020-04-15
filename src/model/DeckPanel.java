package model;

import card.CardFighting;
import card.CardFightingImageViewClone;
import card.SideKnowledge;
import controller.Credentials;
import controller.Lists;
import enums.ELayerZ;
import utils.ArrayList;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;

public enum DeckPanel {

	INSTANCE;

	private Background background = new Background("misc/backgroundDark.png", ELayerZ.C);
	private ContainerImageViewAbles<CardFightingImageViewClone> panel = null;
	private boolean isShowing = false;

	private DeckPanel() {

		this.panel = new ContainerImageViewAbles<CardFightingImageViewClone>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesHandPlayer)
						.gapY(-Credentials.INSTANCE.DimensionsCardFighting.y / 2).objectsPerRow(10).build());

	}

	public void showPanelAndRelocate(boolean value) {

		this.isShowing = value;

		this.background.getImageView().setVisible(value);
		this.background.getImageView().toFront();

		if (value)
			showPanel();

		for (CardFightingImageViewClone cardFighting : this.panel)
			cardFighting.getImageView().setVisible(value);

	}

	private void showPanel() {

		this.panel.getArrayList().clear();

		ArrayList<CardFighting> deckClone = Lists.INSTANCE.deckPlayer.getArrayList().clone();
		ArrayList<CardFightingImageViewClone> deckTemp = new ArrayList<CardFightingImageViewClone>();

		for (int counter = -5; counter <= 4; counter++) {

			for (CardFighting cardFighting : deckClone.clone()) {

				SideKnowledge sideKnowledge = cardFighting.getSideKnowledge();
				int fightingValue = sideKnowledge.getFightingValue();

				if (fightingValue != counter)
					continue;

				deckClone.remove(cardFighting);
				deckTemp.addLast(cardFighting.getCardFightingImageViewClone());

			}

			deckTemp.shuffle();

			for (CardFightingImageViewClone cardFightingImageViewClone : deckTemp.clone())
				if (cardFightingImageViewClone.isInstanceOfCardAging()) {

					deckTemp.remove(cardFightingImageViewClone);
					this.panel.getArrayList().addLast(cardFightingImageViewClone);

				}

			this.panel.getArrayList().addAll(deckTemp);
			deckTemp.clear();

		}

		this.panel.relocateImageViews();
		this.panel.toBack();

	}

	public boolean isShowing() {
		return this.isShowing;
	}

}
