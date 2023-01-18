package controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.GSC;
import controller.SceneController.SceneName;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Beagle;
import models.BicolorCorgi;
import models.Doggos;
import models.Husky;
import models.Player;
import models.Pug;
import models.Samoyed;
import models.TricolorCorgi;
import models.User;

public class CharacterController implements Initializable {
	
	final int TOTAL_CHARACTERS = 6;
	
	@FXML
	private ImageView mainImage;
	
	@FXML
	private Text titleText;
	
	@FXML
	private Text indexTextHidden;
	
	@FXML
	private Text descText;
	
	@FXML
	private Text nameText;
	
	
	public void onBackwardClick() {
		User u = GSC.getInstance().getP1() == null ? GSC.getInstance().getUser1() : GSC.getInstance().getUser2();
		String inv = u.getInventory();
		
		Integer curr = Integer.parseInt(indexTextHidden.getText());
		for (int i = (curr-1); i!=curr; --i) {
			if (i < 0)
				i = TOTAL_CHARACTERS-1;
			if (inv.charAt(i) == '1') {
				Doggos d = getCharacterType(i);
				mainImage.setImage(d.getLogo());
				nameText.setText(d.getName());
				descText.setText(d.getDesc());
				indexTextHidden.setText(Integer.toString(i));
				break;
			}
		}
	}
	
	public void onForwardClick() {
		User u = GSC.getInstance().getP1() == null ? GSC.getInstance().getUser1() : GSC.getInstance().getUser2();
		String inv = u.getInventory();
		
		Integer curr = Integer.parseInt(indexTextHidden.getText());
		for (int i = curr+1; i!=curr; ++i) {
			i%=TOTAL_CHARACTERS;
			if (inv.charAt(i) == '1') {
				Doggos d = getCharacterType(i);
				mainImage.setImage(d.getLogo());
				nameText.setText(d.getName());
				descText.setText(d.getDesc());
				indexTextHidden.setText(Integer.toString(i));
				break;
			}
		}
	}
	
	private Doggos getCharacterType(int index) {
		switch (index) {
		case 0:
			return new BicolorCorgi();
		case 1:
			return new TricolorCorgi();
		case 2:
			return new Beagle();
		case 3:
			return new Pug();
		case 4:
			return new Husky();
		case 5:
			return new Samoyed();
		default:
			return null;
		}
	}
	
	public void onChooseClick() {
		GSC g = GSC.getInstance();
		Integer curr = Integer.parseInt(indexTextHidden.getText());
		Doggos d = getCharacterType(curr);
		SceneController sc = new SceneController();
		
		if(g.getP1() == null) {
			g.setP1(new Player(d));
			nameText.getScene().setRoot(sc.getScene(SceneName.CHARACTER_SCENE));
		} else {
			g.setP2(new Player(d));
			nameText.getScene().setRoot(sc.getScene(SceneName.GAME_SCENE));
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (GSC.getInstance().getP1() != null) {
			titleText.setText("Player 2");
		}
	}

}
