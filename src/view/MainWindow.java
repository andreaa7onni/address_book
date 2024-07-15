package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.Controller;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private RubricaPanel rubricaPanel; // Pannello che visualizza la rubrica

	// Costruttore della finestra principale
	public MainWindow(int idUser, Controller controller) {

		super("Rubrica");

		// Imposta i dati della rubrica per l'utente specifico
		controller.setRubricaData(idUser);

		// Crea e configura il pannello della rubrica
		rubricaPanel = new RubricaPanel();
		rubricaPanel.setPreferredSize(new Dimension(600, 200));
		rubricaPanel.setData(controller.getRubricaData());

		// Creazione dei pulsanti
		int widthButton = 130;
		int heightButton = 28;
		JButton buttonNuovo = new JButton("Nuovo");
		buttonNuovo.setPreferredSize(new Dimension(widthButton, heightButton));
		JButton buttonModifica = new JButton("Modifica");
		buttonModifica.setPreferredSize(new Dimension(widthButton, heightButton));
		JButton buttonElimina = new JButton("Elimina");
		buttonElimina.setPreferredSize(new Dimension(widthButton, heightButton));

		// Configurazione del layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(0, 60, 40, 40);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(buttonNuovo, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		add(buttonModifica, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		add(buttonElimina, gbc);

		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(rubricaPanel, gbc);

		// Aggiunta degli ActionListener per i pulsanti
		buttonNuovo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EditorPersona(controller, rubricaPanel, idUser); // Apre una finestra per creare una nuova persona
			}
		});

		buttonModifica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = rubricaPanel.getTableSelectedRow();
				if (selectedRow != -1) {
					new EditorPersona(controller, rubricaPanel, selectedRow, idUser); // Apre una finestra per
																						// modificare la persona
																						// selezionata
				} else {
					JOptionPane.showMessageDialog(null, "Per modificare un contatto è prima necessario selezionarlo.",
							"Errore", JOptionPane.ERROR_MESSAGE); // Mostra un messaggio di errore se non è stata
																	// selezionata alcuna persona
				}
			}
		});

		buttonElimina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = rubricaPanel.getTableSelectedRow();
				if (selectedRow != -1) {
					int response = JOptionPane.showConfirmDialog(null,
							"Sei sicuro di voler eliminare la persona "
									+ controller.getRubricaData().getRubrica().get(selectedRow).getNome()
									+ controller.getRubricaData().getRubrica().get(selectedRow).getCognome() + "?",
							"Conferma", JOptionPane.YES_NO_OPTION);

					if (response == JOptionPane.YES_OPTION) {
						controller.deletePersona(selectedRow, idUser); // Elimina la persona selezionata
						controller.setRubricaData(idUser); // Aggiorna i dati della rubrica
						rubricaPanel.setData(controller.getRubricaData()); // Imposta i nuovi dati nel pannello
						rubricaPanel.refresh(); // Aggiorna la visualizzazione del pannello
					}
				} else {
					JOptionPane.showMessageDialog(null, "Per eliminare un contatto è prima necessario selezionarlo.",
							"Errore", JOptionPane.ERROR_MESSAGE); // Mostra un messaggio di errore se non è stata
																	// selezionata alcuna persona
				}

			}
		});

		setSize(750, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
