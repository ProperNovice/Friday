package model;

import controller.Credentials;
import enums.ELayerZ;
import utils.ImageView;
import utils.ImageViewAble;

public class Background implements ImageViewAble {

	public Background(String fileName, ELayerZ eLayerZ) {

		new ImageView(fileName, this, eLayerZ);

		this.getImageView().relocateCenter(Credentials.INSTANCE.CoordinatesSortCardPanelBackground);
		this.getImageView().setVisible(false);

	}

}
