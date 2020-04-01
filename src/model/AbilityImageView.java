package model;

import controller.Credentials;
import enums.ELayerZ;
import utils.ImageView;
import utils.ImageViewAble;

public class AbilityImageView implements ImageViewAble {

	public AbilityImageView() {
		createImageView();
	}

	private void createImageView() {

		new ImageView("Ready.png", this, ELayerZ.B);
		this.getImageView().setBack("Tap.png");
		this.getImageView().setWidth(Credentials.INSTANCE.DimensionsUsedImages);

		this.getImageView().setVisible(false);

	}

	public void setCanBeUsedVisibleTrue() {

		this.getImageView().setVisible(true);
		this.getImageView().flipFront();

	}

	public void setHasAlreadyBeenUsedVisibleTrue() {

		this.getImageView().setVisible(true);
		this.getImageView().flipBack();

	}

}
