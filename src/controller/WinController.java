package controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.GSC;
import controller.SceneController.SceneName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class WinController implements Initializable {
	
	@FXML Text winText;
	
	@FXML Text addText;
	
	public void onReturnClick(ActionEvent e) {
		SceneController sc = new SceneController();
		addText.getScene().setRoot(sc.getScene(SceneName.MAIN_SCENE));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int c = GSC.getInstance().getGameResult();
		if (c == 0) {
			winText.setText("It's a draw!");
			addText.setText("+1 Corgion");
		} else if(c == 1) {
			winText.setText("Player 1 wins!");
			addText.setText("+2 Corgions");
		} else {
			winText.setText("Player 2 wins!");
			addText.setText("+2 Corgions");
		}
	}

}
