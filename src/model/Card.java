package model;

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

	}

	protected abstract String getFolder();

	protected abstract void print();

	@Override
	public void handleMouseButtonPressedPrimary() {
		print();
	}

}
