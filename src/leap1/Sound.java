package leap1;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sound {

	public void clap() throws Exception {
		String soundFile = "C:\\devsrc\\code\\hackathon\\leap1\\res\\applause.wav";
		play(soundFile);
	}

	public void ding() throws Exception {
		String soundFile = "C:\\devsrc\\code\\hackathon\\leap1\\res\\glass_ping.wav";
		play(soundFile);
	}

	public void play(String soundFile) throws Exception {
		InputStream in = new FileInputStream(soundFile);
		AudioStream audioStream = new AudioStream(in);

		// play the audio clip with the audioplayer class
		AudioPlayer.player.start(audioStream);

	}
}