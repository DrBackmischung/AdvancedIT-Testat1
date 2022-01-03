package testat1_1a;

import java.util.concurrent.Semaphore;

public class Lok {
	
	private Semaphore sharedTrack = new Semaphore(1, true);
	private Semaphore lock = new Semaphore(0, true);
	
	public Lok() {}
	
	// Der Semaphor sharedTrack ist f�r die Verwaltung des gemeinsamen Abschnitts der Strecke zust�ndig
	// Der Semaphor lock sorgt daf�r, dass Lok0 am Anfang f�hrt, auch wenn Lok1 als erstes gestartet wird. 
	
	/**
	 * Wird ausgef�hrt, wenn Lok0 an der Weiche ankommt
	 * @see Semaphore
	 */
	void enterLok0() throws InterruptedException {
		System.out.println("Lok0 will den geteilten Abschnitt befahren!");
		sharedTrack.acquire();
		System.out.println("Lok0 f�hrt ein! Choo choo!");
	}

	/**
	 * Wird ausgef�hrt, wenn Lok0 den gemeinsamen Abschnitt verl�sst
	 * @see Semaphore
	 */
	void exitLok0() {
		System.out.println("Lok0 verl�sst den geteilten Abschnitt...");
		lock.release();
	}

	/**
	 * Wird ausgef�hrt, wenn Lok1 an der Weiche ankommt
	 * @see Semaphore
	 * @see #exitLok0()
	 */
	void enterLok1() throws InterruptedException {
		System.out.println("Lok1 will den geteilten Abschnitt befahren!");
		// Hier wird gewartet, au�er Lok0 hat durch das Verlassen des Abschnitts (exitLok0()) den Semaphor released.
		// Deswegen wird Lok1 am Anfang zwingend warten m�ssen, bis Lok0 den Abschnitt einmal durchfahren hat
		lock.acquire();
		System.out.println("Lok1 f�hrt ein! Choo choo!");
	}

	/**
	 * Wird ausgef�hrt, wenn Lok1 den gemeinsamen Abschnitt verl�sst
	 * @see Semaphore
	 */
	void exitLok1() {
		System.out.println("Lok1 verl�sst den geteilten Abschnitt...");
		sharedTrack.release();
	}
	
}
