package app;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageQuerier {

	public enum ImageType {
		BICOLOR_CORGI, TRICOLOR_CORGI, BEAGLE, PUG, HUSKY, SAMOYED, LOGIN_BACKGROUND2, TREATOS, QM
	}
	
	//BICOLOR-TRICOLOR-BEAGLE-PUG-HUSKY-SAMOYED, 0: False, 1: True
	public ImageType getTypeFromIndex(int i) {
		switch (i) {
		case 0:
			return ImageType.BICOLOR_CORGI;
		case 1:
			return ImageType.TRICOLOR_CORGI;
		case 2:
			return ImageType.BEAGLE;
		case 3:
			return ImageType.PUG;
		case 4:
			return ImageType.HUSKY;
		case 5:
			return ImageType.SAMOYED;
		default:
			return null;
		}
	}

	public Image getImage(ImageType i) {

		String path = "";
		switch (i) {
		case BICOLOR_CORGI:
			path = "BicolorCorgi.png";
			break;
		case TRICOLOR_CORGI:
			path = "TricolorCorgi.png";
			break;
		case BEAGLE:
			path = "Beagle.png";
			break;
		case PUG:
			path = "Pug.png";
			break;
		case HUSKY:
			path = "Husky.png";
			break;
		case SAMOYED:
			path = "Samoyed.png";
			break;
		case LOGIN_BACKGROUND2:
			path = "LoginPlayer2.png";
			break;
		case TREATOS:
			path = "Treatos.png";
			break;
		case QM:
			path = "QM.png";
			break;
		}
		
		try {
			return new Image(new FileInputStream("src/assets/" + path));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[!] Resource access error!");
			return null;
		}
	}
	

	public ImageView getImageView(Image i, int size) {
		ImageView iv  = new ImageView();
		iv.setFitWidth(size);
		iv.setPreserveRatio(true);
		iv.setImage(i);
		return iv;
	}

}
