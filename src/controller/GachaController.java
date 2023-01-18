package controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import app.GSC;
import app.ImageQuerier;
import app.QueryManager;
import app.ImageQuerier.ImageType;
import controller.SceneController.SceneName;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import models.User;

public class GachaController implements Initializable{
	
	@FXML
	private Text textCorgions;
	
	@FXML
	private Text textTreatos;
	
	@FXML
	private Text brokeText;
	
	@FXML
	private Pane resultPane;
	
	@FXML
	private ImageView resultImage;
	
	@FXML
	private Text resultText;
	
	@FXML
	private Button wishButton;
	
	public void onCloseClick() {
		resultPane.setVisible(false);
	}
	
	public void onWishClick() {
		User u = GSC.getInstance().getUser1();
		Integer corgions = u.getCorgions();
		Integer treatos = u.getTreats();
		
		if (corgions < 1 && treatos < 1000)
			brokeText.setVisible(true);
		else {
			if (corgions >= 1) {
				u.setCorgions(corgions-1);
				textCorgions.setText(u.getCorgions().toString());
			} else if (treatos >= 1000) {
				u.setTreats(treatos - 1000);
				textTreatos.setText(u.getTreats().toString());
			}
			
			gachaRandom();
			resultPane.setVisible(true);
			QueryManager qm = new QueryManager();
			qm.updateCurrency(u.getId(), u.getCorgions(), u.getTreats());
		}
	}
	
	public void onBackClick() {
		SceneController sc = new SceneController();
		wishButton.getScene().setRoot(sc.getScene(SceneName.MAIN_SCENE));
	}
	
	private void gachaRandom() {
		Random rand = new Random();
		QueryManager qm = new QueryManager();
		User u = GSC.getInstance().getUser1();
		ImageQuerier iq = new ImageQuerier();
		
		//BICOLOR-TRICOLOR-BEAGLE-PUG-HUSKY-SAMOYED
		char[] inventory = u.getInventory().toCharArray();
		
		Integer res = rand.nextInt(1000);
		System.out.println(res);
		if (res >= 1 && res <= 15) {
			res = rand.nextInt(5) + 1;
			switch (res) {
			case 1:
				resultImage.setImage(iq.getImage(ImageType.TRICOLOR_CORGI));
				resultText.setText("Tricolor Corgi");
				break;
			case 2:
				resultImage.setImage(iq.getImage(ImageType.BEAGLE));
				resultText.setText("Beagle");
				break;
			case 3:
				resultImage.setImage(iq.getImage(ImageType.PUG));
				resultText.setText("Pug Pug Pug!");
				break;
			case 4:
				resultImage.setImage(iq.getImage(ImageType.HUSKY));
				resultText.setText("Husky");
				break;
			case 5:
				resultImage.setImage(iq.getImage(ImageType.SAMOYED));
				resultText.setText("Samoyed Fluff!");
				break;
			default:
				break;
			}
			inventory[res] = '1';
			u.setInventory(String.valueOf(inventory));
			qm.updateInventory(u.getId(), u.getInventory());
		} else {
			resultImage.setImage(iq.getImage(ImageType.TREATOS));
			resultText.setText("100 treatos");
			u.setTreats(u.getTreats()+100);
			qm.updateCurrency(u.getId(), u.getCorgions(), u.getTreats());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User u = GSC.getInstance().getUser1();
		textCorgions.setText(u.getCorgions().toString());
		textTreatos.setText(u.getTreats().toString());
	}
	
	

}
