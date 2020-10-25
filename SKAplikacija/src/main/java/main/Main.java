package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ChoosingView;

public class Main extends Application{
	
	public static Stage window;
	public static Stage window2 = new Stage();
	

	
	
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		ChoosingView cw = new ChoosingView();
		Scene scene = new Scene(cw, 400, 100);
		window.setScene(scene);
		window.setTitle("Choose file");
		window2.initModality(Modality.APPLICATION_MODAL);
		window.show();
		
	}

	
	
	
}
