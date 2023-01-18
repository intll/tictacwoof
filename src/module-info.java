module TicTacWoof {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens app to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
}
