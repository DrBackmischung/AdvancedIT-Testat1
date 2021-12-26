package testat1_1a;

public class LokThread extends Thread {
	
	private Lok z;
	private int id;
	private double speed;

	private final int lengthSharedTrack = 500;
	private final int lengthOwnTrack = 1000;
	
	public LokThread(int id, Lok z, double speed) {
		this.id = id;
		this.z = z;
		this.speed = speed;
	}
	
	public void run() {
		/*
		 *  Fahren: Das Fahren wird in dem Fall durch das Schlafenlegen des Threads f�r eine 
		 *  gewisse Zeit simuliert. Die Zeit ist l�nger, wenn der Zug langsamer f�hrt.
		 */
		if(id == 0) {
			while(true) {
				try {
					// Zug 0 f�hrt auf der eigenen Strecke
					Thread.sleep((long) (lengthOwnTrack / speed));
					// Zug 0 muss geteilten Abschnitt betreten
					z.enterLok0();
					Thread.sleep((long) (lengthSharedTrack / speed));
					z.exitLok0();
					// Zug 0 verl�sst den geteilten Abschnitt
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			while(true) {
				try {
					// Zug 1 f�hrt auf der eigenen Strecke
					Thread.sleep((long) (lengthOwnTrack / speed));
					// Zug 1 muss geteilten Abschnitt betreten
					z.enterLok1();
					Thread.sleep((long) (lengthSharedTrack / speed));
					z.exitLok1();
					// Zug 1 verl�sst den geteilten Abschnitt
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
