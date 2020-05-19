package meujogo.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private boolean isVisivel;
	private Timer timer;
	private float gravity = 0.05f;

	public Player() {
		this.x = 100;
		this.y = 520;
		isVisivel = true;

		timer = new Timer(10000, this);
		timer.start();
	}


	public void load() {
		ImageIcon referencia = new ImageIcon("res\\skatista.gif");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);

	}

	public void update() {
		x += dx;
		y += dy;

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_W) {
			dy = -3;
		}
		if (codigo == KeyEvent.VK_S) {
			dy = 3;
		}
		if (codigo == KeyEvent.VK_A) {
			dx = -3;
		}
		if (codigo == KeyEvent.VK_D) {
			dx = 3;
		}
	}

	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();


		if (codigo == KeyEvent.VK_W) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_S) {
			dy = 0;
		}
		if (codigo == KeyEvent.VK_A) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_D) {
			dx = 0;
		}
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}


	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
