package controller;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.*;

public class Controller {

	private Rubrica rubrica;
	private Connection conn; // Connessione al database

	public Controller() {
		try {
			conn = DatabaseConnector.getConnection();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			System.exit(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(1); // Termina l'applicazione in caso di errore
		} catch (IOException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
	}

	// Restituisce la connessione al database
	public Connection getConn() {
		return conn;
	}

	// Imposta i dati della rubrica per un determinato utente
	public void setRubricaData(int idUser) {

		ArrayList<Persona> persone = new ArrayList<Persona>();

		String selectQuery = "SELECT * FROM persone WHERE owner = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(selectQuery);
			stmt.setLong(1, idUser);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {

				String nome = resultSet.getString("nome");
				String cognome = resultSet.getString("cognome");
				String indirizzo = resultSet.getString("indirizzo");
				String numero = resultSet.getString("numero");
				int eta = resultSet.getInt("eta");

				persone.add(new Persona(nome, cognome, indirizzo, numero, eta));
			}
			this.rubrica = new Rubrica(persone);

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante il Login, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Restituisce la rubrica corrente
	public Rubrica getRubricaData() {
		return this.rubrica;
	}

	// Aggiunge una nuova persona al database
	public void addPersona(Persona nuovaPersona, int idUser) {
		String insertQuery = "INSERT INTO persone (nome, cognome, indirizzo, numero, eta, owner) VALUES (?,?,?,?,?,?);";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(insertQuery);
			stmt.setString(1, nuovaPersona.getNome());
			stmt.setString(2, nuovaPersona.getCognome());
			stmt.setString(3, nuovaPersona.getIndirizzo());
			stmt.setString(4, nuovaPersona.getNumero());
			stmt.setInt(5, nuovaPersona.getEta());
			stmt.setInt(6, idUser);
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante l'aggiunta di una nuova persona, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Aggiorna i dati di una persona nel database
	public void updatePersona(int selectedRow, String nome, String cognome, String indirizzo, String numero, int eta,
			int idUser) {
		int idPersona = -1;
		String selectQuery = "SELECT * FROM persone WHERE nome = ? AND cognome = ? AND indirizzo = ? AND numero = ? AND eta = ? AND owner = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(selectQuery);
			stmt.setString(1, rubrica.getRubrica().get(selectedRow).getNome());
			stmt.setString(2, rubrica.getRubrica().get(selectedRow).getCognome());
			stmt.setString(3, rubrica.getRubrica().get(selectedRow).getIndirizzo());
			stmt.setString(4, rubrica.getRubrica().get(selectedRow).getNumero());
			stmt.setLong(5, rubrica.getRubrica().get(selectedRow).getEta());
			stmt.setLong(6, idUser);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				idPersona = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante la modifica, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

		String updateQuery = "UPDATE persone SET nome = ?, cognome = ?, indirizzo = ?, numero = ?, eta = ? WHERE id = ?";
		PreparedStatement stmt2;
		try {
			stmt2 = conn.prepareStatement(updateQuery);
			stmt2.setString(1, nome);
			stmt2.setString(2, cognome);
			stmt2.setString(3, indirizzo);
			stmt2.setString(4, numero);
			stmt2.setLong(5, eta);
			stmt2.setLong(6, idPersona);
			stmt2.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante la modifica, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// Elimina una persona dal database
	public void deletePersona(int selectedRow, int idUser) {
		int idPersona = -1;
		String selectQuery = "SELECT * FROM persone WHERE nome = ? AND cognome = ? AND indirizzo = ? AND numero = ? AND eta = ? AND owner = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(selectQuery);
			stmt.setString(1, rubrica.getRubrica().get(selectedRow).getNome());
			stmt.setString(2, rubrica.getRubrica().get(selectedRow).getCognome());
			stmt.setString(3, rubrica.getRubrica().get(selectedRow).getIndirizzo());
			stmt.setString(4, rubrica.getRubrica().get(selectedRow).getNumero());
			stmt.setLong(5, rubrica.getRubrica().get(selectedRow).getEta());
			stmt.setLong(6, idUser);

			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				idPersona = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante la cancellazione, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

		String deleteQuery = "DELETE FROM persone WHERE id = ?";
		PreparedStatement stmt2;
		try {
			stmt2 = conn.prepareStatement(deleteQuery);
			stmt2.setLong(1, idPersona);
			stmt2.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante la cancellazione, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// Registra un nuovo utente nel database
	public void signInUser(String username, String password) {
		String insertQuery = "INSERT INTO utenti (username, password) VALUES (?,?);";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(insertQuery);
			stmt.setString(1, username);
			stmt.setString(2, password);
			int rowsInserted = stmt.executeUpdate();
			System.out.println(rowsInserted + " row(s) inserted.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante la Registrazione, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	// Verifica le credenziali di accesso di un utente
	public int checkLogin(String username, String password) {
		int idUser = -1;
		String selectQuery = "SELECT id FROM utenti WHERE username = ? AND password = ?";
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(selectQuery);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				idUser = resultSet.getInt("id");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Errore durante il Login, Riprova.", "Errore",
					JOptionPane.ERROR_MESSAGE);
		}

		return idUser;
	}

}
