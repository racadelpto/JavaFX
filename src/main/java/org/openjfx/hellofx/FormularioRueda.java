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

	Label lblId = new Label("Id:");
	Label lblMarca = new Label("Marca:");
	Label lblModelo = new Label("Modelo:");
	Label lblDimensiones = new Label("Dimensiones:");
	Label lblPeso = new Label("Peso (kg):");
	Label lblEstado = new Label("Estado:");
	Label lblComponente = new Label("Componente:");

	TextField txtId = new TextField();
	TextField txtMarca = new TextField();
	TextField txtModelo = new TextField();
	TextField txtDimensiones = new TextField();

	ChoiceBox chPeso = new ChoiceBox();
	ChoiceBox chEstado = new ChoiceBox();

	ToggleGroup grpComponente = new ToggleGroup();

	RadioButton radDuro = new RadioButton("Duro");
	RadioButton radMedio = new RadioButton("Medio");
	RadioButton radBlando = new RadioButton("Blando");

	Button btnInsertar = new Button("Insertar");
	Button btnActualizar = new Button("Actualizar");
	Button btnEliminar = new Button("Eliminar");

	Label lblSelectIdRueda = new Label("Seleccionar Id Rueda:");
	ChoiceBox chSelectIdRueda = new ChoiceBox();

	public FormularioRueda() {

		radDuro.setToggleGroup(grpComponente);
		radMedio.setToggleGroup(grpComponente);
		radBlando.setToggleGroup(grpComponente);

		for (int i = 0; i < 101; i++) {
			chPeso.getItems().add(i);
		}

		chEstado.getItems().add("Nuevo");
		chEstado.getItems().add("Usado");
		chEstado.getItems().add("Averiado");
		chEstado.getItems().add("Óptimo");

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

		this.add(btnInsertar, 0, 7, 4, 1);

		this.setPadding(new Insets(20, 20, 20, 20));

		this.setVgap(10);
		this.setHgap(10);

	}

	public FormularioRueda(String campo) {

		radDuro.setToggleGroup(grpComponente);
		radMedio.setToggleGroup(grpComponente);
		radBlando.setToggleGroup(grpComponente);

		for (int i = 0; i < 101; i++) {
			chPeso.getItems().add(i);
		}

		chEstado.getItems().add("Nuevo");
		chEstado.getItems().add("Usado");
		chEstado.getItems().add("Averiado");
		chEstado.getItems().add("Óptimo");

		this.add(lblSelectIdRueda, 0, 0);
		this.add(chSelectIdRueda, 1, 0, 4, 1);

		this.add(lblId, 0, 1);
		this.add(txtId, 1, 1, 4, 1);

		this.add(lblMarca, 0, 2);
		this.add(txtMarca, 1, 2, 4, 1);

		this.add(lblModelo, 0, 3);
		this.add(txtModelo, 1, 3, 4, 1);

		this.add(lblDimensiones, 0, 4);
		this.add(txtDimensiones, 1, 4, 4, 1);

		this.add(lblPeso, 0, 5);
		this.add(chPeso, 1, 5, 4, 1);

		this.add(lblEstado, 0, 6);
		this.add(chEstado, 1, 6, 4, 1);

		this.add(lblComponente, 0, 7);
		this.add(radBlando, 1, 7);
		this.add(radMedio, 2, 7);
		this.add(radDuro, 3, 7);

		this.add(btnActualizar, 0, 8, 2, 1);
		this.add(btnEliminar, 1, 8, 2, 1);

		this.setPadding(new Insets(20, 20, 20, 20));

		this.setVgap(10);
		this.setHgap(10);

	}

}
