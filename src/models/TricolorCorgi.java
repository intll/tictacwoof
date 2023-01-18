package models;

import app.GSC;
import app.ImageQuerier;
import app.ImageQuerier.ImageType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class TricolorCorgi extends Doggos{
	
	private Image logo = new ImageQuerier().getImage(ImageType.TRICOLOR_CORGI);

	public TricolorCorgi() {
		super("Tricolor Corgi", "Catch! : Gain a corner piece, if there is an opponent there, replace it.");
	}

	@Override
	public void useSkill(BorderPane[][] bp, int[][] gridStatus) {
		int curr = GSC.getInstance().isPlayerOnesTurn() ? 1 : 2;
		Player p = curr == 1 ? GSC.getInstance().getP1() : GSC.getInstance().getP2();
		
		if (gridStatus[0][0] != curr)
		{
			gridStatus[0][0] = curr;
			bp[0][0].setCenter(new ImageQuerier().getImageView(p.getSelectedChar().getLogo(), 60));
		} else if (gridStatus[0][4] != curr) {
			gridStatus[0][4] = curr;
			bp[0][4].setCenter(new ImageQuerier().getImageView(p.getSelectedChar().getLogo(), 60));
		} else if (gridStatus[4][0] != curr) {
			gridStatus[4][0] = curr;
			bp[4][0].setCenter(new ImageQuerier().getImageView(p.getSelectedChar().getLogo(), 60));
		} else {
			gridStatus[4][4] = curr;
			bp[4][4].setCenter(new ImageQuerier().getImageView(p.getSelectedChar().getLogo(), 60));
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
