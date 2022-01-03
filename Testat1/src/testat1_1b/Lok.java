package testat1_1b;

import java.util.concurrent.Semaphore;

public class Lok {
	
	// Der Semaphor mutex wird benutzt, um auf die Zustände zuzugreifen und zu ändern, damit diese nicht in Konflikt geraten.
	private Semaphore mutex = new Semaphore(1, true);
	// Jede Lok erhält einen eigenen privaten Semaphor
	private Semaphore[] priv = new Semaphore[2];
	// Eine Lok kann zwei Zustände haben, Fahren oder Warten
	private int[] state = new int[2];
	// Damit sich die Loks abwechseln, wird immer geschaut, ob die Lok überhaupt als nächstes dran ist. Lok0 startet.
	private int next = 0;
	
	private final int DRIVING = 0;
	private final int WAITING = 1;
	
	public Lok() {
		state[0] = DRIVING;
		state[1] = DRIVING;
		priv[0] = new Semaphore(0, true);
		priv[1] = new Semaphore(0, true);
	}
	
	/**
	 * Wird ausgeführt, wenn Lok0 an der Weiche ankommt
	 * @see Semaphore
	 * @see #exitLok1()
	 */
	void enterLok0() throws InterruptedException {
		System.out.println("Lok0 will den geteilten Abschnitt befahren!");
		mutex.acquire();
		// Nur, wenn Lok1 zuvor gefahren ist, kann Lok0 den Abschnitt befahren (streng abswechselnd)
		if(next == 0) {
			priv[0].release();
		} else {
			state[0] = WAITING;
		}
		mutex.release();
		// Es wird gewartet, wenn der Semaphor von Lok1 noch nicht freigegeben wurde (exitLok1())
		priv[0].acquire();
		System.out.println("Lok0 fährt ein! Choo choo!");
	}

	/**
	 * Wird ausgeführt, wenn Lok0 den gemeinsamen Abschnitt verlässt
	 * @see Semaphore
	 */
	void exitLok0() throws InterruptedException {
		System.out.println("Lok0 verlässt den geteilten Abschnitt...");
		mutex.acquire();
		next = 1;
		if(state[1] == WAITING) {
			state[1] = DRIVING;
			// Lok1 darf nun fahren => Lok1 freigeben!
			priv[1].release();
		}
		mutex.release();
	}
	
	/**
	 * Wird ausgeführt, wenn Lok1 an der Weiche ankommt
	 * @see Semaphore 
	 * @see #exitLok0()
	 */
	void enterLok1() throws InterruptedException {
		System.out.println("Lok1 will den geteilten Abschnitt befahren!");
		mutex.acquire();
		// Nur, wenn Lok0 zuvor gefahren ist, kann Lok1 den Abschnitt befahren (streng abswechselnd)
		if(next == 1) {
			priv[1].release();
		} else {
			state[1] = WAITING;
		}
		mutex.release();
		// Es wird gewartet, wenn der Semaphor von Lok0 noch nicht freigegeben wurde (exitLok0())
		priv[1].acquire();
		System.out.println("Lok1 fährt ein! Choo choo!");
	}

	/**
	 * Wird ausgeführt, wenn Lok1 den gemeinsamen Abschnitt verlässt
	 * @see Semaphore
	 */
	void exitLok1() throws InterruptedException {
		System.out.println("Lok1 verlässt den geteilten Abschnitt...");
		mutex.acquire();
		next = 0;
		if(state[0] == WAITING) {
			state[0] = DRIVING;
			// Lok0 darf nun fahren => Lok0 freigeben!
			priv[0].release();
		}
		mutex.release();
	}
	
}
