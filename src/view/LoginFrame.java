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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField textFieldUsername;
	private JPasswordField fieldPassword;

	// Costruttore
	public LoginFrame(Controller controller) {
		super("Login");

		// Creazione dei componenti dell'interfaccia utente
		JLabel labelUsername = new JLabel("Username: ");
		this.textFieldUsername = new JTextField(15);
		JLabel labelPassword = new JLabel("Password: ");
		this.fieldPassword = new JPasswordField(15);
		fieldPassword.setEchoChar('*');
		JButton login = new JButton("Login");
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
		add(fieldPassword, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(login, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		add(registrati, gbc);

		// Azione per il bottone "Login"
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ottenimento dell'username e della password inseriti dall'utente
				char[] passwordChars = fieldPassword.getPassword();
				String password = new String(passwordChars);

				// Verifica delle credenziali di accesso
				int idUser = controller.checkLogin(textFieldUsername.getText(), password);
				if (idUser != -1) {
					// Se le credenziali sono corrette, apre la finestra principale e chiude quella
					// di login
					new MainWindow(idUser, controller);
					dispose();
				} else {
					// Se le credenziali non sono corrette, mostra un messaggio di errore
					JOptionPane.showMessageDialog(null, "Dati non corretti, Riprova.", "Errore",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// Azione per il bottone "Registrati"
		registrati.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Apre la finestra per la registrazione di un nuovo utente
				new SignInFrame(controller);

			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

}
