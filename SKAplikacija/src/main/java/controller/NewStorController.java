package controller;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import main.Main;
import view.NewStorView;

public class NewStorController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent event) {
		Scene scene = new Scene(NewStorView.getInstance(), 500, 500);
		Main.window.setScene(scene);
	}

}
