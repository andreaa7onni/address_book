package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Controller;

public class SignInFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldUsername;
	private JTextField textFieldPassword;

	// Costruttore
	public SignInFrame(Controller controller) {
		super("SignIn");

		// Creazione dei componenti dell'interfaccia utente
		JLabel labelUsername = new JLabel("Username: ");
		this.textFieldUsername = new JTextField(15);
		JLabel labelPassword = new JLabel("Password: ");
		this.textFieldPassword = new JTextField(15);
		JButton registrati = new JButton("Registrati");

		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(0, 0, 10, 5);

		gbc.gridx = 0;
		gbc.gridy = 1;
		add(labelUsername, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(textFieldUsername, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		add(labelPassword, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(textFieldPassword, gbc);

		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(registrati, gbc);

		// Azione per il bottone "Registrati"
		registrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Verifica se i campi username e password sono vuoti
				if (textFieldUsername.getText().strip().length() == 0
						|| textFieldPassword.getText().strip().length() == 0) {
					// Se uno dei campi è vuoto, mostra un messaggio di errore
					JOptionPane.showMessageDialog(null, "I campi non possono essere vuoti.", "Errore",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Se entrambi i campi sono compilati, esegue la registrazione dell'utente, poi
					// chiude la finestra
					controller.signInUser(textFieldUsername.getText(), textFieldPassword.getText());
					dispose();
				}
			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

}
