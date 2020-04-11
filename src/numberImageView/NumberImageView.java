package numberImageView;

import controller.Credentials;
import enums.ELayerZ;
import utils.ImageView;
import utils.ImageViewAble;

public abstract class NumberImageView implements ImageViewAble {

	private String filePath = "misc/numbers/circle black/";

	public NumberImageView() {

		this.filePath += getNumber() + ".png";

		ImageView imageView = new ImageView(this.filePath, this, ELayerZ.B);
		imageView.setWidth(Credentials.INSTANCE.DimensionsListSizeIndicators);
		imageView.setVisible(false);

	}

	protected abstract int getNumber();

}
