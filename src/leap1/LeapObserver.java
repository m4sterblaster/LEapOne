package leap1;

public class LeapObserver {

	Sound sound = new Sound();

	public void fire(int state) {
		try {
			if (state == 1) {
				sound.ding();
			} else if (state == 2) {
				sound.clap();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
