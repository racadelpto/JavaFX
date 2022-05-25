package org.openjfx.hellofx;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class FormularioRueda extends GridPane {

	// Declaramos los Label para indicar el atributo
	Label lblId = new Label("Id:");
	Label lblMarca = new Label("Marca:");
	Label lblModelo = new Label("Modelo:");
	Label lblDimensiones = new Label("Dimensiones:");
	Label lblPeso = new Label("Peso (kg):");
	Label lblEstado = new Label("Estado:");
	Label lblComponente = new Label("Componente:");

	// Declaramos los TextField para introducir datos
	TextField txtId = new TextField();
	TextField txtMarca = new TextField();
	TextField txtModelo = new TextField();
	TextField txtDimensiones = new TextField();

	// Declaramos los ChoiceBox para elegir datos
	ChoiceBox chPeso = new ChoiceBox();
	ChoiceBox chEstado = new ChoiceBox();

	// Declaramos el ToggleGroup y los RadioButton que añadiremos a él para poder
	// seleccionar solo uno
	ToggleGroup grpComponente = new ToggleGroup();

	RadioButton radDuro = new RadioButton("Duro");
	RadioButton radMedio = new RadioButton("Medio");
	RadioButton radBlando = new RadioButton("Blando");

	// Declaramos los Button para cada formulario (se añaden en la app principal)
	Button btnInsertar = new Button("Insertar");
	Button btnActualizar = new Button("Actualizar");
	Button btnEliminar = new Button("Eliminar");

	// Declaramos el Label y ChoiceBox para el formulario (se añaden en la app principal)
	Label lblSelectIdRueda = new Label("Seleccionar Id Rueda:");
	ChoiceBox chSelectIdRueda = new ChoiceBox();

	//  Constructor para el formulario estándar
	// A los objetos creados se le añadirán los labels, choicebox o buttons que correspondan
	// en función de si son el de insertar o actualizar/eliminar
	public FormularioRueda() {

		// Añadimos cada RadioButton al ToggleGroup grpComponente
		radDuro.setToggleGroup(grpComponente);
		radMedio.setToggleGroup(grpComponente);
		radBlando.setToggleGroup(grpComponente);

		// Introducimos datos en el chPeso, números de 0 al 100
		for (int i = 0; i < 101; i++) {
			chPeso.getItems().add(i);
		}

		// Introducimos diversos estados al chEstado
		chEstado.getItems().add("Nuevo");
		chEstado.getItems().add("Usado");
		chEstado.getItems().add("Averiado");
		chEstado.getItems().add("Óptimo");

		// Añadimos cada elemento del formulario al GridPane según su posición
		this.add(lblId, 0, 0);
		this.add(txtId, 1, 0, 4, 1);

		this.add(lblMarca, 0, 1);
		this.add(txtMarca, 1, 1, 4, 1);

		this.add(lblModelo, 0, 2);
		this.add(txtModelo, 1, 2, 4, 1);

		this.add(lblDimensiones, 0, 3);
		this.add(txtDimensiones, 1, 3, 4, 1);

		this.add(lblPeso, 0, 4);
		this.add(chPeso, 1, 4, 4, 1);

		this.add(lblEstado, 0, 5);
		this.add(chEstado, 1, 5, 4, 1);

		this.add(lblComponente, 0, 6);
		this.add(radBlando, 1, 6);
		this.add(radMedio, 2, 6);
		this.add(radDuro, 3, 6);

		// Determinamos el padding del GridPane y la distancia entre elementos
		this.setPadding(new Insets(10, 20, 20, 20));
		this.setVgap(10);
		this.setHgap(10);
	}
}
