package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class SceneController {
	
	public enum SceneName {
		LAUNCHER_SCENE,
		REGISTER_SCENE,
		LOGIN_SCENE,
		MAIN_SCENE,
		GAME_SCENE,
		GACHA_SCENE,
		INVENTORY_SCENE,
		CHARACTER_SCENE,
		WIN_SCENE
	}
	
	public Parent getScene(SceneName s) {
		
		String path = "";
		
		switch (s) {
		case LAUNCHER_SCENE:
			path = "launcherScene.fxml";
			break;
		case MAIN_SCENE:
			path = "mainScene.fxml";
			break;
		case REGISTER_SCENE:
			path = "registerScene.fxml";
			break;
		case LOGIN_SCENE:
			path = "loginScene.fxml";
			break;
		case GAME_SCENE:
			path = "gameScene.fxml";
			break;
		case GACHA_SCENE:
			path = "gachaScene.fxml";
			break;
		case INVENTORY_SCENE:
			path = "inventoryScene.fxml";
			break;
		case CHARACTER_SCENE:
			path = "characterScene.fxml";
			break;
		case WIN_SCENE:
			path = "winScene.fxml";
			break;
		default:
			break;
		}
		
		try {
			return FXMLLoader.load(getClass().getResource("/scenes/" + path));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[!] Fatal Error: Resource not found.");
			System.exit(1);
			return null;
		}
		
	}

}
