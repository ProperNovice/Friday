package utils;

import enums.ELayerZ;

public enum LayerZ {

	INSTANCE;

	private HashMap<ELayerZ, ArrayList<ImageView>> layerZ = new HashMap<>();
	private HashMap<ImageView, javafx.scene.image.ImageView> listImageViewsFX = new HashMap<>();

	private LayerZ() {
		addLayers();
	}

	private void addLayers() {
		for (ELayerZ eLayerZ : ELayerZ.values())
			this.layerZ.put(eLayerZ, new ArrayList<ImageView>());

	}

	public void addImageViewAbleToLayer(ImageView imageView, ELayerZ eLayerZ,
			javafx.scene.image.ImageView imageViewFX) {

		this.layerZ.get(eLayerZ).addLast(imageView);
		this.listImageViewsFX.put(imageView, imageViewFX);

	}

	public void addImageViewToLastLayer(ImageView imageView, javafx.scene.image.ImageView imageViewFX) {
		addImageViewAbleToLayer(imageView, ELayerZ.values()[ELayerZ.values().length - 1], imageViewFX);
	}

	public void toFrontImageview(ImageView imageView) {

		ArrayList<ImageView> list = getListContainingImageViewAble(imageView);
		list.remove(imageView);
		list.addLast(imageView);
		toFront();

	}

	public void toBackImageview(ImageView imageView) {

		ArrayList<ImageView> list = getListContainingImageViewAble(imageView);
		list.remove(imageView);
		list.addFirst(imageView);
		toFront();

	}

	private ArrayList<ImageView> getListContainingImageViewAble(ImageView imageView) {

		for (ELayerZ eLayerZ : ELayerZ.values()) {

			ArrayList<ImageView> list = this.layerZ.get(eLayerZ);

			if (!list.contains(imageView))
				continue;

			return list;

		}

		ShutDown.INSTANCE.execute("LayerZ getListContainingImageView\ndidn't find imageView");

		return null;

	}

	private void toFront() {

		for (ELayerZ eLayerZ : ELayerZ.values())
			for (ImageView imageView : this.layerZ.get(eLayerZ))
				this.listImageViewsFX.get(imageView).toFront();

	}

}
