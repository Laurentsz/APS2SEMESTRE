package meujogo;

import javax.swing.JFrame;

import meujogo.Modelo.Fase;



public class Container extends JFrame {
	

	public Container() {
		add(new Fase ());
		setTitle("APS 2º SEMESTRE");
		setSize(1024,728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	

	}
	

