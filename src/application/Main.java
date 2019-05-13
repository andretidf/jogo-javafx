package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage stage;

	private static Scene mainScene;
	private static Scene inputTerrestre;
	private static Scene inputAquatico;
	private static Scene view5;

	@Override
	public void start(Stage Primarystage) {
		this.stage = Primarystage;
		try {
			Parent fxmlMain = FXMLLoader.load(getClass().getResource("/gui/View.fxml"));
			mainScene = new Scene(fxmlMain);

			Parent fxmlView4 = FXMLLoader.load(getClass().getResource("/gui/inputTerrestre.fxml"));
			inputTerrestre = new Scene(fxmlView4);

			Parent fxmlView5 = FXMLLoader.load(getClass().getResource("/gui/inputAquatico.fxml"));
			inputAquatico = new Scene(fxmlView5);

			Parent fxmlView6 = FXMLLoader.load(getClass().getResource("/gui/input2.fxml"));
			view5 = new Scene(fxmlView6);

			Primarystage.setScene(mainScene);
			Primarystage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void changeScreen(String scr) throws IOException {
		switch (scr) {
		case "main":
			stage.setScene(mainScene);
			break;

		case "inputTerrestre":
			stage.setScene(inputTerrestre);
			break;
		case "inputAquatico":
			stage.setScene(inputAquatico);
			break;
		case "input2":
			stage.setScene(view5);
			break;
		case "close":

			stage.close();
			break;

		}
	}

	public static void main(String[] args) {

		launch(args);
	}
}
