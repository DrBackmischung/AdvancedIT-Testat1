package de.mathisneunzig.testat1a;

import java.util.concurrent.Semaphore;

public class Zug {
	
	private Semaphore sharedTrack = new Semaphore(1, true);
	private Semaphore lock = new Semaphore(0, true);
	
	public Zug() {}
	
	// Der Semaphor sharedTrack ist f�r die Verwaltung des gemeinsamen Abschnitts der Strecke zust�ndig
	void enterLok0() throws InterruptedException {
		System.out.println("Zug 0 will den geteilten Abschnitt befahren!");
		sharedTrack.acquire();
		System.out.println("Zug 0 f�hrt ein! Choo choo!");
	}
	
	void exitLok0() {
		System.out.println("Zug 0 verl�sst den geteilten Abschnitt...");
		lock.release();
	}
	
	void enterLok1() throws InterruptedException {
		System.out.println("Zug 1 will den geteilten Abschnitt befahren!");
		lock.acquire();
		System.out.println("Zug 1 f�hrt ein! Choo choo!");
	}
	
	void exitLok1() {
		System.out.println("Zug 1 verl�sst den geteilten Abschnitt...");
		sharedTrack.release();
	}
	
}
