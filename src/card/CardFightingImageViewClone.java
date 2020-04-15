package card;

import controller.Credentials;
import controller.Flow;
import enums.ELayerZ;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

public class CardFightingImageViewClone implements ImageViewAble, EventHandlerAble {

	public CardFightingImageViewClone(String filePath) {

		new ImageView(filePath, this, ELayerZ.C);
		getImageView().setWidth(Credentials.INSTANCE.DimensionsCardFighting);
		getImageView().setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {
		Flow.INSTANCE.getCurrentGameState().executeCardFightingPanelPressed();
	}

}
