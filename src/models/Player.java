package models;

import app.GSC;

public class Player {
	
	private Integer skillUsages;
	private Doggos selectedChar;
	
	public Player( Doggos selectedChar) {
		super();
		this.skillUsages = 2;
		this.selectedChar = selectedChar;
	}

	public boolean useSkill() {
		if (skillUsages > 0) {
			selectedChar.useSkill(GSC.getInstance().getBp(), GSC.getInstance().getGridStatus());
			skillUsages--;
			return true;
		}
		return false;
	}

	public Integer getSkillUsages() {
		return skillUsages;
	}

	public void setSkillUsages(Integer skillUsages) {
		this.skillUsages = skillUsages;
	}

	public Doggos getSelectedChar() {
		return selectedChar;
	}

	public void setSelectedChar(Doggos selectedChar) {
		this.selectedChar = selectedChar;
	}
	
}
