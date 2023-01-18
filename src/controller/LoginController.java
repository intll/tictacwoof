package controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.GSC;
import app.ImageQuerier;
import app.QueryManager;
import app.ImageQuerier.ImageType;
import controller.SceneController.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.User;

public class LoginController implements Initializable {

	@FXML
	private Button backButton;

	@FXML
	private Button loginButton;

	@FXML
	private Button registerButton;

	@FXML
	private TextField usernameText;

	@FXML
	private PasswordField passwordText;

	@FXML
	private Text warningText;

	@FXML
	private ImageView loginBackground;

	public void onBackClick(ActionEvent e) {

		SceneController sc = new SceneController();
		if (GSC.getInstance().getUser1() != null) {
			backButton.getScene().setRoot(sc.getScene(SceneName.MAIN_SCENE));
		} else {
			backButton.getScene().setRoot(sc.getScene(SceneName.LAUNCHER_SCENE));
		}

	}

	public void onLoginClick(ActionEvent e) {
		String username = usernameText.getText();
		String password = passwordText.getText();
		
		if (username.isBlank() || password.isBlank())
			return;
		
		QueryManager qm = new QueryManager();
		
		User searchResult = qm.searchUser(username, password);
		if (searchResult != null && !searchResult.getId().equals("WPASS")) {
			SceneController sc = new SceneController();
			
			User user1 = GSC.getInstance().getUser1();
			if (user1 == null) {
				GSC.getInstance().setUser1(searchResult);
				loginButton.getScene().setRoot(sc.getScene(SceneName.MAIN_SCENE));
			} else if (!user1.getId().equals(searchResult.getId())){
				GSC.getInstance().setUser2(searchResult);
				loginButton.getScene().setRoot(sc.getScene(SceneName.CHARACTER_SCENE));
			}
			
		} else {                
			warningText.setVisible(true);
		}
		
	}

	public void onRegisterClick(ActionEvent e) {
		SceneController sc = new SceneController();
		registerButton.getScene().setRoot(sc.getScene(SceneName.REGISTER_SCENE));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ImageQuerier iq = new ImageQuerier();
		if (GSC.getInstance().getUser1() != null) {
			loginBackground.setImage(iq.getImage(ImageType.LOGIN_BACKGROUND2));
		}
	}

}
