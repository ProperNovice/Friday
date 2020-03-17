package utils;

import controller.Credentials;
import utils.EventHandler.EventHandlerAble;

public class NumberImageView implements ImageViewAble {

	public static HashMap<Integer, ArrayList<Image>> objectPoolImages = new HashMap<Integer, ArrayList<Image>>();
	private EventHandlerAble eventHandlerAble = null;
	private ENumberImageViewColor numberImageViewColor = ENumberImageViewColor.TRANSPARENT;
	private ENumberImageViewShape numberImageViewShape = ENumberImageViewShape.CIRCLE;
	private int numberShowing = -1;

	public NumberImageView() {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

	}

	public NumberImageView(int number) {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

		setNumber(number);

	}

	public NumberImageView(EventHandlerAble eventHandlerAble) {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

		this.eventHandlerAble = eventHandlerAble;

	}

	public NumberImageView(int number, EventHandlerAble eventHandlerAble) {

		if (!objectPoolImages.containsKey(0))
			createObjectPool();

		setNumber(number);
		this.eventHandlerAble = eventHandlerAble;

	}

	public void setNumber(int value) {

		if (value > 20)
			ShutDown.INSTANCE.execute("@" + this.getClass());

		setImageView(value);

	}

	public void setInfinity() {
		setImageView(21);
	}

	public void setPhi() {
		setImageView(22);
	}

	public void setX() {
		setImageView(23);
	}

	public void setBlank() {
		setImageView(24);
	}

	private void createObjectPool() {

		for (int counter = 0; counter <= 24; counter++)
			objectPoolImages.put(counter, new ArrayList<Image>());

	}

	private void setImageView(int listIndex) {

		if (listIndex == this.numberShowing)
			return;

		String pathNumbers = "misc/numbers/", shape = this.numberImageViewShape.string(), pathPng = ".png";

		Image image = null;

		String pathFile = "";
		pathFile += pathNumbers + listIndex;

		if (this.numberImageViewColor != ENumberImageViewColor.TRANSPARENT)
			pathFile += shape;

		pathFile += this.numberImageViewColor.string();
		pathFile += pathPng;

		if (!objectPoolImages.get(listIndex).isEmpty())
			image = objectPoolImages.get(listIndex).removeFirst();
		else
			image = new Image(pathFile);

		if (MapImageViews.INSTANCE.getImageViewsMap().get(this) != null) {

			objectPoolImages.get(this.numberShowing)
					.addLast(MapImageViews.INSTANCE.getImageViewsMap().get(this).getImage());
			MapImageViews.INSTANCE.getImageViewsMap().get(this).setImage(image);

		} else {

			if (this.eventHandlerAble == null)
				MapImageViews.INSTANCE.getImageViewsMap().put(this, new ImageView(image, this));
			else
				MapImageViews.INSTANCE.getImageViewsMap().put(this,
						new ImageView(image, this, this.eventHandlerAble));

			MapImageViews.INSTANCE.getImageViewsMap().get(this)
					.setWidth(Credentials.INSTANCE.DimensionsNumberImageView.x);

		}

		this.numberShowing = listIndex;

	}

	private enum ENumberImageViewColor {

		GRAY("g"), BLACK("b"), TRANSPARENT("t");

		private String string = null;

		private ENumberImageViewColor(String string) {
			this.string = string;
		}

		public String string() {
			return this.string;
		}

	}

	private enum ENumberImageViewShape {

		CIRCLE("c"), SQUARE("s");

		private String string = null;

		private ENumberImageViewShape(String string) {
			this.string = string;
		}

		public String string() {
			return this.string;
		}

	}

}
