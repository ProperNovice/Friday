package gui;

import javafx.scene.image.ImageView;
import utils.EventHandler;
import utils.Parent;
import utils.ShutDown;
import utils.EventHandler.EventHandlerAble;

public class Panel extends Parent implements EventHandlerAble {

	private ImageView background = new ImageView("/images/misc/background.png");

	public Panel() {

		this.background.toBack();
		this.background.setOnMousePressed(new EventHandler(this));

		this.getChildren().add(this.background);
		this.getChildren().add(new PanelGame());

	}

	@Override
	public void handleMouseButtonPressedSecondary() {
		ShutDown.INSTANCE.execute();
	}

}
