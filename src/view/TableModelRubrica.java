package view;

import javax.swing.table.AbstractTableModel;

import model.Persona;
import model.Rubrica;

public class TableModelRubrica extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Rubrica rubrica; // Contiene i dati della rubrica
	private String[] columnNames = { "Nome", "Cognome", "Numero" }; // Nomi delle colonne della tabella

	// Imposta i dati della rubrica nel modello di tabella
	public void setData(Rubrica rubrica) {
		this.rubrica = rubrica;
	}

	// Ritorna il numero di righe nella tabella
	@Override
	public int getRowCount() {
		return rubrica.getRubrica().size();
	}

	// Ritorna il numero di colonne nella tabella
	@Override
	public int getColumnCount() {
		return 3;
	}

	// Ritorna il valore della cella specificata
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Persona persona = rubrica.getRubrica().get(rowIndex);
		switch (columnIndex) {
		case 0:
			return persona.getNome();
		case 1:
			return persona.getCognome();
		case 2:
			return persona.getNumero();
		default:
			return null;
		}
	}

	// Ritorna il nome della colonna specificata
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
