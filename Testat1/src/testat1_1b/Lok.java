package testat1_1b;

import java.util.concurrent.Semaphore;

public class Lok {
	
	private Semaphore sharedTrack = new Semaphore(1, true);
	private Semaphore lock = new Semaphore(0, true);
	
	public Lok() {}
	
	// Der Semaphor sharedTrack ist f�r die Verwaltung des gemeinsamen Abschnitts der Strecke zust�ndig
	void enterLok0() throws InterruptedException {
		System.out.println("Lok0 will den geteilten Abschnitt befahren!");
		sharedTrack.acquire();
		System.out.println("Lok0 f�hrt ein! Choo choo!");
	}
	
	void exitLok0() {
		System.out.println("Lok0 verl�sst den geteilten Abschnitt...");
		lock.release();
	}
	
	void enterLok1() throws InterruptedException {
		System.out.println("Lok1 will den geteilten Abschnitt befahren!");
		lock.acquire();
		System.out.println("Lok1 f�hrt ein! Choo choo!");
	}
	
	void exitLok1() {
		System.out.println("Lok1 verl�sst den geteilten Abschnitt...");
		sharedTrack.release();
	}
	
}
