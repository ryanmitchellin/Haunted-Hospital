import Documents.main;

import java.net.URL;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;

public class Sound {
	//used to open the audio file
	Clip clip;

	//to store the path of the sound file
	URL soundURL[] = new URL[20];

	public Sound() {
		soundURL[0] = getClass().getResource("/sound/map1darkSound.wav");
		soundURL[1] = getClass().getResource("/sound/collectkey.wav");
	}

	//adding the audio and set if not able to load catch error
	public void fileSet(int a) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {

		}
	}

	public void play() {
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

}