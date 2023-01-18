package controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.GSC;
import controller.SceneController.SceneName;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainController implements Initializable {
	
	@FXML
	private Button playButton;
	
	@FXML
	private Button inventoryButton;
	
	@FXML
	private Button wishButton;
	
	@FXML 
	private Text textUsername;
	
	@FXML 
	private Pane alertPane;
	
	public void onExitClick() {
		Platform.exit();
	}
	
	public void onPlayClick() {
		if (GSC.getInstance().getUser2() == null) {
			SceneController sc = new SceneController();
			playButton.getScene().setRoot(sc.getScene(SceneName.LOGIN_SCENE));
		}
		else {
			alertPane.setVisible(true);
		}
	}
	
	public void onYesClick() {
		SceneController sc = new SceneController();
		playButton.getScene().setRoot(sc.getScene(SceneName.CHARACTER_SCENE));
	}
	
	public void onNoClick() {
		SceneController sc = new SceneController();
		alertPane.getScene().setRoot(sc.getScene(SceneName.LOGIN_SCENE));
	}
	
	public void onWishClick() {
		SceneController sc = new SceneController();
		wishButton.getScene().setRoot(sc.getScene(SceneName.GACHA_SCENE));
	}
	
	public void onInventoryClick() {
		SceneController sc = new SceneController();
		inventoryButton.getScene().setRoot(sc.getScene(SceneName.INVENTORY_SCENE));
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(GSC.getInstance().getUser1().getUsername());
		textUsername.setText(GSC.getInstance().getUser1().getUsername());
	}

}
