package model;

import controller.Credentials;
import utils.ImageView;
import utils.ImageViewAble;

public class LifeToken implements ImageViewAble {

	public LifeToken() {
		createImageView();
	}

	private void createImageView() {

		String fileName = "Life Token - ";

		new ImageView(fileName + "Color.png", this);
		this.getImageView().setBack(fileName + "Black and White.png");
		this.getImageView().setWidth(Credentials.INSTANCE.DimensionsLifeToken);

	}

}
