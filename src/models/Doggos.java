package models;

import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public abstract class Doggos {
	
	//BICOLOR-TRICOLOR-BEAGLE-PUG-HUSKY-SAMOYED, 0: False, 1: True
	private String name;
	private String desc;
	private Image logo;
	
	public Doggos(String name, String desc) {
		super();
		this.name = name;
		this.desc = desc;
		this.logo = null;
	}

	public abstract void useSkill(BorderPane[][] bp, int[][] gridStatus);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}
	
	
	
	
}
