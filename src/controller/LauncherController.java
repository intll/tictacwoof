package controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.Connector;
import controller.SceneController.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class LauncherController implements Initializable {
	
	@FXML
	private Button loginButton;
	
	@FXML
	private CheckBox connectionStatus;
	
	public void onLoginClick(ActionEvent e) {
		SceneController sc = new SceneController();
		loginButton.getScene().setRoot(sc.getScene(SceneName.LOGIN_SCENE));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (Connector.getConnection() != null)
			connectionStatus.setIndeterminate(false);
	}

}
