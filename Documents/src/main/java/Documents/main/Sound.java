import Documents.main;

import java.net.URL;
import javax.sound.sampled.Clip;

public class Sound {
	//used to open the audio file
	Clip clip;

	//to store the path of the sound file
	URL soundURL[] = new URL[20];

	public Sound() {
		soundURL[0] = getClass().getResource("/sound/");
	}

}