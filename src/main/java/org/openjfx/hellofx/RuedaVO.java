package org.openjfx.hellofx;

/**
 * Clase RuedaVO
 * 
 * @author rhdie
 *
 */
public class RuedaVO {

	// Declaramos los atributos privados de la clase RuedaVO
	private String idRueda;
	private String marca;
	private String modelo;
	private String dimensiones;
	private int peso;
	private String componente;
	private String estado;
	private String fecha;
	private String sesion;
	private String idCoche;

	// Constructor que recibe datos de todos los atributos y se los asigna al objeto
	// que crea de la clase RuedaVO
	public RuedaVO(String idRueda, String marca, String modelo, String dimensiones, int peso, String componente,
			String estado, String fecha, String sesion, String idCoche) {
		super();
		this.idRueda = idRueda;
		this.marca = marca;
		this.modelo = modelo;
		this.dimensiones = dimensiones;
		this.peso = peso;
		this.componente = componente;
		this.estado = estado;
		this.fecha = fecha;
		this.sesion = sesion;
		this.idCoche = idCoche;
	}

	// Constructor que no recibe ning�n atributo pero inicializa un objeto de la
	// clase RuedaVO
	public RuedaVO() {
		super();
	}

	/*
	 * Getters y Setters de los atributos de la clase RuedaVO
	 */

	// idRueda
	public String getIdRueda() {
		return idRueda;
	}

	public void setIdRueda(String idRueda) {
		this.idRueda = idRueda;
	}

	// marca
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	// modelo
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	// dimensiones
	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	// peso
	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	// componente
	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	// estado
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// fecha
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	// sesion
	public String getSesion() {
		return sesion;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	// idCoche
	public String getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(String idCoche) {
		this.idCoche = idCoche;
	}

}
