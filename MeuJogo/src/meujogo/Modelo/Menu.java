package meujogo.Modelo;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

public class Menu {
	
	public String[] options = {"Jogar", "carregar jogo", "sair"};
	
	public int currentOption = 0;
	
	public int maxOption = options.length -1;
	
	public boolean up,down,ok;
	
	public void tick() {
		
		if(down) {
			currentOption++;
			up = false;
			if(currentOption > maxOption) {
				currentOption = 0;
				
			}
		}
		if(up) {
			currentOption--;
			up = false;
			if(currentOption < 0) {
				currentOption = maxOption;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, Container.WIDTH*Container.HEIGHT,  Container.HEIGHT*Container.HEIGHT);
		
		
	}

}
