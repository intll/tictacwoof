package models;

import app.GSC;
import app.ImageQuerier;
import app.ImageQuerier.ImageType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Husky extends Doggos{
	
	private Image logo = new ImageQuerier().getImage(ImageType.HUSKY);
	
	public Husky() {
		super("Husky", "Sheer Power : Destroys an opponent piece at random.");
	}

	@Override
	public void useSkill(BorderPane[][] bp, int[][] gridStatus) {
		int op = GSC.getInstance().isPlayerOnesTurn() ? 2 : 1;
		for (int i=0; i<5; ++i) {
			for (int j=0; j<5; ++j) {
				if (gridStatus[i][j] == op) {
					bp[i][j].setCenter(null);
					gridStatus[i][j] = 0;
				}
			}
		}
	}
	
	@Override
	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}
	

}