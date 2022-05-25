package org.openjfx.hellofx;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class FormularioGatosPerros extends GridPane {

	// Declaramos los Labels tanto para perros como gatos
	Label lblNombre = new Label("Nombre:");
	Label lblPeso = new Label("Peso (kg):");
	Label lblEdad = new Label("Edad:");
	Label lblSexo = new Label("Sexo:");
	Label lblAdjetivo = new Label("Adjetivo:");
	Label lblNacionalidad = new Label("Nacionalidad:");
	Label lblColor = new Label("Color:");
	Label lblRaza = new Label("Raza:");

	// Declaramos los TextField, ChoiceBox, ToggleGroup, RadioButton para esos
	// Labels
	TextField txtNombre = new TextField();
	ChoiceBox chPeso = new ChoiceBox();
	TextField txtEdad = new TextField();
	ToggleGroup grpSexo = new ToggleGroup();
	TextField txtAdjetivo = new TextField();
	TextField txtNacionalidad = new TextField();
	TextField txtColor = new TextField();
	TextField txtRaza = new TextField();

	RadioButton radMasculino = new RadioButton("Masculino");
	RadioButton radFemenino = new RadioButton("Femenino");
	RadioButton radDesconocido = new RadioButton("Desconocido");

	// Declaramos los Button
	Button btnGuardar = new Button("Guardar");
	Button btnBorrar = new Button("Borrar");

	// Constructor para el formulario de gatos y perros
	public FormularioGatosPerros() {

		// Añadimos cada RadioButton al ToggleGroup grpSexo
		radMasculino.setToggleGroup(grpSexo);
		radFemenino.setToggleGroup(grpSexo);
		radDesconocido.setToggleGroup(grpSexo);

		// Introducimos datos en el chPeso, números de 1 al 50
		for (int i = 1; i < 51; i++) {
			chPeso.getItems().add(i);
		}

		// Añadimos cada elemento del formulario al GridPane según su posición
		this.add(lblNombre, 0, 0);
		this.add(txtNombre, 1, 0, 4, 1);

		this.add(lblPeso, 0, 1);
		this.add(chPeso, 1, 1, 4, 1);

		this.add(lblEdad, 0, 2);
		this.add(txtEdad, 1, 2, 4, 1);

		this.add(lblSexo, 0, 3);
		this.add(radMasculino, 1, 3);
		this.add(radFemenino, 2, 3);
		this.add(radDesconocido, 3, 3);

		this.add(lblAdjetivo, 0, 4);
		this.add(txtAdjetivo, 1, 4, 4, 1);

		this.add(lblNacionalidad, 0, 5);
		this.add(txtNacionalidad, 1, 5, 4, 1);

		this.add(lblColor, 0, 6);
		this.add(txtColor, 1, 6, 4, 1);

		this.add(lblRaza, 0, 7, 4, 1);
		this.add(txtRaza, 1, 7, 4, 1);

		this.add(btnGuardar, 0, 8, 2, 1);
		this.add(btnBorrar, 1, 8, 2, 1);

		// Determinamos el padding del GridPane y la distancia entre sus elementos
		this.setPadding(new Insets(20, 20, 20, 20));
		this.setVgap(10);
		this.setHgap(10);
	}
}
