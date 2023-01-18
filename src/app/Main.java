package app;
	
import controller.SceneController;
import controller.SceneController.SceneName;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	SceneController sc = new SceneController();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = sc.getScene(SceneName.LAUNCHER_SCENE);
			Scene scene = new Scene(root, 640, 480);
			GSC.getInstance().setRoot(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setResizable(false);
			primaryStage.setTitle("TicTacWoof! [0.1a]");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
