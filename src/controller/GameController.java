package controller;

import java.net.URL;
import java.util.ResourceBundle;

import app.GSC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameController implements Initializable {

	@FXML
	private Button playerOneSkill;

	@FXML
	private Button playerTwoSkill;

	@FXML
	private ImageView playerOneImage;

	@FXML
	private ImageView playerTwoImage;

	@FXML
	private GridPane gameGrid;
	
	@FXML
	private Text usage1;
	
	@FXML
	private Text usage2;

	public void onPlayerOneSkillClick(ActionEvent e) {
		if (GSC.getInstance().getP1().useSkill()) {
			usage1.setText("Usages: " + GSC.getInstance().getP1().getSkillUsages());
		};
	}

	public void onPlayerTwoSkillClick(ActionEvent e) {
		if (GSC.getInstance().getP2().useSkill()) {
			usage2.setText("Usages: " + GSC.getInstance().getP2().getSkillUsages());
		};
	}

	public void onGridClick(MouseEvent e) {
		Node n = (Node) e.getSource();
		boolean valid = GSC.getInstance().setPawn(getGridRow(n), getGridCol(n));

		if (valid && playerOneImage.getOpacity() == 0.5) {
			playerTwoImage.setOpacity(0.5);
			playerOneImage.setOpacity(1);
			playerOneSkill.setDisable(false);
			playerTwoSkill.setDisable(true);
		} else {
			playerOneImage.setOpacity(0.5);
			playerTwoImage.setOpacity(1);
			playerTwoSkill.setDisable(false);
			playerOneSkill.setDisable(true);
		}
	}

	private Integer getGridRow(Node n) {
		return GridPane.getRowIndex(n) == null ? 0 : GridPane.getRowIndex(n);
	}

	private Integer getGridCol(Node n) {
		return GridPane.getColumnIndex(n) == null ? 0 : GridPane.getColumnIndex(n);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GSC.getInstance().startGame();
		BorderPane gridBoxes[][] = new BorderPane[5][5];
		for (Node n : gameGrid.getChildren()) {
			if (n instanceof BorderPane)
				gridBoxes[getGridRow(n)][getGridCol(n)] = (BorderPane) n;
		}
		GSC.getInstance().setBp(gridBoxes);
		
		playerOneImage.setImage(GSC.getInstance().getP1().getSelectedChar().getLogo());
		playerTwoImage.setImage(GSC.getInstance().getP2().getSelectedChar().getLogo());
	}

}
