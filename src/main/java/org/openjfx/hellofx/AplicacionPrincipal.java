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
		
		fRueda.btnInsertar.setOnAction(new EventHandler<ActionEvent>(){

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
				
				String query = "INSERT into rueda values(?,?,?,?,?,?,?,?,?,?)";
				
				Connection con = ConexionBD.conectarBD();
				
				try {
					PreparedStatement pStmt = con.prepareStatement(query);
					
					pStmt.setString(1, fRueda.txtId.getText());
					pStmt.setString(2, fRueda.txtMarca.getText());
					pStmt.setString(3, fRueda.txtModelo.getText());
					pStmt.setString(4, fRueda.txtDimensiones.getText());
					pStmt.setInt(5, (int) fRueda.chPeso.getSelectionModel().getSelectedItem());
					pStmt.setString(6, (String) fRueda.chEstado.getSelectionModel().getSelectedItem());
					pStmt.setString(7, temp.getText());
					pStmt.setString(8, null);
					pStmt.setString(9, null);
					pStmt.setString(10, "AlfC4");
					
					pStmt.executeUpdate();
					
					pStmt.close();
					con.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		pestana1.setContent(fRueda);
		
		
		Tab pestana2 = new Tab("Modificar/Borrar");
		
		Label lblSelectIdRueda = new Label("Seleccionar Id Rueda:");
		ChoiceBox chSelectIdRueda = new ChoiceBox();
		FormularioRueda fRuedaPestana2 = new FormularioRueda("pestaña2");
		
		Connection con = ConexionBD.conectarBD();
		
		String query = "Select * from rueda";
		
		try {
			PreparedStatement pStmt = con.prepareStatement(query);
			
			ResultSet res = pStmt.executeQuery();
			
			res.next();
			
			while(res.next()) {
				RuedaVO rueda = new RuedaVO();
				
				rueda.setIdRueda(res.getString("idRueda"));
				rueda.setMarca(res.getString("Marca"));
				rueda.setModelo(res.getString("Modelo"));
				rueda.setDimensiones(res.getString("Dimensiones"));
				rueda.setPeso(res.getInt("Peso"));
				rueda.setComponente(res.getString("Componente"));
				rueda.setEstado(res.getString("Estado"));
				rueda.setFecha(res.getString("Fecha"));
				rueda.setSesion(res.getString("Sesión"));
				rueda.setIdCoche(res.getString("Coche_idCoche"));
				
				listaRuedas.add(rueda);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RuedaVO rueda = new RuedaVO();
		
		Iterator<RuedaVO> itr = listaRuedas.iterator();
		
		rueda = itr.next();
		
		while(itr.hasNext()) {
			chSelectIdRueda.getItems().add(rueda.getIdRueda());
			
			rueda = itr.next();
		}
		
		chSelectIdRueda.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				
				int posicion = chSelectIdRueda.getSelectionModel().getSelectedIndex();
				
				RuedaVO rueda = listaRuedas.get(posicion);
				
				fRuedaPestana2.txtId.setText(rueda.getIdRueda());
				fRuedaPestana2.txtMarca.setText(rueda.getMarca());
				fRuedaPestana2.txtModelo.setText(rueda.getModelo());
				fRuedaPestana2.txtDimensiones.setText(rueda.getDimensiones());
				fRuedaPestana2.chPeso.getSelectionModel().select(rueda.getPeso());
				fRuedaPestana2.chEstado.getSelectionModel().select(rueda.getEstado());
				
				switch(rueda.getComponente()) {
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
		
		fRuedaPestana2.btnActualizar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				
				
			}
			
		});
		
		fRuedaPestana2.btnEliminar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {

				
				
			}
			
		});
		
		VBox panelVBox = new VBox();
		
		panelVBox.getChildren().add(lblSelectIdRueda);
		panelVBox.getChildren().add(chSelectIdRueda);
		panelVBox.getChildren().add(fRuedaPestana2);
		
		pestana2.setContent(panelVBox);
		
		
		Tab pestana3 = new Tab("Listado");
		
		GridPane panelPestana3 = new GridPane();
		ScrollPane panelScrollPestana3 = new ScrollPane();
		
		Button btnOrdenarAscDesc = new Button("Desc");
		
		panelPestana3.add(btnOrdenarAscDesc, 0, 0);
		
		for(int i= 0; i<listaRuedas.size(); i++) {
			Label labelRueda = new Label(listaRuedas.get(i).getIdRueda()+", "+listaRuedas.get(i).getMarca()+", "+listaRuedas.get(i).getModelo()+", "+listaRuedas.get(i).getDimensiones()+
					", "+listaRuedas.get(i).getPeso()+", "+listaRuedas.get(i).getComponente()+", "+listaRuedas.get(i).getEstado()+", "+listaRuedas.get(i).getFecha()+", "+listaRuedas.get(i).getSesion()+
					", "+listaRuedas.get(i).getIdCoche());
			panelPestana3.add(labelRueda, 0, i+1);
		}
		
		btnOrdenarAscDesc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				panelPestana3.getChildren().remove(1, listaRuedas.size()+1);
				
				switch(btnOrdenarAscDesc.getText()){
				case "Desc":
					btnOrdenarAscDesc.setText("Asc");
					
					for(int i= listaRuedas.size()-1; i>=0; i--) {
						Label labelRueda = new Label(listaRuedas.get(i).getIdRueda()+", "+listaRuedas.get(i).getMarca()+", "+listaRuedas.get(i).getModelo()+", "+listaRuedas.get(i).getDimensiones()+
								", "+listaRuedas.get(i).getPeso()+", "+listaRuedas.get(i).getComponente()+", "+listaRuedas.get(i).getEstado()+", "+listaRuedas.get(i).getFecha()+", "+listaRuedas.get(i).getSesion()+
								", "+listaRuedas.get(i).getIdCoche());

						panelPestana3.add(labelRueda, 0, listaRuedas.size()-i);
					}
					
					break;
				
				case "Asc":
					btnOrdenarAscDesc.setText("Desc");

					for(int i= 0; i<listaRuedas.size(); i++) {
						Label labelRueda = new Label(listaRuedas.get(i).getIdRueda()+", "+listaRuedas.get(i).getMarca()+", "+listaRuedas.get(i).getModelo()+", "+listaRuedas.get(i).getDimensiones()+
								", "+listaRuedas.get(i).getPeso()+", "+listaRuedas.get(i).getComponente()+", "+listaRuedas.get(i).getEstado()+", "+listaRuedas.get(i).getFecha()+", "+listaRuedas.get(i).getSesion()+
								", "+listaRuedas.get(i).getIdCoche());
						
						panelPestana3.add(labelRueda, 0, i+1);
					}

					break;
				}

				
					
				
					
				
				
					
					
				
				
			}
			
		});
		
		panelScrollPestana3.setContent(panelPestana3);
		
		pestana3.setContent(panelScrollPestana3);
		
		
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
