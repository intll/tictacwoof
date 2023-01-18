package models;

import app.ImageQuerier;
import app.ImageQuerier.ImageType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Pug extends Doggos{
	
	private Image logo = new ImageQuerier().getImage(ImageType.PUG);

	public Pug() {
		super("Pug", "Blinding Cuteness : Blinds the current board to both sides!");
	}

	@Override
	public void useSkill(BorderPane[][] bp, int[][] gridStatus) {
		ImageQuerier iq = new ImageQuerier();
		for (int i=0; i<5; ++i) {
			for (int j=0; j<5; ++j) {
				if (gridStatus[i][j] != 0) {
					bp[i][j].setCenter(iq.getImageView(iq.getImage(ImageType.QM), 50));
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
