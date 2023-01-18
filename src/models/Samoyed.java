package models;

import app.GSC;
import app.ImageQuerier;
import app.ImageQuerier.ImageType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Samoyed extends Doggos{
	
	private Image logo = new ImageQuerier().getImage(ImageType.SAMOYED);
	
	public Samoyed() {
		super("Samoyed", "Naughty Wag : Switches one of your pieces to the opponents at random.");
	}

	@Override
	public void useSkill(BorderPane[][] bp, int[][] gridStatus) {
		int op = GSC.getInstance().isPlayerOnesTurn() ? 1 : 2;
		
		int posP1x = -1;
		int posP2x = -1;
		int posP1y = -1;
		int posP2y = -1;
		
		for (int i=0; i<5; ++i) {
			for (int j=0; j<5; ++j) {
				if (gridStatus[i][j] == op) {
					posP1x = i;
					posP1y = j;
				} else if (gridStatus[i][j] != op && gridStatus[i][j] != 0) {
					posP2x = i;
					posP2y = j;
				}
			}
		}
		
		if (posP1x == -1 || posP2x == -1)
			return;
		
		gridStatus[posP1x][posP1y] = 2;
		gridStatus[posP2x][posP2y] = 1;
		
		bp[posP1x][posP1y].setCenter(new ImageQuerier().getImageView(GSC.getInstance().getP2().getSelectedChar().getLogo(), 60));
		bp[posP2x][posP2y].setCenter(new ImageQuerier().getImageView(GSC.getInstance().getP1().getSelectedChar().getLogo(), 60));
	}
	
	@Override
	public Image getLogo() {
		return logo;
	}

	public void setLogo(Image logo) {
		this.logo = logo;
	}
	

}
