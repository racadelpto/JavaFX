package org.openjfx.hellofx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ExamenTema6 extends Application {

	@Override
	public void start(Stage stage) {

		// Creamos un BorderPane
		BorderPane borderPane = new BorderPane();

		// Creamos un MenuBar
		MenuBar menuPrincipal = new MenuBar();

		// Creamos Menus con diferentes textos
		Menu MGatos = new Menu("Gatos");
		Menu MPerros = new Menu("Perros");
		Menu MAyuda = new Menu("Ayuda");

		// Creamos MenuItems con diferentes textos
		MenuItem MGCargar = new MenuItem("Cargar");
		MenuItem MGGuardar = new MenuItem("Guardar");
		MenuItem MGSalir = new MenuItem("Salir");
		MenuItem MPCargar = new MenuItem("Cargar");
		MenuItem MPGuardar = new MenuItem("Guardar");
		MenuItem MAcercaDe = new MenuItem("Acerca de");

		// Asignamos los MenuItems a su Menu correspondiente
		MGatos.getItems().addAll(MGCargar, MGGuardar, MGSalir);
		MPerros.getItems().addAll(MPCargar, MPGuardar);
		MAyuda.getItems().add(MAcercaDe);

		// Añadimos todos los Menus al menuPrincipal
		menuPrincipal.getMenus().addAll(MGatos, MPerros, MAyuda);

		// Creamos un TabPane
		TabPane panelPestanas = new TabPane();

		/* Código de la Primera Pestaña */

		// Creamos la Tab pestana1
		Tab pestana1 = new Tab("Gatos");

		// Creamos un objeto de la clase FormularioGatosPerros fGatos
		FormularioGatosPerros fGatos = new FormularioGatosPerros();

		// Añadimos un evento al MGSalir
		MGSalir.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// Cerramos el stage
				stage.close();
			}
		});

		// Añadimos un evento al MGCargar
		MGCargar.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// Ponemos la pestaña correspondiente activa
				panelPestanas.getSelectionModel().select(0);

				// Rellenamos los datos de fGatos
				fGatos.txtNombre.setText("Pepe");
				fGatos.chPeso.getSelectionModel().select(13);
				fGatos.txtEdad.setText("6");
				fGatos.radFemenino.setSelected(true);
				fGatos.txtAdjetivo.setText("Feliz");
				fGatos.txtNacionalidad.setText("Francés");
				fGatos.txtColor.setText("Negro");
				fGatos.txtRaza.setText("Maine Coon");
			}
		});

		// Añadimos un evento al MGGuardar
		MGGuardar.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// Ponemos la pestaña correspondiente activa
				panelPestanas.getSelectionModel().select(0);
			}
		});

		// Añadimos un evento al btnGuardar de fGatos
		fGatos.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// Creamos un nuevo stage
				Stage stageNuevaVentana = new Stage();

				// Creamos un GridPane
				GridPane gridPane = new GridPane();

				// Creamos Labels
				// En cada uno vendra el Label correspondiente de fGatos con sus datos
				// Y los vamos añadiendo al gridPane
				Label nombre = new Label(fGatos.lblNombre.getText() + " " + fGatos.txtNombre.getText());
				gridPane.add(nombre, 0, 0);

				Label peso = new Label(
						fGatos.lblPeso.getText() + " " + fGatos.chPeso.getSelectionModel().getSelectedIndex());
				gridPane.add(peso, 0, 1);

				Label edad = new Label(fGatos.lblEdad.getText() + " " + fGatos.txtEdad.getText());
				gridPane.add(edad, 0, 2);

				RadioButton temp = (RadioButton) fGatos.grpSexo.getSelectedToggle();
				Label sexo = new Label(fGatos.lblSexo.getText() + " " + temp.getText());
				gridPane.add(sexo, 0, 3);

				Label adjetivo = new Label(fGatos.lblAdjetivo.getText() + " " + fGatos.txtAdjetivo.getText());
				gridPane.add(adjetivo, 0, 4);

				Label nacionalidad = new Label(
						fGatos.lblNacionalidad.getText() + " " + fGatos.txtNacionalidad.getText());
				gridPane.add(nacionalidad, 0, 5);

				Label color = new Label(fGatos.lblColor.getText() + " " + fGatos.txtColor.getText());
				gridPane.add(color, 0, 6);

				Label raza = new Label(fGatos.lblRaza.getText() + " " + fGatos.txtRaza.getText());
				gridPane.add(raza, 0, 7);

				// Determinamos el padding del GridPane y la distancia entre sus elementos
				gridPane.setPadding(new Insets(20, 20, 20, 20));
				gridPane.setVgap(10);
				gridPane.setHgap(10);

				// La sceneNueva contiene el gridPane
				var sceneNueva = new Scene(gridPane, 400, 350);

				// El stageNuevaVentana es el contenido global de la ventana
				stageNuevaVentana.setScene(sceneNueva);

				// Mostramos la aplicacion visual
				stageNuevaVentana.show();
			}
		});

		// Asignamos como contenido de pestana1 fGatos
		pestana1.setContent(fGatos);

		/* Código de la Segunda Pestaña */

		// Creamos la Tab pestana2
		Tab pestana2 = new Tab("Perros");

		// Creamos un objeto de la clase FormularioGatosPerros fPerros
		FormularioGatosPerros fPerros = new FormularioGatosPerros();

		// Añadimos un evento al MPCargar
		MPCargar.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// Ponemos activa la pestaña correspondiente
				panelPestanas.getSelectionModel().select(1);

				// Rellenamos los datos del fPerros
				fPerros.txtNombre.setText("Lulu");
				fPerros.chPeso.getSelectionModel().select(20);
				fPerros.txtEdad.setText("10");
				fPerros.radMasculino.setSelected(true);
				fPerros.txtAdjetivo.setText("Tranquilo");
				fPerros.txtNacionalidad.setText("Español");
				fPerros.txtColor.setText("Marrón");
				fPerros.txtRaza.setText("Siamés");
			}
		});

		// Añadimos un evento al MPGuardar
		MPGuardar.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// Ponemos activa la pestaña correspondiente
				panelPestanas.getSelectionModel().select(1);
			}
		});

		// Añadimos un evento al btnGuardar de fPerros
		fPerros.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {

				// Creamos un nuevo stage
				Stage stageNuevaVentana = new Stage();

				// Creamos un GridPane
				GridPane gridPane = new GridPane();

				// Creamos Labels
				// En cada uno vendra el Label correspondiente de fPerros con sus datos
				// Y los vamos añadiendo al gridPane
				Label nombre = new Label(fPerros.lblNombre.getText() + " " + fPerros.txtNombre.getText());
				gridPane.add(nombre, 0, 0);

				Label peso = new Label(
						fPerros.lblPeso.getText() + " " + fPerros.chPeso.getSelectionModel().getSelectedIndex());
				gridPane.add(peso, 0, 1);

				Label edad = new Label(fPerros.lblEdad.getText() + " " + fPerros.txtEdad.getText());
				gridPane.add(edad, 0, 2);

				RadioButton temp = (RadioButton) fPerros.grpSexo.getSelectedToggle();
				Label sexo = new Label(fPerros.lblSexo.getText() + " " + temp.getText());
				gridPane.add(sexo, 0, 3);

				Label adjetivo = new Label(fPerros.lblAdjetivo.getText() + " " + fPerros.txtAdjetivo.getText());
				gridPane.add(adjetivo, 0, 4);

				Label nacionalidad = new Label(
						fPerros.lblNacionalidad.getText() + " " + fPerros.txtNacionalidad.getText());
				gridPane.add(nacionalidad, 0, 5);

				Label color = new Label(fPerros.lblColor.getText() + " " + fPerros.txtColor.getText());
				gridPane.add(color, 0, 6);

				Label raza = new Label(fPerros.lblRaza.getText() + " " + fPerros.txtRaza.getText());
				gridPane.add(raza, 0, 7);

				// Determinamos el padding del GridPane y la distancia entre sus elementos
				gridPane.setPadding(new Insets(20, 20, 20, 20));
				gridPane.setVgap(10);
				gridPane.setHgap(10);

				// La sceneNueva contiene el gridPane
				var sceneNueva = new Scene(gridPane, 400, 350);

				// El stageNuevaVentana es el contenido global de la ventana
				stageNuevaVentana.setScene(sceneNueva);

				// Mostramos la aplicacion visual
				stageNuevaVentana.show();
			}
		});

		// Asignamos como contenido de pestana2 fPerros
		pestana2.setContent(fPerros);

		// Esto no se pide pero para no cerrar las pestañas sin querer lo pongo
		pestana1.setClosable(false);
		pestana2.setClosable(false);

		// Añadimos las pestañas a panelPestanas
		panelPestanas.getTabs().addAll(pestana1, pestana2);

		// Asignamos el menuPrincipal al top del borderPane y el panelPestanas al center
		// del borderPane
		borderPane.setTop(menuPrincipal);
		borderPane.setCenter(panelPestanas);

		// La scene contiene el borderPane
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
