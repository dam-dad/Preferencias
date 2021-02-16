package dad.javafx.preferencias;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	public static final String APP_NAME = "MisPreferencias";
	
	public static Preferences preferences;  
	
	public static Stage primaryStage; 
	
	private MainController controller;
	
	@Override
	public void init() throws Exception {
		preferences = Preferences.load();
		super.init();
	}
	
	@Override
	public void stop() throws Exception {
		preferences.save();
		super.stop();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;

		controller = new MainController();
		
		Scene scene = new Scene(controller.getView());
		
		primaryStage.setTitle("Preferencies");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
