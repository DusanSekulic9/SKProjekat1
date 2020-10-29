package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import main.Main;
import view.CreateUpdateView;

public class CreateUpdateController implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent event) {
		Scene scene = new Scene(new CreateUpdateView(), 400, 400);
		Main.window3.setScene(scene);
		Main.window3.show();
	}

}
