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

	// Inicializamos un ArrayList de RuedaVO privado
	private ArrayList<RuedaVO> listaRuedas = new ArrayList<RuedaVO>();

	@Override
	public void start(Stage stage) {

		// Creamos un BorderPane
		BorderPane borderPane = new BorderPane();

		// Creamos un MenuBar
		MenuBar menuPrincipal = new MenuBar();

		// Creamos Menus con diferentes textos
		Menu MArchivo = new Menu("Archivo");
		Menu MOpciones = new Menu("Opciones");
		Menu MAyuda = new Menu("Ayuda");

		// Creamos MenuItems con diferentes textos
		MenuItem MAbrir = new MenuItem("Abrir");
		MenuItem MGuardar = new MenuItem("Guardar");
		MenuItem MCerrar = new MenuItem("Cerrar");
		MenuItem MGuardar2 = new MenuItem("Guardar");
		MenuItem MMostrar = new MenuItem("Mostrar");
		MenuItem MEliminar = new MenuItem("Eliminar");
		MenuItem MModificar = new MenuItem("Modificar");
		MenuItem MAcercaDe = new MenuItem("Acerca de");

		// Asignamos los MenuItems a su Menu correspondiente
		MArchivo.getItems().addAll(MAbrir, MGuardar, MCerrar);
		MOpciones.getItems().addAll(MGuardar2, MMostrar, MEliminar, MModificar);
		MAyuda.getItems().add(MAcercaDe);

		// Añadimos todos los Menus al MenuBar
		menuPrincipal.getMenus().addAll(MArchivo, MOpciones, MAyuda);

		// Creamos un TabPane
		TabPane panelPestanas = new TabPane();

		/* Código de la Primera Pestaña */

		// Creamos la Tab pestana1
		Tab pestana1 = new Tab("Insertar Datos");

		// Inicializamos un objeto FormularioRueda fRuedaPestana1 con el primer
		// constructor
		FormularioRueda fRuedaPestana1 = new FormularioRueda();

		// Añadimos un evento al btnInsertar de fRuedaPestana1
		fRuedaPestana1.btnInsertar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// Inicializamos un objeto RuedaVO rueda
				RuedaVO rueda = new RuedaVO();

				// Le asignamos a rueda los valores que están escritos/seleccionados en los
				// TextFields/ChoiceBoxs/ToggleGroup
				// de fRueda correspondientes a cada atributo
				rueda.setIdRueda(fRuedaPestana1.txtId.getText());
				rueda.setMarca(fRuedaPestana1.txtMarca.getText());
				rueda.setModelo(fRuedaPestana1.txtModelo.getText());
				rueda.setDimensiones(fRuedaPestana1.txtDimensiones.getText());
				rueda.setPeso((int) fRuedaPestana1.chPeso.getSelectionModel().getSelectedItem());
				rueda.setEstado((String) fRuedaPestana1.chEstado.getSelectionModel().getSelectedItem());

				RadioButton temp = (RadioButton) fRuedaPestana1.grpComponente.getSelectedToggle();

				rueda.setComponente(temp.getText());

				// Ejecutamos la función insertar() de RuedaDAO con rueda como parámetro
				RuedaDAO.insertar(rueda);
			}

		});

		// Asignamos fRueda como contenido de pestana1
		pestana1.setContent(fRuedaPestana1);

		/* Código de la Segunda Pestaña */

		// Creamos la Tab pestana2
		Tab pestana2 = new Tab("Modificar/Borrar");

		// Inicializamos un objeto FormularioRueda fRuedaPestana2 con el segundo
		// constructor
		FormularioRueda fRuedaPestana2 = new FormularioRueda("pestaña2");

		// Asignamos listaRuedas a la función cargarRuedas() de RuedaDAO
		listaRuedas = RuedaDAO.cargarRuedas();

		// Creamos un objeto rueda de la clase RuedaVO
		RuedaVO rueda = new RuedaVO();

		// Iterator de RuedaVO itr sobre la listaRuedas
		Iterator<RuedaVO> itr = listaRuedas.iterator();

		// rueda pasa a ser el siguiente elemento del itr
		rueda = itr.next();

		// Mientras itr tiene más elementos
		while (itr.hasNext()) {

			// Metemos en chSelectIdRueda el idRueda de rueda
			fRuedaPestana2.chSelectIdRueda.getItems().add(rueda.getIdRueda());

			// rueda pasa a ser el siguiente elemento del itr
			rueda = itr.next();
		}

		// Metemos en chSelectIdRueda el idRueda de rueda
		fRuedaPestana2.chSelectIdRueda.getItems().add(rueda.getIdRueda());

		// Añadimos un evento al chSelectIdRueda de fRuedaPestana2
		fRuedaPestana2.chSelectIdRueda.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// Inicializamos int posicion al número de la posición seleccionada en el
				// chSelectIdRueda de fRuedaPestana2
				int posicion = fRuedaPestana2.chSelectIdRueda.getSelectionModel().getSelectedIndex();

				// Creamos un objeto rueda de la clase RuedaVO que asignamos al objeto de
				// listaRuedas
				// con la posicion introducida
				RuedaVO rueda = listaRuedas.get(posicion);

				// Asignamos a los TextFields/ChoiceBoxs/ToggleGroup los atributos que rueda
				// contiene y que
				// les toca
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

		// Añadimos un evento al btnActualizar de fRuedaPestana2
		fRuedaPestana2.btnActualizar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// Creamos un objeto rueda de la clase RuedaVO
				RuedaVO rueda = new RuedaVO();

				// Asignamos a cada campo de rueda el contenido de los
				// TextFields/ChoiceBoxs/ToggleGroup que le toca
				rueda.setIdRueda(fRuedaPestana2.txtId.getText());
				rueda.setMarca(fRuedaPestana2.txtMarca.getText());
				rueda.setModelo(fRuedaPestana2.txtModelo.getText());
				rueda.setDimensiones(fRuedaPestana2.txtDimensiones.getText());
				rueda.setPeso((int) fRuedaPestana2.chPeso.getSelectionModel().getSelectedItem());
				rueda.setEstado((String) fRuedaPestana2.chEstado.getSelectionModel().getSelectedItem());

				RadioButton temp = (RadioButton) fRuedaPestana2.grpComponente.getSelectedToggle();

				rueda.setComponente(temp.getText());

				// Ejecutamos la función actualizar() de RuedaDAO con rueda como parámetro
				RuedaDAO.actualizar(rueda);
			}

		});

		// Añadimos un evento al btnEliminar de fRuedaPestana2
		fRuedaPestana2.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// Ejecutamos la función eliminar() de RuedaDAO con el texto del TextField txtId
				// como parámetro
				RuedaDAO.eliminar(fRuedaPestana2.txtId.getText());
			}

		});

		// Creamos un VBox
		VBox panelVBox = new VBox();

		// Añadimos al panelVBox el fRuedaPestana2
		panelVBox.getChildren().add(fRuedaPestana2);

		// Asignamos el panelVBox como contenido de pestana2
		pestana2.setContent(panelVBox);

		/* Código de la Tercera Pestaña */

		// Creamos el Tab pestana3
		Tab pestana3 = new Tab("Listado");

		// Creamos un GridPane y un ScrollPane
		GridPane panelPestana3 = new GridPane();
		ScrollPane panelScrollPestana3 = new ScrollPane();

		// Creamos un Button btnOrdenarAscDesc
		Button btnOrdenarAscDesc = new Button("Desc");

		// Añadimos el btnOrdenarAscDesc al panelPestana3
		panelPestana3.add(btnOrdenarAscDesc, 0, 0);

		// Con un for recorremos el ArrayList listaRuedas que contiene las ruedas
		// RuedaVO
		for (int i = 0; i < listaRuedas.size(); i++) {

			// Vamos creando Label con el contenido de cada rueda como texto
			Label labelRueda = new Label(
					i + 1 + ". " + listaRuedas.get(i).getIdRueda() + ", " + listaRuedas.get(i).getMarca() + ", "
							+ listaRuedas.get(i).getModelo() + ", " + listaRuedas.get(i).getDimensiones() + ", "
							+ listaRuedas.get(i).getPeso() + ", " + listaRuedas.get(i).getComponente() + ", "
							+ listaRuedas.get(i).getEstado() + ", " + listaRuedas.get(i).getFecha() + ", "
							+ listaRuedas.get(i).getSesion() + ", " + listaRuedas.get(i).getIdCoche());

			// Vamos añadiendo los labelRueda al panelPestana3
			panelPestana3.add(labelRueda, 0, i + 1);
		}

		// Añadimos un evento al btnOrdenarAscDesc
		btnOrdenarAscDesc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				// Eliminamos todas las filas de panelPestana3 menos la primera que contiene el
				// botón
				panelPestana3.getChildren().remove(1, listaRuedas.size() + 1);

				// Switch con el texto de btnOrdenarAscDesc como referencia
				switch (btnOrdenarAscDesc.getText()) {
				// En el caso de Desc
				case "Desc":

					// El texto de btnOrdenarAscDesc pasa a ser Asc
					btnOrdenarAscDesc.setText("Asc");

					// Recorremos la listaRuedas del final al principio
					for (int i = listaRuedas.size() - 1; i >= 0; i--) {

						// Vamos creando Label con el contenido de cada rueda como texto
						Label labelRueda = new Label(listaRuedas.size() - i + ". " + listaRuedas.get(i).getIdRueda()
								+ ", " + listaRuedas.get(i).getMarca() + ", " + listaRuedas.get(i).getModelo() + ", "
								+ listaRuedas.get(i).getDimensiones() + ", " + listaRuedas.get(i).getPeso() + ", "
								+ listaRuedas.get(i).getComponente() + ", " + listaRuedas.get(i).getEstado() + ", "
								+ listaRuedas.get(i).getFecha() + ", " + listaRuedas.get(i).getSesion() + ", "
								+ listaRuedas.get(i).getIdCoche());

						// Vamos añadiendo los labelRueda al panelPestana3
						panelPestana3.add(labelRueda, 0, listaRuedas.size() - i);
					}
					break;

				// En el caso de Asc
				case "Asc":

					// El texto de btnOrdenarAscDesc pasa a ser Desc
					btnOrdenarAscDesc.setText("Desc");

					// Recorremos la listaRuedas del principio al final
					for (int i = 0; i < listaRuedas.size(); i++) {

						// Vamos creando Label con el contenido de cada rueda como texto
						Label labelRueda = new Label(i + 1 + ". " + listaRuedas.get(i).getIdRueda() + ", "
								+ listaRuedas.get(i).getMarca() + ", " + listaRuedas.get(i).getModelo() + ", "
								+ listaRuedas.get(i).getDimensiones() + ", " + listaRuedas.get(i).getPeso() + ", "
								+ listaRuedas.get(i).getComponente() + ", " + listaRuedas.get(i).getEstado() + ", "
								+ listaRuedas.get(i).getFecha() + ", " + listaRuedas.get(i).getSesion() + ", "
								+ listaRuedas.get(i).getIdCoche());

						// Vamos añadiendo los labelRueda al panelPestana3
						panelPestana3.add(labelRueda, 0, i + 1);
					}
					break;
				}

			}

		});

		// Determinamos el Padding y el espacio vertical entre elementos del GridPane
		panelPestana3.setPadding(new Insets(20, 20, 20, 20));
		panelPestana3.setVgap(10);

		// Asignamos como contenido de panelScrollPestana3 el panelPestana3
		panelScrollPestana3.setContent(panelPestana3);

		// Asignamos como contenido de pestana3 panelScrollPestana3
		pestana3.setContent(panelScrollPestana3);

		// Hacemos que las pestañas no sean cerrables
		pestana1.setClosable(false);
		pestana2.setClosable(false);
		pestana3.setClosable(false);

		// Asignamos las pestañas al panelPestanas
		panelPestanas.getTabs().addAll(pestana1, pestana2, pestana3);

		// Asignamos el menuPrincipal al top del borderPane y el panelPestanas al center
		// del borderPane
		borderPane.setTop(menuPrincipal);
		borderPane.setCenter(panelPestanas);

		// La scene contiene los panel
		var scene = new Scene(borderPane, 450, 400);

		// El stage es el contenido global de la ventana
		stage.setScene(scene);

		// Mostramos la aplicacion visual
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
