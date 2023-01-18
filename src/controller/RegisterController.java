package controller;

import app.GSC;
import app.QueryManager;
import controller.SceneController.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController {
	
	@FXML
	private Button backButton;
	
	@FXML
	private Button registerButton;
	
	@FXML
	private TextField emailText;
	
	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	
	@FXML
	private Text warningTextTaken;
	
	@FXML
	private Text warningTextInvalid;
	
	public void onBackClick(ActionEvent e) {
		SceneController sc = new SceneController();
		if (GSC.getInstance().getUser1() == null) {
			registerButton.getScene().setRoot(sc.getScene(SceneName.LAUNCHER_SCENE));
		}  else {
			registerButton.getScene().setRoot(sc.getScene(SceneName.MAIN_SCENE));
		}
	}
	
	public void onRegisterClick(ActionEvent e) {
		String email = emailText.getText();
		String username = usernameText.getText();
		String password = passwordText.getText();
		
		if (email.isBlank() || username.isBlank() || password.isBlank())
			return;
		
		if (!email.contains("@") || !email.endsWith(".com") || email.length() > 30 || username.length() > 12 || password.length() > 20)
		{
			warningTextInvalid.setVisible(true);
			warningTextTaken.setVisible(false);
			return;
		}
		
		QueryManager qm = new QueryManager();
		if (qm.insertUser(email, username, password)) {
			SceneController sc = new SceneController();
			
			if (GSC.getInstance().getUser1() == null) {
				GSC.getInstance().setUser1(qm.searchUser(email, password));
				registerButton.getScene().setRoot(sc.getScene(SceneName.MAIN_SCENE));
			}  else {
				GSC.getInstance().setUser2(qm.searchUser(email, password));
				registerButton.getScene().setRoot(sc.getScene(SceneName.CHARACTER_SCENE));
			}
			
		} else {
			warningTextInvalid.setVisible(false);
			warningTextTaken.setVisible(true);
		}
	
	}

}
