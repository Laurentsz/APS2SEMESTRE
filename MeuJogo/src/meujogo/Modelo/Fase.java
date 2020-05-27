package meujogo.Modelo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	
	private Image fundoo;
	private Image fimDeJogo;
	private Player player;
	private Timer timer;
	private Timer timer2;
	private List<Lixo1> lixo1;
	private List<Lixo2> lixo2;
	private List<Lixo3> lixo3;
	private List<Lixo4> lixo4;
	private List<Obstaculo1> obstaculo1;
	private List<Obstaculo2> obstaculo2;
	private List<Fundo> fundo;
	int segundos = 0;
	int pontuacao = 0;
	String highScore = "";
	private boolean emJogo;
	private viewer.Menu menu;

	
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("res\\");
		fundoo = referencia.getImage();
		
		if (highScore.equals("")) {
			highScore = this.GetHighScore();
		}

		inicializaFundo();
		player = new Player();
		player.load();

		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();
		
		
		timer2 = new Timer (1000, new ActionListener() {
			public void actionPerformed(ActionEvent qq) {
				if (segundos <= 35) {
					segundos++;
				}
				
				if (segundos == 36) {
					emJogo = false;
				}
			}
		});
		timer2.start();
		
		inicializalixos();
		emJogo = true;
		inicializalixos2();
		emJogo = true;
		inicializalixos3();
		emJogo = true;
		inicializalixos4();
		emJogo = true;
		inicializaobstaculo();
		emJogo = true;
		inicializaobstaculo2();
		emJogo = true;
		

	}


	// Aqui é onde indicamos a quantidade e posição dos objetos.
	public void inicializalixos() {
		int coordenadas[] = new int[30];
		lixo1 = new ArrayList<Lixo1>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 9000 + 3000);
			int y = (int) (Math.random() * 405 + 405);
			lixo1.add(new Lixo1(x, y));
		}
	}

	public void inicializalixos2() {
		int coordenadas[] = new int[30];
		lixo2 = new ArrayList<Lixo2>();

		for (int k = 0; k < coordenadas.length; k++) {
			int x = (int) (Math.random() * 9000 + 3000);
			int y = (int) (Math.random() * 405 + 405);
			lixo2.add(new Lixo2(x, y));
		}
	}

	public void inicializalixos3() {
		int coordenadas[] = new int[30];
		lixo3 = new ArrayList<Lixo3>();

		for (int l = 0; l < coordenadas.length; l++) {
			int x = (int) (Math.random() * 9000 + 3000);
			int y = (int) (Math.random() * 405 + 405);
			lixo3.add(new Lixo3(x, y));

		}
	}

	public void inicializalixos4() {
		int coordenadas[] = new int[30];
		lixo4 = new ArrayList<Lixo4>();

		for (int e = 0; e < coordenadas.length; e++) {
			int x = (int) (Math.random() * 9000 + 3000);
			int y = (int) (Math.random() * 405 + 405);
			lixo4.add(new Lixo4(x, y));

		}
	}

	public void inicializaobstaculo() {
		int coordenadas[] = new int[7];
		obstaculo1 = new ArrayList<Obstaculo1>();

		for (int e = 0; e < coordenadas.length; e++) {
			int x = (int) (Math.random() * 9000 + 3000);
			int y = (int) (Math.random() * 400 + 405);
			obstaculo1.add(new Obstaculo1(x, y));

		}
	}

	public void inicializaobstaculo2() {
		int coordenadas[] = new int[7];
		obstaculo2 = new ArrayList<Obstaculo2>();

		for (int e = 0; e < coordenadas.length; e++) {
			int x = (int) (Math.random() * 9000 + 3000);
			int y = (int) (Math.random() * 400 + 405);
			obstaculo2.add(new Obstaculo2(x, y));

		}
	}

	// Inicializando o fundo
	public void inicializaFundo() {
		int coordenadas[] = new int[1];
		fundo = new ArrayList<Fundo>();
		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 4092 + 0);
			int y = (int) (Math.random());
			fundo.add(new Fundo(0, y));
			fundo.add(new Fundo(4092, y));
		}
	}
	
	
	public void checkPontuacao() {
		if (pontuacao > Integer.parseInt((highScore.split(": ")[1]))) {
			String nome = JOptionPane.showInputDialog("Novo recorde! Insira seu nome:");
			highScore = nome + ": "+ pontuacao;
			
			
			File pontuacaoFile = new File("highScore.dat");
			
			if (!pontuacaoFile.exists()) {
				try {
					pontuacaoFile.createNewFile();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			
			try {
				writeFile = new FileWriter(pontuacaoFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highScore);
			}
			catch (Exception e) { }
			finally {
				try {
					if (writer != null)
						writer.close();
				}
				catch (Exception e) { }
			}
		}

	}	
	
	public String GetHighScore() {
		FileReader readFile = null;
		BufferedReader reader = null;
		try {
			readFile = new FileReader("HighScore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		catch (Exception e) {
			return "";
		}
		finally {
			try {
				if(reader != null)
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Puxando as imagens
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			if (highScore == "")
				highScore = ("Sem Highscore");
			graficos.drawImage(fundoo, 0, 0, null);
			for (int p = 0; p < fundo.size(); p++) {
				Fundo q = fundo.get(p);
				q.load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
				graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);

				for (int j = 0; j < lixo1.size(); j++) {
					Lixo1 in = lixo1.get(j);
					in.load();
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

				}
				for (int k = 0; k < lixo2.size(); k++) {
					Lixo2 in = lixo2.get(k);
					in.load();
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

				}
				for (int l = 0; l < lixo3.size(); l++) {
					Lixo3 in = lixo3.get(l);
					in.load();
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

				}
				for (int w = 0; w < lixo4.size(); w++) {
					Lixo4 in = lixo4.get(w);
					in.load();
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

				}
				for (int vg = 0; vg < obstaculo1.size(); vg++) {
					Obstaculo1 in = obstaculo1.get(vg);
					in.load();
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

				}
				for (int pg = 0; pg < obstaculo2.size(); pg++) {
					Obstaculo2 in = obstaculo2.get(pg);
					in.load();
					graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);

				}

				graficos.setFont(graficos.getFont().deriveFont(20.0f));
				graficos.setColor(Color.white);
				graficos.drawString("TEMPO = " + segundos, 600, 50);
				graficos.drawString("PONTUAÇÃO = " + pontuacao, 800, 50);
				graficos.drawString(highScore, 50, 50);

			}
		} else {
			ImageIcon fimJogo = new ImageIcon("res\\FIMB.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, this);
			
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		for (int p = 0; p < fundo.size(); p++) {
			Fundo on = fundo.get(p);
			if (on.isVisivel()) {
				on.update();
			} else {
				fundo.remove(p);
			}

		}

		for (int j = 0; j < lixo1.size(); j++) {
			Lixo1 in = lixo1.get(j);
			if (in.isVisivel()) {
				in.update();
			} else {
				lixo1.remove(j);
			}
		}
		for (int k = 0; k < lixo2.size(); k++) {
			Lixo2 in = lixo2.get(k);
			if (in.isVisivel()) {
				in.update();
			} else {
				lixo2.remove(k);
			}
		}

		for (int h = 0; h < lixo3.size(); h++) {
			Lixo3 in = lixo3.get(h);
			if (in.isVisivel()) {
				in.update();
			} else {
				lixo3.remove(h);
			}
		}

		for (int u = 0; u < lixo4.size(); u++) {
			Lixo4 in = lixo4.get(u);
			if (in.isVisivel()) {
				in.update();
			} else {
				lixo4.remove(u);
			}
		}

		for (int gv = 0; gv < obstaculo1.size(); gv++) {
			Obstaculo1 in = obstaculo1.get(gv);
			if (in.isVisivel()) {
				in.update();
			} else {
				obstaculo1.remove(gv);
			}
		}

		for (int rt = 0; rt < obstaculo2.size(); rt++) {
			Obstaculo2 in = obstaculo2.get(rt);
			if (in.isVisivel()) {
				in.update();
			} else {
				obstaculo2.remove(rt);
			}
		}
		checarColisoes();
		repaint();
	}

// Sistema de Colisão para quando o player se chocar com algum dos lixos, o jogo acaba.

	public void checarColisoes() {
		Rectangle formaPlayer = player.getBounds();
		Rectangle formaLixo1;
		Rectangle formaLixo2;
		Rectangle formaLixo3;
		Rectangle formaLixo4;
		Rectangle formaObstaculo1;
		Rectangle formaObstaculo2;

		for (int i = 0; i < lixo1.size(); i++) {
			Lixo1 tempLixo1 = lixo1.get(i);
			formaLixo1 = tempLixo1.getBounds();
			if (formaPlayer.intersects(formaLixo1)) {
				pontuacao = pontuacao + 1;
				tempLixo1.setVisivel(false);

			}
		}
		for (int p = 0; p < lixo2.size(); p++) {
			Lixo2 tempLixo2 = lixo2.get(p);
			formaLixo2 = tempLixo2.getBounds();
			if (formaPlayer.intersects(formaLixo2)) {
				pontuacao = pontuacao + 1;
				tempLixo2.setVisivel(false);
			}
		}
		for (int m = 0; m < lixo3.size(); m++) {
			Lixo3 tempLixo3 = lixo3.get(m);
			formaLixo3 = tempLixo3.getBounds();
			if (formaPlayer.intersects(formaLixo3)) {
				pontuacao = pontuacao + 1;
				tempLixo3.setVisivel(false);
			}
		}
		for (int mq = 0; mq < lixo4.size(); mq++) {
			Lixo4 tempLixo4 = lixo4.get(mq);
			formaLixo4 = tempLixo4.getBounds();
			if (formaPlayer.intersects(formaLixo4)) {
				pontuacao = pontuacao + 1;
				tempLixo4.setVisivel(false);
			}
		}

		for (int mh = 0; mh < obstaculo1.size(); mh++) {
			Obstaculo1 tempObstaculo1 = obstaculo1.get(mh);
			formaObstaculo1 = tempObstaculo1.getBounds();
			if (formaPlayer.intersects(formaObstaculo1)) {
				tempObstaculo1.setVisivel(false);
				emJogo = false;
				checkPontuacao();
			}
		}

		for (int xt = 0; xt < obstaculo2.size(); xt++) {
			Obstaculo2 tempObstaculo2 = obstaculo2.get(xt);
			formaObstaculo2 = tempObstaculo2.getBounds();
			if (formaPlayer.intersects(formaObstaculo2)) {
				tempObstaculo2.setVisivel(false);
				emJogo = false;
				checkPontuacao();
			}
		}

	}

	private class TecladoAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
	

	  }

