package controller;

import view.LoginFrame;

public class Main {

	private static Controller controller;

	public static void main(String[] args) {

		// Creazione di un'istanza del controller
		controller = new Controller();
		// Creazione di un'istanza del frame di login e passaggio del controller ad esso
		new LoginFrame(controller);
	}

}
