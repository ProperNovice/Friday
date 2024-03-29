package card;

import controller.Credentials;
import controller.Flow;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

public abstract class Card implements ImageViewAble, EventHandlerAble {

	public Card(String fileName) {
		createImage(fileName);
	}

	protected void createImage(String fileName) {

		String filePath = getFilePath(fileName);
		new ImageView(filePath, this);
		this.getImageView().setWidth(getWidth());

	}

	protected abstract String getFolder();

	public abstract void print();

	protected double getWidth() {
		return Credentials.INSTANCE.DimensionsCardFighting.x;
	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Flow.INSTANCE.getCurrentGameState().executeCardPressed(this);
	}

	@Override
	public void handleMouseEntered() {
		Flow.INSTANCE.getCurrentGameState().executeCardWhenEntered(this);
	}

	@Override
	public void handleMouseExited() {
		Flow.INSTANCE.getCurrentGameState().executeCardWhenExited(this);
	}

	protected String getFilePath(String fileName) {
		return getFolder() + fileName + ".png";
	}

}
