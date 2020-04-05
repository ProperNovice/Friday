package model;

import controller.Credentials;
import utils.ContainerImageViewAbles;
import utils.CoordinatesBuilder;
import utils.ImageView;
import utils.ImageViewAble;
import utils.RearrangeTypeEnum;

public enum SortCardsPanel {

	INSTANCE;

	private Background background = new Background();
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
		
	}

	private class Background implements ImageViewAble {

		public Background() {

			new ImageView("Sort Panel.png", this);

			this.getImageView().relocateCenter(Credentials.INSTANCE.CoordinatesSortCardPanelBackground);
			this.getImageView().setVisible(false);

		}

	}

	public ContainerImageViewAbles<CardFighting> getPanel() {
		return this.panel;
	}

}
