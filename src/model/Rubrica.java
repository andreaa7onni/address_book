package model;

import java.util.ArrayList;

public class Rubrica {

	private ArrayList<Persona> rubrica = new ArrayList<>(); // Lista di oggetti Persona

	// Costruttore della classe Rubrica
	public Rubrica(ArrayList<Persona> rubrica) {
		this.setRubrica(rubrica);
	}

	// Ritorna la lista di persone
	public ArrayList<Persona> getRubrica() {
		return rubrica;
	}

	// Imposta la lista di persone
	public void setRubrica(ArrayList<Persona> rubrica) {
		this.rubrica = rubrica;
	}

}
