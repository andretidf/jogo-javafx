package gui;

import java.io.IOException;
import java.util.Optional;

import application.Main;
import dao.AnimalDAO;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Animal;
import model.Habitat;

public class ViewController {

	@FXML
	private Text lbTexto;
	@FXML
	public Label lbTextoInput;
	@FXML
	private Button btYes;
	@FXML
	private Button btCancel;

	@FXML
	private TextField inputTextoAnimal;
	@FXML
	private TextField inputTextoDiferenca;

	private Animal animal;

	public void onBtYesAction() throws IOException {

		Optional<ButtonType> result = Alerts.showConfirmation("Jogo", "O ANIMAL QUE VOCÊ PENSOU VIVE NA ÁGUA?");
		if (result.get() == ButtonType.OK) {
			onBtYesAction1(Habitat.AQUATICO);
		} else {
			onBtYesAction1(Habitat.TERRESTRE);
		}

	}

	public void onBtYesAction1(Habitat habitat) throws IOException {

		int indice = AnimalDAO.find(habitat).size() - 1;
		if (indice < 0) {
			indice = 0;
		}

		// caracteristica
		while (indice >= 0) {
			animal = AnimalDAO.find(habitat).get(indice);
			Optional<ButtonType> result1 = Alerts.showConfirmation("Jogo",
					"O ANIMAL QUE VOCÊ PENSOU " + animal.getCaracteristica().toUpperCase() + " ?");
			indice--;
			if (result1.get() == ButtonType.OK) {
				Optional<ButtonType> result2 = Alerts.showConfirmation("Jogo",
						"O ANIMAL QUE VOCÊ PENSOU É " + animal.getNome().toUpperCase() + " ?");

				if (result2.get() == ButtonType.OK) {
					Alerts.showAlert("FIM DO JOGO", null, "EU VENCI!!!!!", AlertType.INFORMATION);
					break;
				} else if (result2.get() == ButtonType.CANCEL && indice < 0) {
					
					if (habitat == Habitat.AQUATICO) {
						onBtNotActionAquatico();
					} else {
						onBtNotActionTerrestre();
					}
				} else {
					continue;
				}

			} else if (result1.get() == ButtonType.CANCEL && indice < 0) {
				
				if (habitat == Habitat.AQUATICO) {
					onBtNotActionAquatico();
				} else {
					onBtNotActionTerrestre();
				}
			} else {
				continue;
			}

		}
	}

	// caracteristica


	public void onBtNotActionAquatico() throws IOException {

		Main.changeScreen("inputAquatico");

	}

	public void onBtNotActionTerrestre() throws IOException {

		Main.changeScreen("inputTerrestre");

	}

	public void animalInputAquatico() throws IOException {

		gravar(Habitat.AQUATICO);

	}

	public void animalInputTerrestre() throws IOException {
		gravar(Habitat.TERRESTRE);
	}

	private void gravar(Habitat habitat) throws IOException {
		if(inputTextoAnimal == null || inputTextoAnimal.getText().trim().isEmpty()) {
			Alerts.showAlert("Erro", null, "O campo deve ser preenchido!", AlertType.ERROR);
			
		}else {
		AnimalDAO.gravarBD(new Animal(inputTextoAnimal.getText(), habitat));

		Main.changeScreen("input2");
		inputTextoAnimal.clear();
		}
	}

	public void diferencaInput() throws IOException {
		if(inputTextoDiferenca == null || inputTextoDiferenca.getText().trim().isEmpty()) {
			Alerts.showAlert("Erro", null, "O campo deve ser preenchido!", AlertType.ERROR);
			
		}else {
		String caracteristica = inputTextoDiferenca.getText();
		int indice = AnimalDAO.findAll().size() - 1;
		Animal animal = AnimalDAO.findAll().get(indice);
		AnimalDAO.editarUltimo(new Animal(animal.getNome(), animal.getHabitat(), caracteristica));
		inputTextoDiferenca.clear();
		Main.changeScreen("main");
		}
	}

	@FXML
	private void fecharTelaAction() throws IOException {
		AnimalDAO.limpaArquivo();
		Main.changeScreen("close");
	}

}
