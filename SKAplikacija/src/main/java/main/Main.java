package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.ChooseComponentView;
import view.ChoosingView;

public class Main extends Application{
	
	public static Stage window;
	public static Stage window2 = new Stage();
	public static Stage window3 = new Stage();
	public static Stage window4 = new Stage();
	

	
	
	public static void main(String[] args) {
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		ChooseComponentView cw = new ChooseComponentView();
		Scene scene = new Scene(cw, 400, 200);
		window.setScene(scene);
		window.setTitle("Choose implementation");
		window2.initModality(Modality.APPLICATION_MODAL);
		window.show();
		
	}

	
	
	
}
