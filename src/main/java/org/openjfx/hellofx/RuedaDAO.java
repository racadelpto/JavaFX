package org.openjfx.hellofx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RuedaDAO {

	/**
	 * Función que recibe un String con el id de una rueda de la BDD y elimina esa
	 * fila de la BDD
	 * 
	 * @param codXX
	 * @return un 0 o 1 en función de si ha borrado la fila o no
	 */
	public static int eliminar(String codXX) {

		// Inicializamos int resultado a 0
		int resultado = 0;

		// Nos conectamos a la base de datos gracias a conectarBD()
		Connection con = ConexionBD.conectarBD();

		// String query con la consulta a ejecutar
		String query = "DELETE from rueda WHERE idRueda = ?";

		// Intentamos
		try {

			// PreparedStatement pStmt con query como referencia gracias a con
			PreparedStatement pStmt = con.prepareStatement(query);

			// Asignamos codXX al primer ? del pStmt
			pStmt.setString(1, codXX);

			// resultado es igual a la ejecución del pStmt
			resultado = pStmt.executeUpdate();

			// Cerramos pStmt y con
			pStmt.close();
			con.close();

		} catch (SQLException e) {

			// Expresamos el error
			e.printStackTrace();
		}

		// Devolvemos resultado
		return resultado;
	}

	/**
	 * Función que recibe un objeto de la clase RuedaVO e inserta datos en la tabla
	 * rueda de la BDD en función de los atributos del objeto
	 * 
	 * @param rueda
	 * @return un 0 o 1 en función de si ha insertado los datos o no
	 */
	public static int insertar(RuedaVO rueda) {

		// Inicializamos int resultado a 0
		int resultado = 0;

		// Si piloto es igual a null
		if (rueda == null) {

			// Devolvemos resultado
			return resultado;
		}

		// String query con la consulta a ejecutar
		String query = "INSERT into rueda values(?,?,?,?,?,?,?,?,?,?)";

		// Nos conectamos a la base de datos gracias a conectarBD()
		Connection con = ConexionBD.conectarBD();

		// Intentamos
		try {

			// PreparedStatement pStmt con query como referencia gracias a con
			PreparedStatement pStmt = con.prepareStatement(query);

			// Si el idRueda de piloto es diferente de null y no es igual a ""
			if (rueda.getIdRueda() != null && !rueda.getIdRueda().equals("")) {

				// Asignamos idRueda de rueda al primer ? del pStmt
				pStmt.setString(1, rueda.getIdRueda());

			} // Si no
			else {

				// Devolvemos resultado
				return resultado;
			}

			// Si la marca de piloto es diferente de null y no es igual a ""
			if (rueda.getMarca() != null && !rueda.getMarca().equals("")) {

				// Asignamos marca de rueda al segundo ? del pStmt
				pStmt.setString(2, rueda.getMarca());

			} // Si no
			else {

				// Devolvemos resultado
				return resultado;
			}

			// Si el modelo de piloto es diferente de null y no es igual a ""
			if (rueda.getModelo() != null && !rueda.getModelo().equals("")) {

				// Asignamos modelo de rueda al tercer ? del pStmt
				pStmt.setString(3, rueda.getModelo());

			} // Si no
			else {

				// Devolvemos resultado
				return resultado;
			}

			// Si la dimensiones de piloto es diferente de null y no es igual a ""
			if (rueda.getDimensiones() != null && !rueda.getDimensiones().equals("")) {

				// Asignamos dimensiones de rueda al cuarto ? del pStmt
				pStmt.setString(4, rueda.getDimensiones());

			} // Si no
			else {

				// Devolvemos resultado
				return resultado;
			}

			// Si el peso de piloto es mayor de -1
			if (rueda.getPeso() > -1) {

				// Asignamos peso de rueda al quinto ? del pStmt
				pStmt.setInt(5, rueda.getPeso());

			} // Si no
			else {

				// Devolvemos resultado
				return resultado;
			}

			// Si el componente de piloto es diferente de null y no es igual a ""
			if (rueda.getComponente() != null && !rueda.getComponente().equals("")) {

				// Asignamos componente de rueda al sexto ? del pStmt
				pStmt.setString(6, rueda.getComponente());

			} // Si no
			else {

				// Devolvemos resultado
				return resultado;
			}

			// Si el estado de piloto es diferente de null y no es igual a ""
			if (rueda.getEstado() != null && !rueda.getEstado().equals("")) {

				// Asignamos componente de rueda al séptimo ? del pStmt
				pStmt.setString(7, rueda.getEstado());

			} // Si no
			else {

				// Devolvemos resultado
				return resultado;
			}

			pStmt.setString(8, null);
			pStmt.setString(9, null);
			pStmt.setString(10, "AlfC4");

			// resultado es igual a la ejecución del pStmt
			resultado = pStmt.executeUpdate();

			// Cerramos pStmt y con
			pStmt.close();
			con.close();

		} catch (SQLException e) {

			// Expresamos el error
			e.printStackTrace();
		}

		// Devolvemos resultado
		return resultado;
	}

	/**
	 * Función que recibe un objeto de la clase RuedaVO y actualiza los datos en la
	 * tabla rueda en función de los atributos del objeto
	 * 
	 * @param piloto
	 * @return un 0 o 1 en función de si ha actualizado los datos o no
	 */
	public static int actualizar(RuedaVO rueda) {

		// Inicializamos in resultado a 0 e int posicion a 1
		int resultado = 0;
		int posicion = 1;

		// String query con la consulta a ejecutar
		String query = "UPDATE rueda SET ";

		// Si carrera es igual a null
		if (rueda == null) {

			// Devolvemos resultado
			return resultado;
		}

		// Si el idRueda de piloto es igual a null y es igual a ""
		if (rueda.getIdRueda() == null && rueda.getIdRueda().equals("")) {

			return resultado;

		}

		// Si la marca de piloto es diferente de null y no es igual a ""
		if (rueda.getMarca() != null && !rueda.getMarca().equals("")) {

			// Sumamos a query ese String
			query += "Marca = ?";

			// Sumamos 1 a posicion
			posicion++;
		}

		// Si el modelo de piloto es diferente de null y no es igual a ""
		if (rueda.getModelo() != null && !rueda.getModelo().equals("")) {

			// Si posicion es igual a 1
			if (posicion == 1) {

				// Sumamos a query ese String
				query += "Modelo = ?";

			} // Si no
			else {

				// Sumamos a query ese String
				query += ", Modelo = ?";
			}

			// Sumamos 1 a posicion
			posicion++;
		}

		// Si la dimensiones de piloto es diferente de null y no es igual a ""
		if (rueda.getDimensiones() != null && !rueda.getDimensiones().equals("")) {

			// Si posicion es igual a 1
			if (posicion == 1) {

				// Sumamos a query ese String
				query += "Dimensiones = ?";

			} // Si no
			else {

				// Sumamos a query ese String
				query += ", Dimensiones = ?";
			}

			// Sumamos 1 a posicion
			posicion++;
		}

		// Si el peso de piloto es mayor de -1
		if (rueda.getPeso() > -1) {

			// Si posicion es igual a 1
			if (posicion == 1) {

				// Sumamos a query ese String
				query += "Peso = ?";

			} // Si no
			else {

				// Sumamos a query ese String
				query += ", Peso = ?";
			}

			// Sumamos 1 a posicion
			posicion++;
		}

		// Si el componente de piloto es diferente de null y no es igual a ""
		if (rueda.getComponente() != null && !rueda.getComponente().equals("")) {

			// Si posicion es igual a 1
			if (posicion == 1) {

				// Sumamos a query ese String
				query += "Componente = ?";

			} // Si no
			else {

				// Sumamos a query ese String
				query += ", Componente = ?";
			}

			// Sumamos 1 a posicion
			posicion++;
		}

		// Si el estado de piloto es diferente de null y no es igual a ""
		if (rueda.getEstado() != null && !rueda.getEstado().equals("")) {

			// Si posicion es igual a 1
			if (posicion == 1) {

				// Sumamos a query ese String
				query += "Estado = ?";

			} // Si no
			else {

				// Sumamos a query ese String
				query += ", Estado = ?";
			}

			// Sumamos 1 a posicion
			posicion++;
		}

		query += ", Fecha = ?, Sesión = ?, Coche_idCoche = ?";

		// Unimos a query ese String
		query = query.concat(" WHERE idRueda = ?");

		// Si posicion es igual a 1
		if (posicion == 1) {

			// Devolvemos resultado
			return resultado;
		}

		// Nos conectamos a la base de datos gracias a conectarBD()
		Connection con = ConexionBD.conectarBD();

		// Intentamos
		try {

			// PreparedStatement pStmt con query como referencia gracias a con
			PreparedStatement pStmt = con.prepareStatement(query);

			// Definimos posicion igual a 1
			posicion = 1;

			// Si la marca de piloto es diferente de null y no es igual a ""
			if (rueda.getMarca() != null && !rueda.getMarca().equals("")) {

				// Asignamos marca de rueda al ? del pStmt en la posicion en ese
				// momento
				pStmt.setString(posicion, rueda.getMarca());

				// Sumamos 1 a posicion
				posicion++;
			}

			// Si el modelo de piloto es diferente de null y no es igual a ""
			if (rueda.getModelo() != null && !rueda.getModelo().equals("")) {

				// Asignamos modelo de rueda al ? del pStmt en la posicion en ese
				// momento
				pStmt.setString(posicion, rueda.getModelo());

				// Sumamos 1 a posicion
				posicion++;
			}

			// Si la dimensiones de piloto es diferente de null y no es igual a ""
			if (rueda.getDimensiones() != null && !rueda.getDimensiones().equals("")) {

				// Asignamos dimensiones de rueda al ? del pStmt en la posicion en ese
				// momento
				pStmt.setString(posicion, rueda.getDimensiones());

				// Sumamos 1 a posicion
				posicion++;
			}

			// Si el peso de piloto es mayor de -1
			if (rueda.getPeso() > -1) {

				// Asignamos peso de rueda al ? del pStmt en la posicion en ese
				// momento
				pStmt.setInt(posicion, rueda.getPeso());

				// Sumamos 1 a posicion
				posicion++;
			}

			// Si el componente de piloto es diferente de null y no es igual a ""
			if (rueda.getComponente() != null && !rueda.getComponente().equals("")) {

				// Asignamos componente de rueda al ? del pStmt en la posicion en ese
				// momento
				pStmt.setString(posicion, rueda.getComponente());

				// Sumamos 1 a posicion
				posicion++;
			}

			// Si el estado de piloto es diferente de null y no es igual a ""
			if (rueda.getEstado() != null && !rueda.getEstado().equals("")) {

				// Asignamos estado de rueda al ? del pStmt en la posicion en ese
				// momento
				pStmt.setString(posicion, rueda.getEstado());

				// Sumamos 1 a posicion
				posicion++;
			}

			// Asignamos fecha de rueda al ? del pStmt en la posicion en ese
			// momento
			pStmt.setString(posicion, null);

			// Sumamos 1 a posicion
			posicion++;

			// Asignamos sesion de rueda al ? del pStmt en la posicion en ese
			// momento
			pStmt.setString(posicion, null);

			// Sumamos 1 a posicion
			posicion++;

			// Asignamos idCoche de rueda al ? del pStmt en la posicion en ese
			// momento
			pStmt.setString(posicion, "AlfC4");

			// Sumamos 1 a posicion
			posicion++;

			// Asignamos idPiloto de piloto al ? del pStmt en la posicion en ese
			// momento
			pStmt.setString(posicion, rueda.getIdRueda());

			// resultado es igual a la ejecución del pStmt
			resultado = pStmt.executeUpdate();

			// Cerramos pStmt y con
			pStmt.close();
			con.close();

		} catch (SQLException e) {

			// Expresamos el error
			e.printStackTrace();
		}

		// Devolvemos resultado
		return resultado;
	}

	public static ArrayList<RuedaVO> cargarRuedas() {

		ArrayList<RuedaVO> listaRuedas = new ArrayList<RuedaVO>();

		Connection con = ConexionBD.conectarBD();

		String query = "Select * from rueda";

		try {
			PreparedStatement pStmt = con.prepareStatement(query);

			ResultSet res = pStmt.executeQuery();

			res.next();

			while (res.next()) {
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

		return listaRuedas;
	}

}
