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
		System.out.println(
				"Frame Id:" + frame.id()
				+ ", Fingers " + frame.fingers().count()
				/*
				+ ", Timestamp " + frame.timestamp()
				+ ", Hands " + frame.hands().count()
				
				+ ", Tools " + frame.tools().count()
				+ ", Gestures " + frame.gestures().count()*/
				);
		
		GestureList gestureList = frame.gestures();
		for (int index = 0; index < gestureList.count(); index++) {
			Gesture gesture = gestureList.get(index);
			System.out.println("gest" + index);
			
			if(gesture.type() == Gesture.Type.TYPE_CIRCLE) {
			    CircleGesture circleGesture = new CircleGesture(gesture);
			    System.out.println("ko³o");
			    for(Gesture gestureObj : gestureList)
			    {
			        switch (gestureObj.state()) {
			            case STATE_START:
			            	System.out.println("ko³o start");
			                break;
			            case STATE_UPDATE:
			                System.out.println("ko³o update");
			                break;
			            case STATE_STOP:
			            	System.out.println("ko³o stop");
			                break;
			            default:
			            	System.out.println("nie rozpozna³em stanu");
			                break;
			        }
			    }
			    
			   float turns = circleGesture.progress();
			   System.out.println(turns);
			   Pointable circlePointable = circleGesture.pointable();
			   System.out.println("Palec:" + circlePointable);
			   float seconds = circleGesture.durationSeconds();
			   System.out.println("Czas trwania:" + seconds);
			   Vector centerPoint = circleGesture.center();
			   System.out.println("center:" + centerPoint);
			   float radius = circleGesture.radius();
			   System.out.println("promien:" + radius);
			   String clockwiseness;
			   if (circleGesture.pointable().direction().angleTo(circleGesture.normal()) <= Math.PI/2) {
			           clockwiseness = "clockwise";
			   }
			   else
			   {
			       clockwiseness = "counterclockwise";
			   }
			   System.out.println(clockwiseness);
			}
			/*
			if(gesture.type() == Gesture.Type.TYPE_SWIPE) {
			    SwipeGesture swipe = new SwipeGesture(gesture);
			    System.out.println("swipe");
			    for(Gesture gestureObj : gestureList)
			    {
			        switch (gestureObj.state()) {
			            case STATE_START:
			            	System.out.println("swipe start");
			                break;
			            case STATE_UPDATE:
			                System.out.println("swipe update");
			                break;
			            case STATE_STOP:
			            	System.out.println("swipe stop");
			                break;
			            default:
			            	System.out.println("nie rozpozna³em stanu");
			                break;
			        }
			    }
			    Vector swipeDirection = swipe.direction();
			    System.out.println("kierunek: " + swipeDirection);
			    
			    Pointable swiper = swipe.pointable();
			    System.out.println("finger/tool: " + swiper);
			    
			    Vector currentSwipePosition = swipe.position();
			    System.out.println("position: " + currentSwipePosition);
			    
			    float currentSwipeSpeed = swipe.speed();
			    System.out.println("speed: " + currentSwipeSpeed);
			    
			    Vector swipeStart = swipe.startPosition();
			    System.out.println("startposition: " + swipeStart);
			}*/
			
			if(gesture.type() == Gesture.Type.TYPE_SCREEN_TAP) {
			    ScreenTapGesture screentap = new ScreenTapGesture(gesture);
			    System.out.println("screen tap!");
			    for(Gesture gestureObj : gestureList)
			    {
			        switch (gestureObj.state()) {
			            case STATE_START:
			            	System.out.println("screentap start");
			                break;
			            case STATE_UPDATE:
			                System.out.println("screentap update");
			                break;
			            case STATE_STOP:
			            	System.out.println("screentap stop");
			                break;
			            default:
			            	System.out.println("nie rozpozna³em stanu");
			                break;
			        }
			    }
			    
			    Pointable poker = screentap.pointable();
			    System.out.println("finger: " + poker);
			    
			    Vector pokeLocation = screentap.position();
			    System.out.println("position: " + pokeLocation);
			    
			    Vector pokeDirection = screentap.direction();
			    System.out.println("direction: " + pokeDirection);
			}
			
			if(gesture.type() == KeyTapGesture.classType()) {
			    KeyTapGesture keytapGesture = new KeyTapGesture(gesture);
			    System.out.println("key tap");
			    for(Gesture gestureObj : gestureList)
			    {
			        switch (gestureObj.state()) {
			            case STATE_START:
			            	System.out.println("key tap start");
			                break;
			            case STATE_UPDATE:
			                System.out.println("key tap update");
			                break;
			            case STATE_STOP:
			            	System.out.println("key tap stop");
			                break;
			            default:
			            	System.out.println("nie rozpozna³em stanu");
			                break;
			        }
			    }
			}
		}
		
		//musimy stworzyæ obiekt klasy HandList przez nadanie mu wartoœci z frame'u
		/*
		HandList hands123 = frame.hands();
		switch(hands123.count()) { 
		case 0 : System.out.println("poka¿ d³onie");
		break;
		case 1 : System.out.println("masz tylko jedn¹ d³oñ :(");
		break;
		case 2 : System.out.println("masz dwie d³onie!");
		break;
		}
		
		Hand front = hands123.frontmost();
		
		for(int index = 0; index < hands123.count(); index++) {
			System.out.println("rece: " + hands123.get(index));
		}
		
		System.out.println("rece: 1 " + hands123.get(0) + " 2: " + hands123.get(1));
		System.out.println("FRONT: " + front);
		
		//nie dziala
		if(hands123.get(0) == front) {
			System.out.println("TO PIERWSZA JEST Z PRZODU!!!");
		}
		if(hands123.get(1) == front) {
			System.out.println("DRUGA JEST Z TYLU!!!!!");
		}
		*/
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
