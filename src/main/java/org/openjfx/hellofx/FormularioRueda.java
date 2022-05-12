package org.openjfx.hellofx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FormularioRueda extends GridPane {

	Label lblMarca = new Label("Marca:");
	Label lblModelo = new Label("Modelo:");
	Label lblDimensiones = new Label("Dimensiones:");
	Label lblPeso = new Label("Peso:");
	Label lblEstado = new Label("Estado:");
	Label lblComponente = new Label("Componente:");

	Button btnInsertar = new Button("Insertar");

	public FormularioRueda() {

	}

}
