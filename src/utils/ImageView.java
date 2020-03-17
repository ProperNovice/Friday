package utils;

import gui.PanelGame;
import javafx.scene.shape.Rectangle;
import utils.EventHandler.EventHandlerAble;

public class ImageView implements INode {

	private javafx.scene.image.ImageView imageView = null;
	private Image imageFront = null, imageBack = null, imageShowing = null;
	private Image imageVisibilityFalse = new Image("misc/imageVisibilityFalse.png");
	private double widthOriginal, heightOriginal, scale = 1, xClip = 0, yClip = 0;
	private EventHandler eventHandler = null;

	public ImageView(String path, Object object) {

		this.imageFront = new Image(path);
		createAndAddNode((ImageViewAble) object);

		if (object instanceof EventHandlerAble)
			this.setEventHandler((EventHandlerAble) object);

	}

	public ImageView(Image image, Object object) {

		this.imageFront = image;
		createAndAddNode((ImageViewAble) object);

		if (object instanceof EventHandlerAble)
			this.setEventHandler((EventHandlerAble) object);

	}

	public ImageView(Image image, ImageViewAble imageViewAble, EventHandlerAble eventHandlerAble) {

		this.imageFront = image;
		createAndAddNode(imageViewAble);

		this.setEventHandler(eventHandlerAble);

	}

	private void createAndAddNode(ImageViewAble imageViewAble) {

		MapImageViews.INSTANCE.getImageViewsMap().put(imageViewAble, this);

		this.imageView = new javafx.scene.image.ImageView(this.imageFront.getImage());

		PanelGame panelGame = PanelGameInstance.INSTANCE.getPanelGameInstance();
		PlatformFX.runLater(() -> panelGame.addNode(this.imageView));

		this.imageShowing = this.imageFront;

		this.widthOriginal = this.imageView.minWidth(0);
		this.heightOriginal = this.imageView.minHeight(0);

	}

	public final void setVisible(final boolean value) {

		if (value)
			this.imageShowing = this.imageFront;
		else if (!value)
			this.imageShowing = this.imageVisibilityFalse;

		setImageShowing();

	}

	public final boolean isVisible() {
		return this.imageShowing.equals(this.imageFront);
	}

	public void toBack() {
		PlatformFX.runLater(() -> this.imageView.toBack());
	}

	@Override
	public void toFront() {
		PlatformFX.runLater(() -> this.imageView.toFront());
	}

	@Override
	public final double getLayoutX() {
		return this.imageView.getLayoutX();
	}

	@Override
	public final double getLayoutY() {
		return this.imageView.getLayoutY();
	}

	@Override
	public void relocate(final double x, final double y) {
		PlatformFX.runLater(() -> this.imageView.relocate(x - this.xClip, y - this.yClip));
	}

	@Override
	public void relocate(final NumbersPair numbersPair) {
		relocate(numbersPair.x, numbersPair.y);
	}

	public boolean contains(double x, double y) {

		double xCoordinate = this.getLayoutX();
		double yCoordinate = this.getLayoutY();
		double width = this.getWidth();
		double height = this.getHeight();

		if (x < xCoordinate)
			return false;

		else if (x > xCoordinate + width)
			return false;

		else if (y < yCoordinate)
			return false;

		else if (y > yCoordinate + height)
			return false;

		return true;

	}

	public boolean contains(NumbersPair numbersPair) {
		return contains(numbersPair.x, numbersPair.y);
	}

//	public final void setViewport(double topLeftX, double topLeftY, double width, double height) {
//
//		PlatformFX.runLater(() -> {
//
//			Rectangle2D rectangle2d = new Rectangle2D(topLeftX / scale, topLeftY / scale, width / scale,
//					height / scale);
//			this.imageView.setViewport(rectangle2d);
//
//		});
//
//	}

	public final void setClip(double x, double y, double width, double height) {

		PlatformFX.runLater(() -> {

			this.xClip = x;
			this.yClip = y;

			Rectangle rectangle = new Rectangle(x / scale, y / scale, width / scale, height / scale);
			this.imageView.setClip(rectangle);

		});

	}

	public final void setRotate(double value) {
		PlatformFX.runLater(() -> this.imageView.setRotate(value));
	}

	private void setEventHandler(EventHandlerAble eventHandlerAble) {

		PlatformFX.runLater(() -> {

			this.eventHandler = new EventHandler(eventHandlerAble);

			this.imageView.setOnMouseEntered(this.eventHandler);
			this.imageView.setOnMouseExited(this.eventHandler);
			this.imageView.setOnMousePressed(this.eventHandler);

		});

	}

	public final void setImage(final Image image) {

		PlatformFX.runLater(() -> {

			this.imageView.setImage(image.getImage());
			this.imageFront = image;

		});
	}

	public final Image getImage() {
		return this.imageFront;
	}

	public final void setScale(double scale) {

		PlatformFX.runLater(() -> {

			this.scale = scale;

			this.imageView.setScaleX(this.scale);
			this.imageView.setScaleY(this.scale);

			double widthTotal = this.widthOriginal;
			double heightTotal = this.heightOriginal;

			double widthNew = this.scale * widthTotal;
			double heightNew = this.scale * heightTotal;

			double translateX = (widthNew - widthTotal) / 2;
			double translateY = (heightNew - heightTotal) / 2;

			this.imageView.setTranslateX(translateX);
			this.imageView.setTranslateY(translateY);

		});

	}

	public void setWidth(double width) {

		double scale = width / this.widthOriginal;
		setScale(scale);

	}

	public void setWidth(NumbersPair numbersPair) {
		setWidth(numbersPair.x);
	}

	public void setHeight(double height) {

		double scale = height / this.heightOriginal;
		setScale(scale);

	}

	public void setHeight(NumbersPair numbersPair) {
		setHeight(numbersPair.y);
	}

	public void setBack(Image imageBack) {
		this.imageBack = imageBack;
	}

	public void setBack(String path) {
		PlatformFX.runLater(() -> setBack(new Image(path)));
	}

	public void flip() {

		if (this.imageShowing == this.imageFront)
			this.imageShowing = this.imageBack;
		else
			this.imageShowing = this.imageFront;

		setImageShowing();

	}

	public void flipFront() {
		this.imageShowing = this.imageFront;
		setImageShowing();
	}

	public void flipBack() {
		this.imageShowing = this.imageBack;
		setImageShowing();
	}

	private void setImageShowing() {
		PlatformFX.runLater(() -> this.imageView.setImage(this.imageShowing.getImage()));
	}

	public double getWidth() {
		return this.widthOriginal * this.scale;
	}

	public double getHeight() {
		return this.heightOriginal * this.scale;
	}

	public double getScale() {
		return this.scale;
	}

	public double getEventX() {
		return this.eventHandler.getMouseEvent().getX() * this.scale;
	}

	public double getEventY() {
		return this.eventHandler.getMouseEvent().getY() * this.scale;
	}

}
