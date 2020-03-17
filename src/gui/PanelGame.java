package gui;

import utils.PanelGameInstance;
import utils.Parent;

public class PanelGame extends Parent {

	public PanelGame() {
		PanelGameInstance.INSTANCE.setPanelGameInstance(this);
	}

}
