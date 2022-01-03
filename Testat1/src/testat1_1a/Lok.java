package testat1_1a;

import java.util.concurrent.Semaphore;

public class Lok {
	
	private Semaphore sharedTrack = new Semaphore(1, true);
	private Semaphore lock = new Semaphore(0, true);
	
	public Lok() {}
	
	// Der Semaphor sharedTrack ist für die Verwaltung des gemeinsamen Abschnitts der Strecke zuständig
	// Der Semaphor lock sorgt dafür, dass Lok0 am Anfang fährt, auch wenn Lok1 als erstes gestartet wird. 
	
	/**
	 * Wird ausgeführt, wenn Lok0 an der Weiche ankommt
	 * @see Semaphore
	 */
	void enterLok0() throws InterruptedException {
		System.out.println("Lok0 will den geteilten Abschnitt befahren!");
		sharedTrack.acquire();
		System.out.println("Lok0 fährt ein! Choo choo!");
	}

	/**
	 * Wird ausgeführt, wenn Lok0 den gemeinsamen Abschnitt verlässt
	 * @see Semaphore
	 */
	void exitLok0() {
		System.out.println("Lok0 verlässt den geteilten Abschnitt...");
		lock.release();
	}

	/**
	 * Wird ausgeführt, wenn Lok1 an der Weiche ankommt
	 * @see Semaphore
	 * @see #exitLok0()
	 */
	void enterLok1() throws InterruptedException {
		System.out.println("Lok1 will den geteilten Abschnitt befahren!");
		// Hier wird gewartet, außer Lok0 hat durch das Verlassen des Abschnitts (exitLok0()) den Semaphor released.
		// Deswegen wird Lok1 am Anfang zwingend warten müssen, bis Lok0 den Abschnitt einmal durchfahren hat
		lock.acquire();
		System.out.println("Lok1 fährt ein! Choo choo!");
	}

	/**
	 * Wird ausgeführt, wenn Lok1 den gemeinsamen Abschnitt verlässt
	 * @see Semaphore
	 */
	void exitLok1() {
		System.out.println("Lok1 verlässt den geteilten Abschnitt...");
		sharedTrack.release();
	}
	
}
