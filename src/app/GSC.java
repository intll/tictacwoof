package app;

import controller.SceneController;
import controller.SceneController.SceneName;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import models.Player;
import models.User;

public class GSC {

	private Scene root;
	
	private User user1;
	private User user2;
	private Player p1;
	private Player p2;
	private int moves;
	private int gridStatus[][] = new int[5][5];
	private BorderPane bp[][] = new BorderPane[5][5];
	private boolean isPlayerOnesTurn;
	private int gameResult;

	private final static GSC INSTANCE = new GSC();

	public static GSC getInstance() {
		return INSTANCE;
	}

	// Game methods PLAYER1 -> 1 PLAYER2 -> 2
	public void startGame() {
		gridStatus = new int[5][5];
		isPlayerOnesTurn = true;
		moves = 0;
		gameResult = 0;
	}

	public boolean setPawn(int row, int column) {
		Player p = isPlayerOnesTurn ? p1 : p2;
		if (gridStatus[row][column] == 0) {
			ImageQuerier iq = new ImageQuerier();
			bp[row][column].setCenter(iq.getImageView(p.getSelectedChar().getLogo(), 60));
			gridStatus[row][column] = isPlayerOnesTurn ? 1 : 2;
			isPlayerOnesTurn = !isPlayerOnesTurn;
			++moves;
			validate();
			return true;
		}
		return false;
	}

	public void validate() {
		// Rows
		for (int i = 0; i < 5; ++i) {
			if (gridStatus[i][0] == 0)
				continue;
			if (gridStatus[i][0] == gridStatus[i][1] && gridStatus[i][1] == gridStatus[i][2]
					&& gridStatus[i][2] == gridStatus[i][3] && gridStatus[i][3] == gridStatus[i][4]) {
				reportWin(gridStatus[i][0]);
			}
		}

		// Columns
		for (int i = 0; i < 5; ++i) {
			if (gridStatus[0][i] == 0)
				continue;
			if (gridStatus[0][i] == gridStatus[1][i] && gridStatus[1][i] == gridStatus[2][i]
					&& gridStatus[2][i] == gridStatus[3][i] && gridStatus[3][i] == gridStatus[4][i]) {
				reportWin(gridStatus[0][i]);
			}
		}

		// Diagonals, 1 and 2
		if (gridStatus[0][0] != 0 &&gridStatus[0][0] == gridStatus[1][1] && gridStatus[1][1] == gridStatus[2][2]
				&& gridStatus[2][2] == gridStatus[3][3] && gridStatus[3][3] == gridStatus[4][4]) {
			reportWin(gridStatus[0][0]);
		}

		if (gridStatus[0][4] != 0 && gridStatus[0][4] == gridStatus[1][3] && gridStatus[1][3] == gridStatus[2][2]
				&& gridStatus[2][2] == gridStatus[3][1] && gridStatus[3][1] == gridStatus[4][0]) {
			reportWin(gridStatus[0][4]);
		}
		
		if (moves >= 25) {
			reportWin(0);
		}

	}
	
	public void reportWin(int c) {
		gameResult = c;
		QueryManager qm = new QueryManager();
		if (c == 1) {
			qm.updateCurrency(user1.getId(), user1.getCorgions()+2, user1.getTreats());
		} else if (c == 2) {
			qm.updateCurrency(user2.getId(), user2.getCorgions()+2, user2.getTreats());
		} else {
			qm.updateCurrency(user1.getId(), user1.getCorgions()+1, user1.getTreats());
			qm.updateCurrency(user2.getId(), user2.getCorgions()+1, user2.getTreats());
		}
		SceneController sc = new SceneController();
		root.setRoot(sc.getScene(SceneName.WIN_SCENE)); 
		p1 = null;
		p2 = null;
		System.out.println(c);
	}

	// Setters and Getters
	public Scene getRoot() {
		return root;
	}

	public void setRoot(Scene root) {
		this.root = root;
	}
	
	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public int[][] getGridStatus() {
		return gridStatus;
	}

	public void setGridStatus(int[][] gridStatus) {
		this.gridStatus = gridStatus;
	}

	public BorderPane[][] getBp() {
		return bp;
	}

	public void setBp(BorderPane[][] bp) {
		this.bp = bp;
	}

	public boolean isPlayerOnesTurn() {
		return isPlayerOnesTurn;
	}

	public void setPlayerOnesTurn(boolean isPlayerOnesTurn) {
		this.isPlayerOnesTurn = isPlayerOnesTurn;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public int getMoves() {
		return moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	public int getGameResult() {
		return gameResult;
	}

	public void setGameResult(int gameResult) {
		this.gameResult = gameResult;
	}
	

}
