package model;

import controller.Credentials;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

public abstract class Card implements ImageViewAble, EventHandlerAble {

	public Card(String fileName) {
		createImage(fileName);
	}

	protected void createImage(String fileName) {

		String fileAdress = getFolder() + fileName + ".png";
		new ImageView(fileAdress, this);
		this.getImageView().setWidth(getWidth());

	}

	protected abstract String getFolder();

	protected abstract void print();

	protected double getWidth() {
		return Credentials.INSTANCE.DimensionsCardFighting.x;
	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		print();
	}

}
