package testat1_1b;

import java.util.concurrent.Semaphore;

public class Lok {
	
	private Semaphore mutex = new Semaphore(1, true);
	private Semaphore[] priv = new Semaphore[2];
	private int[] state = new int[2];
	private int next = 0;
	
	private final int DRIVING = 0;
	private final int WAITING = 1;
	
	public Lok() {
		state[0] = DRIVING;
		state[1] = DRIVING;
		priv[0] = new Semaphore(0, true);
		priv[1] = new Semaphore(0, true);
	}
	
	// Der Semaphor sharedTrack ist für die Verwaltung des gemeinsamen Abschnitts der Strecke zuständig
	void enterLok0() throws InterruptedException {
		System.out.println("Lok0 will den geteilten Abschnitt befahren!");
		mutex.acquire();
		if(next == 0) {
			priv[0].release();
		} else {
			state[0] = WAITING;
		}
		mutex.release();
		priv[0].acquire();
		System.out.println("Lok0 fährt ein! Choo choo!");
	}
	
	void exitLok0() throws InterruptedException {
		System.out.println("Lok0 verlässt den geteilten Abschnitt...");
		mutex.acquire();
		next = 1;
		if(state[1] == WAITING) {
			state[1] = DRIVING;
			priv[1].release();
		}
		mutex.release();
	}
	
	void enterLok1() throws InterruptedException {
		System.out.println("Lok1 will den geteilten Abschnitt befahren!");
		mutex.acquire();
		if(next == 1) {
			priv[1].release();
		} else {
			state[1] = WAITING;
		}
		mutex.release();
		priv[1].acquire();
		System.out.println("Lok1 fährt ein! Choo choo!");
	}
	
	void exitLok1() throws InterruptedException {
		System.out.println("Lok1 verlässt den geteilten Abschnitt...");
		mutex.acquire();
		next = 0;
		if(state[0] == WAITING) {
			state[0] = DRIVING;
			priv[0].release();
		}
		mutex.release();
	}
	
}
