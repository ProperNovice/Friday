package utils;

import javafx.scene.input.KeyCode;

public enum KeyCodeHandler {

	INSTANCE;

	private HashMap<KeyCode, Integer> keyCodes = new HashMap<KeyCode, Integer>();

	private KeyCodeHandler() {

		addKeyCode(KeyCode.Q);
		addKeyCode(KeyCode.W);
		addKeyCode(KeyCode.E);
		addKeyCode(KeyCode.R);
		addKeyCode(KeyCode.T);

	}

	private void addKeyCode(KeyCode keyCode) {
		this.keyCodes.put(keyCode, this.keyCodes.size() + 1);
	}

	public int getKeyCodeInt(KeyCode keyCode) {

		if (this.keyCodes.containsKey(keyCode))
			return this.keyCodes.get(keyCode);
		else
			return -1;

	}

}
