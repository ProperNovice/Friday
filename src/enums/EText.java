package enums;

import utils.Text;

public enum EText {

	BLANK("", TextTypeEnum.INDICATOR),
	CONTINUE("Continue", TextTypeEnum.OPTION),
	RESTART("Restart", TextTypeEnum.OPTION),
	DRAW_HAZARD_CARDS("Draw hazard cards", TextTypeEnum.INDICATOR),
	CHOOSE_HAZARD_TO_FIGHT("Choose hazard\nto fight", TextTypeEnum.INDICATOR),
	FIGHT_THE_HAZARD("Fight the hazard", TextTypeEnum.OPTION),
	SKIP_THE_HAZARD("Skip the hazard", TextTypeEnum.OPTION),
	RESOLVE_FIGHT("Resolve fight", TextTypeEnum.OPTION),
	DRAW_CARD_FREE("Draw card free", TextTypeEnum.OPTION),
	DRAW_CARD_ONE_LIFE("Draw card pay life", TextTypeEnum.OPTION),
	CHOOSE_ABILITY("Choose ability", TextTypeEnum.INDICATOR),
	CHOOSE_CARD("Choose card", TextTypeEnum.INDICATOR),
	DISCARD_CARD("Discard card", TextTypeEnum.INDICATOR),
	SHOW_BOARD("Show board", TextTypeEnum.OPTION),
	SHOW_PANEL("Show panel", TextTypeEnum.OPTION),
	PUT_CARD_ON_TOP("Put card on top", TextTypeEnum.INDICATOR),
	USE_DOUBLE("Use double", TextTypeEnum.INDICATOR),
	YES("Yes", TextTypeEnum.OPTION),
	NO("No", TextTypeEnum.OPTION),
	FIGHT_WON("Fight won", TextTypeEnum.INDICATOR),
	FIGHT_LOST("Fight lost", TextTypeEnum.INDICATOR),
	DESTROY_CARD("Destroy card", TextTypeEnum.INDICATOR),
	
	;

	private String string = null;
	private TextTypeEnum textTypeEnum = null;

	public enum TextTypeEnum {
		INDICATOR, OPTION
	}

	private EText(String string, TextTypeEnum textTypeEnum) {
		this.string = string;
		this.textTypeEnum = textTypeEnum;
	}
	
	public void showText() {
		Text.INSTANCE.showText(this);
	}

	public String getString() {
		return this.string;
	}

	public TextTypeEnum getTextTypeEnum() {
		return this.textTypeEnum;
	}

}
