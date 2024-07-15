package view;

import java.awt.BorderLayout;

import javax.swing.*;

import model.Rubrica;

public class RubricaPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table; // Tabella per visualizzare i dati della rubrica
	private TableModelRubrica tableModelRubrica; // Modello della tabella per la rubrica

	// Costruttore del pannello Rubrica
	public RubricaPanel() {

		tableModelRubrica = new TableModelRubrica();

		table = new JTable(tableModelRubrica);

		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER); // Aggiunge la tabella all'interno di un JScrollPane

	}

	public void setData(Rubrica rubrica) {
		tableModelRubrica.setData(rubrica);
	}

	// Aggiorna la visualizzazione della tabella
	public void refresh() {
		tableModelRubrica.fireTableDataChanged();
	}

	// Ritorna la riga selezionata nella tabella
	public int getTableSelectedRow() {
		return table.getSelectedRow();
	}

}
