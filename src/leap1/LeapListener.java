package leap1;

import com.leapmotion.leap.Bone;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Gesture.State;
import com.leapmotion.leap.Gesture.Type;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Hand;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;

public class LeapListener extends Listener {

	LeapObserver lo = new LeapObserver();

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
	}

	public void onFrame(Controller controller) {
		Frame frame = controller.frame();

		if (!frame.gestures().isEmpty()) {
			System.out.println(gestures(frame.gestures()) + ", " + "Hands: " + frame.hands().count() + ", fingers: "
					+ fingers(frame.hands()) + ", tools: " + frame.tools().count() + ", " + "Frame id: " + frame.id()
					+ ", timestamp: " + frame.timestamp());

		}

	}

	public String gestures(GestureList gestures) {
		if (gestures.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		sb.append("Gestures [");

		Type prevGType = null;
		Type thisGType = null;
		
		
		for (Gesture gesture : gestures) {
			if (thisGType == null || thisGType != prevGType) {
				thisGType = gesture.type();
				sb.append(thisGType);
				sb.append(": ");
			}

			if (gesture.state() == State.STATE_START) {
				if (gesture.type() == Type.TYPE_SWIPE) {
					lo.fire(1);
				} else if (gesture.type() == Type.TYPE_CIRCLE.TYPE_CIRCLE) {
					//lo.fire(2);
				}
			}

			sb.append(gesture.state()).append(".");

			prevGType = thisGType;
		}

		sb.append("]; ");
		return sb.toString();
	}

	public String fingers(HandList hands) {
		StringBuilder sb = new StringBuilder();

		for (Hand hand : hands) {
			sb.append(hand.isLeft() ? "Left Hand [" : "Right Hand [");
			for (Finger finger : hand.fingers()) {
				int i = 0;
				for (Bone.Type boneType : Bone.Type.values()) {
					Bone bone = finger.bone(boneType);
				}
				sb.append("F").append(i).append(":");
				sb.append(finger.direction());
				sb.append(" ");
				i++;
			}
			sb.append("]; ");
		}
		return sb.toString();
	}
}
