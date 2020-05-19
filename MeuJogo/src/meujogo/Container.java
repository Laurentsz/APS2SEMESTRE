package meujogo;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
	
	public static void main (String []args) {
		new Container();
		
		File musica = new File("res\\CharlieBrownJr.wav");

	    Fase.PlaySound(musica);

	}
	
//	static void PlaySound(File Sound)
//	  {
//	    try{
//	      Clip clip = AudioSystem.getClip();
//	      clip.open(AudioSystem.getAudioInputStream(Sound));
//	      clip.start();
//
//	      Thread.sleep(clip.getMicrosecondLength()/1000);
//	    }catch(Exception e)
//	    {
//
//	    }
//	  }
}
