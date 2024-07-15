package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Controller;
import model.Persona;

public class EditorPersona extends JFrame {

	private Controller controller;
	private RubricaPanel rubricaPanel;

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldIndirizzo;
	private JTextField textFieldNumero;
	private JTextField textFieldEta;

	// Costruttore per aggiungere una nuova persona
	public EditorPersona(Controller controller, RubricaPanel rubricaPanel, int idUser) {
		super("Aggiungi Contatto");

		this.controller = controller;
		this.rubricaPanel = rubricaPanel;

		// Configurazione componenti
		JLabel errorLabel = new JLabel("Prima di salvare inserisci tutti i campi!");
		errorLabel.setForeground(Color.red);
		JLabel labelNome = new JLabel("Nome: ");
		this.textFieldNome = new JTextField(15);
		JLabel labelCognome = new JLabel("Cognome: ");
		this.textFieldCognome = new JTextField(15);
		JLabel labelIndirizzo = new JLabel("Indirizzo: ");
		this.textFieldIndirizzo = new JTextField(15);
		JLabel labelNumero = new JLabel("Numero: ");
		this.textFieldNumero = new JTextField(15);
		JLabel labelEta = new JLabel("Età: ");
		this.textFieldEta = new JTextField(15);
		JButton salva = new JButton("Salva");
		JButton annulla = new JButton("Annulla");

		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(0, 0, 10, 5);

		errorLabel.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(errorLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(labelNome, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(textFieldNome, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(labelCognome, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(textFieldCognome, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(labelIndirizzo, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(textFieldIndirizzo, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		add(labelNumero, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(textFieldNumero, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(labelEta, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(textFieldEta, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		add(salva, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(annulla, gbc);

		// Azione per il bottone "Salva"
		salva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldNome.getText().isEmpty() || textFieldCognome.getText().isEmpty()
						|| textFieldIndirizzo.getText().isEmpty() || textFieldNumero.getText().isEmpty()
						|| textFieldEta.getText().isEmpty()) {
					errorLabel.setVisible(true);
				} else {
					editorAddPersona(textFieldNome.getText(), textFieldCognome.getText(), textFieldIndirizzo.getText(),
							textFieldNumero.getText(), Integer.parseInt(textFieldEta.getText()), idUser);
					dispose();
				}
			}
		});

		// Azione per il bottone "Annulla"
		annulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	// Costruttore per modificare una persona esistente
	public EditorPersona(Controller controller, RubricaPanel rubricaPanel, int selectedRow, int idUser) {
		super("Modifica Contatto");

		this.controller = controller;
		this.rubricaPanel = rubricaPanel;

		JLabel errorLabel = new JLabel("Prima di salvare inserisci tutti i campi!");
		errorLabel.setForeground(Color.red);
		JLabel labelNome = new JLabel("Nome: ");
		this.textFieldNome = new JTextField(15);
		this.textFieldNome.setText(controller.getRubricaData().getRubrica().get(selectedRow).getNome());
		JLabel labelCognome = new JLabel("Cognome: ");
		this.textFieldCognome = new JTextField(15);
		this.textFieldCognome.setText(controller.getRubricaData().getRubrica().get(selectedRow).getCognome());
		JLabel labelIndirizzo = new JLabel("Indirizzo: ");
		this.textFieldIndirizzo = new JTextField(15);
		this.textFieldIndirizzo.setText(controller.getRubricaData().getRubrica().get(selectedRow).getIndirizzo());
		JLabel labelNumero = new JLabel("Numero: ");
		this.textFieldNumero = new JTextField(15);
		this.textFieldNumero.setText(controller.getRubricaData().getRubrica().get(selectedRow).getNumero());
		JLabel labelEta = new JLabel("Età: ");
		this.textFieldEta = new JTextField(15);
		this.textFieldEta.setText(Integer.toString(controller.getRubricaData().getRubrica().get(selectedRow).getEta()));

		JButton salva = new JButton("Salva");
		JButton annulla = new JButton("Annulla");

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(0, 0, 10, 5);

		errorLabel.setVisible(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(errorLabel, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(labelNome, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(textFieldNome, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(labelCognome, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(textFieldCognome, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(labelIndirizzo, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(textFieldIndirizzo, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		add(labelNumero, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		add(textFieldNumero, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(labelEta, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		add(textFieldEta, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		add(salva, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		add(annulla, gbc);

		salva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (textFieldNome.getText().isEmpty() || textFieldCognome.getText().isEmpty()
						|| textFieldIndirizzo.getText().isEmpty() || textFieldNumero.getText().isEmpty()
						|| textFieldEta.getText().isEmpty()) {
					errorLabel.setVisible(true);
				} else {
					editorUpdatePersona(selectedRow, textFieldNome.getText(), textFieldCognome.getText(),
							textFieldIndirizzo.getText(), textFieldNumero.getText(),
							Integer.parseInt(textFieldEta.getText()), idUser);
					dispose();
				}
			}
		});

		annulla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	// Metodo per aggiungere una nuova persona
	public void editorAddPersona(String nome, String cognome, String indirizzo, String numero, int eta, int idUser) {
		controller.addPersona(new Persona(nome, cognome, indirizzo, numero, eta), idUser);
		controller.setRubricaData(idUser);
		rubricaPanel.setData(controller.getRubricaData());
		rubricaPanel.refresh();
	}

	// Metodo per aggiornare una persona esistente
	public void editorUpdatePersona(int selectedRow, String nome, String cognome, String indirizzo, String numero,
			int eta, int idUser) {
		controller.updatePersona(selectedRow, nome, cognome, indirizzo, numero, eta, idUser);
		controller.setRubricaData(idUser);
		rubricaPanel.setData(controller.getRubricaData());
		rubricaPanel.refresh();
	}
}
