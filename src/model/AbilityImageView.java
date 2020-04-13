package model;

import card.CardFighting;
import controller.AbilityImageViewList;
import controller.Credentials;
import controller.Flow;
import enums.ELayerZ;
import utils.EventHandler.EventHandlerAble;
import utils.ImageView;
import utils.ImageViewAble;

public class AbilityImageView implements ImageViewAble, EventHandlerAble {

	public AbilityImageView() {
		createImageView();
	}

	private void createImageView() {

		new ImageView("Ready.png", this, ELayerZ.B);
		this.getImageView().setBack("Tap.png");
		this.getImageView().setWidth(Credentials.INSTANCE.DimensionsAbilityImageView);

		this.getImageView().setVisible(false);

	}

	public void setCanBeUsedVisibleTrue() {

		this.getImageView().setVisible(true);
		this.getImageView().flipFront();

	}

	public void setHasAlreadyBeenUsedVisibleTrue() {

		this.getImageView().setVisible(true);
		this.getImageView().flipBack();

	}

	public void relocate(CardFighting cardFighting) {

		double x = cardFighting.getImageView().getLayoutX();
		x += Credentials.INSTANCE.CoordinatesAbilityImageView.x;

		double y = cardFighting.getImageView().getLayoutY();
		y += Credentials.INSTANCE.CoordinatesAbilityImageView.y;

		this.getImageView().relocateCenter(x, y);

	}

	@Override
	public void handleMouseButtonPressedPrimary() {

		CardFighting cardFighting = AbilityImageViewList.INSTANCE.getCardFightingWIthAbilityImageView(this);
		Flow.INSTANCE.getCurrentGameState().executeCardPressed(cardFighting);
	}

}
