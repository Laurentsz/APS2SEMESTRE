package meujogo.Modelo;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {
	 
	//public static void PlaySound(File Sound)
	//  {
	//    try{
	//      Clip clip = AudioSystem.getClip();
	//      clip.open(AudioSystem.getAudioInputStream(Sound));
	//      clip.loop(100);
	//      Thread.sleep(clip.getMicrosecondLength()/1000);
	//    }catch(Exception e){
	//    }
	//  }
	
	 //File musica = new File("res\\CharlieBrownJr.wav");
 	//Sounds.PlaySound(musica);

	    public void executaSom(String caminho) {
	        try {
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(caminho).getAbsoluteFile());
	            Clip clip = AudioSystem.getClip();
	            clip.open(audioInputStream);
	            clip.loop(0);
	        } catch (Exception ex) {
	            System.out.println("Erro ao executar SOM!");
	            ex.printStackTrace();
	        }
	    }
	}