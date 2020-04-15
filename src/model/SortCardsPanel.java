package model;

import card.CardFighting;
import controller.Credentials;
import enums.ELayerZ;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.RearrangeTypeEnum;

public enum SortCardsPanel {

	INSTANCE;

	private Background background = new Background("Sort Panel.png", ELayerZ.A);
	private ContainerImageViewAbles<CardFighting> panel = null;

	private SortCardsPanel() {

		this.panel = new ContainerImageViewAbles<CardFighting>(
				new CoordinatesBuilder().dimensionsNumbersPair(Credentials.INSTANCE.DimensionsCardFighting)
						.rearrangeTypeEnum(RearrangeTypeEnum.PIVOT)
						.coordinatesNumbersPair(Credentials.INSTANCE.CoordinatesSortCardPanelCards).build(),
				3);

	}

	public void showBackgroundPanelAndRelocate(boolean value) {

		this.background.getImageView().setVisible(value);
		this.background.getImageView().toFront();

		this.panel.relocateImageViews();
		this.panel.toFront();

		for (CardFighting cardFighting : this.panel)
			cardFighting.getImageView().setVisible(value);

	}

	public ContainerImageViewAbles<CardFighting> getPanel() {
		return this.panel;
	}

}
