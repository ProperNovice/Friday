package model;

import controller.Credentials;
import enums.ELayerZ;
import utils.ImageView;
import utils.ImageViewAble;

public class IndicatorFreeCard implements ImageViewAble {

	public IndicatorFreeCard() {
		createImageView();
	}

	private void createImageView() {

		new ImageView("F.png", this, ELayerZ.B);
		this.getImageView().setWidth(Credentials.INSTANCE.DimensionsIndicatorFreeCard);
		this.getImageView().setVisible(false);

	}

}
