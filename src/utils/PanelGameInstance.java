package utils;

import gui.PanelGame;

public enum PanelGameInstance {

	INSTANCE;

	private PanelGame panelGameInstance = null;

	public void setPanelGameInstance(PanelGame panelGame) {
		this.panelGameInstance = panelGame;
	}

	public PanelGame getPanelGameInstance() {
		return this.panelGameInstance;
	}

}
