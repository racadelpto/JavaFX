package org.openjfx.hellofx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AplicacionPrincipal extends Application {

	private ArrayList<RuedaVO> listaRuedas = new ArrayList<RuedaVO>();

	@Override
	public void start(Stage stage) {

		BorderPane borderPane = new BorderPane();

		MenuBar menuPrincipal = new MenuBar();

		Menu MArchivo = new Menu("Archivo");
		Menu MOpciones = new Menu("Opciones");
		Menu MAyuda = new Menu("Ayuda");

		MenuItem MAbrir = new MenuItem("Abrir");
		MenuItem MGuardar = new MenuItem("Guardar");
		MenuItem MCerrar = new MenuItem("Cerrar");
		MenuItem MGuardar2 = new MenuItem("Guardar");
		MenuItem MMostrar = new MenuItem("Mostrar");
		MenuItem MEliminar = new MenuItem("Eliminar");
		MenuItem MModificar = new MenuItem("Modificar");
		MenuItem MAcercaDe = new MenuItem("Acerca de");

		MArchivo.getItems().addAll(MAbrir, MGuardar, MCerrar);
		MOpciones.getItems().addAll(MGuardar2, MMostrar, MEliminar, MModificar);
		MAyuda.getItems().add(MAcercaDe);

		menuPrincipal.getMenus().addAll(MArchivo, MOpciones, MAyuda);

		TabPane panelPestanas = new TabPane();

		Tab pestana1 = new Tab("Insertar Datos");

		FormularioRueda fRueda = new FormularioRueda();

		fRueda.btnInsertar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				RuedaVO rueda = new RuedaVO();

				rueda.setIdRueda(fRueda.txtId.getText());
				rueda.setMarca(fRueda.txtMarca.getText());
				rueda.setModelo(fRueda.txtModelo.getText());
				rueda.setDimensiones(fRueda.txtDimensiones.getText());
				rueda.setPeso((int) fRueda.chPeso.getSelectionModel().getSelectedItem());
				rueda.setEstado((String) fRueda.chEstado.getSelectionModel().getSelectedItem());

				RadioButton temp = (RadioButton) fRueda.grpComponente.getSelectedToggle();

				rueda.setComponente(temp.getText());

				RuedaDAO.insertar(rueda);
			}

		});

		pestana1.setContent(fRueda);

		Tab pestana2 = new Tab("Modificar/Borrar");

		FormularioRueda fRuedaPestana2 = new FormularioRueda("pestaña2");

		listaRuedas = RuedaDAO.cargarRuedas();

		RuedaVO rueda = new RuedaVO();

		Iterator<RuedaVO> itr = listaRuedas.iterator();

		rueda = itr.next();
		
		while (itr.hasNext()) {
			fRuedaPestana2.chSelectIdRueda.getItems().add(rueda.getIdRueda());

			rueda = itr.next();
		}

		fRuedaPestana2.chSelectIdRueda.getItems().add(rueda.getIdRueda());
		
		fRuedaPestana2.chSelectIdRueda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				int posicion = fRuedaPestana2.chSelectIdRueda.getSelectionModel().getSelectedIndex();

				RuedaVO rueda = listaRuedas.get(posicion);

				fRuedaPestana2.txtId.setText(rueda.getIdRueda());
				fRuedaPestana2.txtMarca.setText(rueda.getMarca());
				fRuedaPestana2.txtModelo.setText(rueda.getModelo());
				fRuedaPestana2.txtDimensiones.setText(rueda.getDimensiones());
				fRuedaPestana2.chPeso.getSelectionModel().select(rueda.getPeso());
				fRuedaPestana2.chEstado.getSelectionModel().select(rueda.getEstado());

				switch (rueda.getComponente()) {
				case "Duro":
					fRuedaPestana2.radDuro.setSelected(true);
					break;
				case "Medio":
					fRuedaPestana2.radMedio.setSelected(true);
					break;
				case "Blando":
					fRuedaPestana2.radBlando.setSelected(true);
					break;
				}

			}

		});

		fRuedaPestana2.btnActualizar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				RuedaVO rueda = new RuedaVO();

				rueda.setIdRueda(fRuedaPestana2.txtId.getText());
				rueda.setMarca(fRuedaPestana2.txtMarca.getText());
				rueda.setModelo(fRuedaPestana2.txtModelo.getText());
				rueda.setDimensiones(fRuedaPestana2.txtDimensiones.getText());
				rueda.setPeso((int) fRuedaPestana2.chPeso.getSelectionModel().getSelectedItem());
				rueda.setEstado((String) fRuedaPestana2.chEstado.getSelectionModel().getSelectedItem());

				RadioButton temp = (RadioButton) fRuedaPestana2.grpComponente.getSelectedToggle();

				rueda.setComponente(temp.getText());

				RuedaDAO.actualizar(rueda);
			}

		});

		fRuedaPestana2.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				RuedaDAO.eliminar(fRuedaPestana2.txtId.getText());
			}

		});

		VBox panelVBox = new VBox();

		panelVBox.getChildren().add(fRuedaPestana2);

		pestana2.setContent(panelVBox);

		Tab pestana3 = new Tab("Listado");

		GridPane panelPestana3 = new GridPane();
		ScrollPane panelScrollPestana3 = new ScrollPane();

		Button btnOrdenarAscDesc = new Button("Desc");

		panelPestana3.add(btnOrdenarAscDesc, 0, 0);

		for (int i = 0; i < listaRuedas.size(); i++) {
			Label labelRueda = new Label(i+1 + ". " + listaRuedas.get(i).getIdRueda() + ", " + listaRuedas.get(i).getMarca() + ", "
					+ listaRuedas.get(i).getModelo() + ", " + listaRuedas.get(i).getDimensiones() + ", "
					+ listaRuedas.get(i).getPeso() + ", " + listaRuedas.get(i).getComponente() + ", "
					+ listaRuedas.get(i).getEstado() + ", " + listaRuedas.get(i).getFecha() + ", "
					+ listaRuedas.get(i).getSesion() + ", " + listaRuedas.get(i).getIdCoche());
			panelPestana3.add(labelRueda, 0, i + 1);
		}

		btnOrdenarAscDesc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				panelPestana3.getChildren().remove(1, listaRuedas.size() + 1);

				switch (btnOrdenarAscDesc.getText()) {
				case "Desc":
					btnOrdenarAscDesc.setText("Asc");

					for (int i = listaRuedas.size() - 1; i >= 0; i--) {
						Label labelRueda = new Label(listaRuedas.size() - i + ". " + listaRuedas.get(i).getIdRueda() + ", "
								+ listaRuedas.get(i).getMarca() + ", " + listaRuedas.get(i).getModelo() + ", "
								+ listaRuedas.get(i).getDimensiones() + ", " + listaRuedas.get(i).getPeso() + ", "
								+ listaRuedas.get(i).getComponente() + ", " + listaRuedas.get(i).getEstado() + ", "
								+ listaRuedas.get(i).getFecha() + ", " + listaRuedas.get(i).getSesion() + ", "
								+ listaRuedas.get(i).getIdCoche());

						panelPestana3.add(labelRueda, 0, listaRuedas.size() - i);
					}

					break;

				case "Asc":
					btnOrdenarAscDesc.setText("Desc");

					for (int i = 0; i < listaRuedas.size(); i++) {
						Label labelRueda = new Label(i+1 + ". " + listaRuedas.get(i).getIdRueda() + ", "
								+ listaRuedas.get(i).getMarca() + ", " + listaRuedas.get(i).getModelo() + ", "
								+ listaRuedas.get(i).getDimensiones() + ", " + listaRuedas.get(i).getPeso() + ", "
								+ listaRuedas.get(i).getComponente() + ", " + listaRuedas.get(i).getEstado() + ", "
								+ listaRuedas.get(i).getFecha() + ", " + listaRuedas.get(i).getSesion() + ", "
								+ listaRuedas.get(i).getIdCoche());

						panelPestana3.add(labelRueda, 0, i + 1);
					}

					break;
				}

			}

		});

		panelPestana3.setPadding(new Insets(20, 20, 20, 20));
		panelPestana3.setVgap(10);

		panelScrollPestana3.setContent(panelPestana3);

		pestana3.setContent(panelScrollPestana3);

		pestana1.setClosable(false);
		pestana2.setClosable(false);
		pestana3.setClosable(false);

		panelPestanas.getTabs().addAll(pestana1, pestana2, pestana3);

		borderPane.setTop(menuPrincipal);
		borderPane.setCenter(panelPestanas);

		var scene = new Scene(borderPane, 400, 300);

		stage.setScene(scene);

		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
