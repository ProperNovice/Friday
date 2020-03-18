package controller;

import interfaces.ISaveLoadStateAble;
import utils.ArrayList;
import utils.Logger;

public enum Lists implements ISaveLoadStateAble {

	INSTANCE;

	private ArrayList<ISaveLoadStateAble> iSaveLoadStateAbles = new ArrayList<ISaveLoadStateAble>();

	public void instantiate() {

		createLists();
		addSaveStateAbles();

		Logger.INSTANCE.logNewLine("lists instantiated");

	}

	private void createLists() {

	}

	private void addSaveStateAbles() {

	}

	@Override
	public void saveGameStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.saveGameStart();

	}

	@Override
	public void loadGameStart() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.loadGameStart();

	}

	@Override
	public void saveState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.saveState();

	}

	@Override
	public void loadState() {

		for (ISaveLoadStateAble iSaveLoadStateAble : this.iSaveLoadStateAbles)
			iSaveLoadStateAble.loadState();

	}

}
