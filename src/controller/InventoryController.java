package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import app.GSC;
import controller.SceneController.SceneName;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.User;

public class InventoryController implements Initializable{
	
	@FXML
	private ImageView image1;
	
	@FXML
	private ImageView image2;
	
	@FXML
	private ImageView image3;
	
	@FXML
	private ImageView image4;
	
	@FXML
	private ImageView image5;
	
	@FXML
	private ImageView image6;
	
	@FXML
	private Text corgionsText;
	
	@FXML
	private Text treatosText;
	
	public void onBackClick() {
		SceneController sc = new SceneController();
		image1.getScene().setRoot(sc.getScene(SceneName.MAIN_SCENE));
	}
	
	private void setGrayscale(ImageView iv) {
		ColorAdjust ca = new ColorAdjust();
		ca.setSaturation(-1.0);
		ca.setBrightness(-0.6);
		iv.setEffect(ca);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User u = GSC.getInstance().getUser1();
		corgionsText.setText(u.getCorgions().toString());
		treatosText.setText(u.getTreats().toString());
		ArrayList<ImageView> arr = new ArrayList<ImageView>(Arrays.asList(image1, image2, image3, image4, image5, image6));
		
		String inventory = u.getInventory();
		for (int i=0; i<inventory.length(); ++i) {
			if (inventory.charAt(i) == '0')
				setGrayscale(arr.get(i));
		}
	}

}
