package model;

import controller.Credentials;
import enums.ELayerZ;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;
import utils.ShutDown;

public class Background implements ImageViewAble, EventHandlerAble {

	public Background(String fileName, ELayerZ eLayerZ) {

		new ImageView(fileName, this, eLayerZ);

		this.getImageView().relocateCenter(Credentials.INSTANCE.CoordinatesSortCardPanelBackground);
		this.getImageView().setVisible(false);

	}

	@Override
	public void handleMouseButtonPressedSecondary() {
		ShutDown.INSTANCE.execute();
	}

}
