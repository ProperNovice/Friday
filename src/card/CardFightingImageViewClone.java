package card;

import controller.Credentials;
import controller.Flow;
import enums.ELayerZ;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

public class CardFightingImageViewClone implements ImageViewAble, EventHandlerAble {

	private CardFighting cardFighting = null;

	public CardFightingImageViewClone(String filePath, CardFighting cardFighting) {

		this.cardFighting = cardFighting;

		new ImageView(filePath, this, ELayerZ.C);
		getImageView().setWidth(Credentials.INSTANCE.DimensionsCardFighting);

		if (filePath.contains("hazardKnowledge"))
			getImageView().setRotate(180);

		getImageView().setVisible(false);

	}

	public boolean isInstanceOfCardAging() {
		return this.cardFighting instanceof CardFightingAging;
	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Flow.INSTANCE.getCurrentGameState().executeCardFightingPanelPressed();
	}

}
