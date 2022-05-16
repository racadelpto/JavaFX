package org.openjfx.hellofx;

import java.util.ArrayList;

public class dfgdf {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<RuedaVO> lista = RuedaDAO.cargarRuedas();
		System.out.println(lista.size());
		System.out.println(lista.get(40).getIdRueda());
	}

}
