package models;

import app.GSC;
import app.ImageQuerier;
import app.ImageQuerier.ImageType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Beagle extends Doggos {
	
	private Image logo = new ImageQuerier().getImage(ImageType.BEAGLE);
	
	public Beagle() {
		super("Beagle", "Cunning Mastery : Move your piece to a random available location");
	}

	@Override
	public void useSkill(BorderPane[][] bp, int[][] gridStatus) {
		int curr = GSC.getInstance().isPlayerOnesTurn() ? 1 : 2;
		
		int posP1x = -1;
		int posP1y = -1;
		
		int nullX = -1;
		int nullY = -1;
		
		for (int i=0; i<5; ++i) {
			for (int j=0; j<5; ++j) {
				if (gridStatus[i][j] == curr) {
					posP1x = i;
					posP1y = j;
				} else if (gridStatus[i][j] == 0) {
					nullX = i;
					nullY = j;
				}
			}
		}
		
		if (posP1x != -1 && nullX != -1) {
			System.out.print("TEST");
			gridStatus[posP1x][posP1y] = curr;
			gridStatus[nullX][nullY] = 0;
			
			bp[nullX][nullY].setCenter(new ImageQuerier().getImageView(GSC.getInstance().getP1().getSelectedChar().getLogo(), 60));
			bp[posP1x][posP1y].setCenter(null);
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
