import java.io.IOException;

import com.leapmotion.leap.*;
//import com.leapmotion.leap.Gesture.State;

class LeapListener extends Listener {
	public void onInit(Controller controller) {
		System.out.println("Initialized");
	}

	public void onConnect(Controller controller) {
		System.out.println("Connected");
		controller.enableGesture(Gesture.Type.TYPE_SWIPE);
		controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
		controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
		controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
		
		/*controller.config().setFloat("Gesture.Circle.MinRadius", 10.0f);
		controller.config().setFloat("Gesture.Circle.MinArc", .5f);
		controller.config().save();*/
	}
	
	public void onDisconnect(Controller controller) {
		System.out.println("Motion Sensor DisConnected");
	}
	
	public void OnExit(Controller controller) {
		System.out.println("Exited");
	}
	
	public void onFrame(Controller controller) {
		Frame frame = controller.frame();
		System.out.println("Frame Id:" + frame.id()
				+ ", Timestamp " + frame.timestamp()
				+ ", Hands " + frame.hands().count()
				+ ", Fingers " + frame.fingers().count()
				+ ", Tools " + frame.tools().count()
				+ ", Gestures " + frame.gestures().count()
				); 
		
		//musimy stworzyæ obiekt klasy HandList przez nadanie mu wartoœci z frame'u
		
		HandList hands123 = frame.hands();
		/*switch(hands123.count()) { 
		case 0 : System.out.println("poka¿ d³onie");
		break;
		case 1 : System.out.println("masz tylko jedn¹ d³oñ :(");
		break;
		case 2 : System.out.println("masz dwie d³onie!");
		break;
		}
		*/
		Hand front = hands123.frontmost();
		
		/*for(int index = 0; index < hands123.count(); index++) {
			System.out.println("rece: " + hands123.get(index));
		}*/
		
		//System.out.println("rece: 1 " + hands123.get(0) + " 2: " + hands123.get(1));
		//System.out.println("FRONT: " + front);
		
		//nie dziala
		if(hands123.get(0) == front) {
			System.out.println("TO PIERWSZA JEST Z PRZODU!!!");
		}
		if(hands123.get(1) == front) {
			System.out.println("DRUGA JEST Z TYLU!!!!!");
		}
	}
}

public class LeapController {

	public static void main(String[] args) {
		LeapListener listener = new LeapListener();	  //tworzymy listener - obserwuje zmiany w urz¹dzeniu
		Controller controller1 = new Controller();    //tworzymy controller
		
		controller1.addListener(listener);     //dodajemy listenera do controllera
		
		System.out.println("Press enter to quit");
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		controller1.removeListener(listener);

	}

}
