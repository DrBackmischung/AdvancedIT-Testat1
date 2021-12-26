package de.mathisneunzig.testat1a;

public class Zugmanagement {
	
	public static void start() {
		
		Zug z = new Zug();	

		Zugthread zt0 = new Zugthread(0, z, 1.0D);
		Zugthread zt1 = new Zugthread(1, z, 1.2D);
		
		zt0.start();
		zt1.start();
		
	}
	
}
