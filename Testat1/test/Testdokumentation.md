# Testat
	
Luis Maier - Matrikelnummer 7096964 |
Mathis Neunzig - Matrikelnummer 2240587

Beim Spielen mit ihrer neuen Holzeisenbahn legen Wladimir und Donald eine Strecke aus zwei Schienenkreisen, die in der Mitte ein gemeinsames Teilst�ck haben. Damit es keinen Streit gibt, beschlie�en die beiden,
dass ihre Loks immer nur abwechselnd dieses gemeinsame Schienenst�ck befahren d�rfen.

Entwerfen Sie unter Verwendung von Semaphoren eine Steuerung f�r die beiden Loks. Beachten Sie dabei,
dass die Loks unterschiedlich schnell sind. Eine Lok, welche die Weiche erreicht, jedoch noch nicht an der
Reihe ist, muss warten, bis die andere Lok das Mittelst�ck verl�asst. Andererseits soll die Lok ohne zu warten
weiterfahren, wenn sie die Weiche erreicht und klar ist, dass das Schienenst�ck f�r sie frei ist. Lok 0 soll zu
Beginn das Mittelst�ck zuerst befahren.

## Aufgabe 1a

Implementieren Sie eine Java-L�sung f�r die enter- und exit-Methoden als Erzeuger/Verbraucher-Problem.

### Implementation - Beispiel 1

Im ersten Beispiel ist Lok0 schneller, weshalb Lok0 auch zuerst den gemeinsamen Abschnitt befahren will und bef�hrt. Da in der L�sung aber gefordert ist, dass Lok0 immer zuerst den gemeinsamen Abschnitt bef�hrt, muss der LokThread dies ber�cksichtigen. Daher gibt es einen Semaphore(1, true), den Lok0 aquired, wenn diese den Abschnitt befahren will. Lok1 hat einen anderen Semaphore(0, true), der von Anfang an "voll" ist und der erst durch das Durchfahren von Lok0 freigeschaltet wird. 

``` java
	public static void start() {
		
		Lok l = new Lok();	

		LokThread lok0 = new LokThread(0, l, 1.1D);
		LokThread lok1 = new LokThread(1, l, 1.0D);
		
		lok0.start();
		lok1.start();
		
	}
```

### Ausgabe - Beispiel 1

``` java
Lok0 will den geteilten Abschnitt befahren!
Lok0 f�hrt ein! Choo choo!
Lok1 will den geteilten Abschnitt befahren!
Lok0 verl�sst den geteilten Abschnitt...
Lok1 f�hrt ein! Choo choo!
Lok1 verl�sst den geteilten Abschnitt...
Lok0 will den geteilten Abschnitt befahren!
Lok0 f�hrt ein! Choo choo!
Lok0 verl�sst den geteilten Abschnitt...
Lok1 will den geteilten Abschnitt befahren!
Lok1 f�hrt ein! Choo choo!
Lok1 verl�sst den geteilten Abschnitt...
Lok0 will den geteilten Abschnitt befahren!
Lok0 f�hrt ein! Choo choo!
Lok0 verl�sst den geteilten Abschnitt...
Lok1 will den geteilten Abschnitt befahren!
Lok1 f�hrt ein! Choo choo!
Lok1 verl�sst den geteilten Abschnitt...
```

### Auswertung - Beispiel 1

Lok0 startet zuerst und betritt den gemeinsamen Gleisabschnitt auch zuerst - genau das, was in der Aufgabe auch gefordert war. Da Lok0 etwas schneller ist als Lok1, welche nach Lok0 den gemeinsamen Abschnitt betritt, kommt Lok0 in der Regel zu Beginn immer vor Lok1 an der Weiche an und kann den Abschnitt ohne Probleme betreten. 
Wenn Lok0 den gemeinsamen Gleisabschnitt bef�hrt, kann Lok1 wegen dem "lock"-Semaphor, der mit 0 initialisiert ist, nicht betreten. Erst beim Ausfahren von Lok0 wird der Counter vom "lock"-Semaphor auf 1 gesetzt, was Lok1 das Einfahren in den gemeinsamen Abschnitt erm�glicht. Dabei wird der Counter von "lock" wieder auf 0 gesetzt. Lok0 kann den gemeinsamen Gleisabschnitt zu Beginn betreten, da dieser nicht mit dem "lock"-Semaphor beim Einfahren arbeitet, sondern den "sharedTrack"-Semaphor, der mit 1 initialisiert wird. Lok1 setzt nach dem Durchfahren des gemeinsamen Abschnitts den Counter von diesem wieder auf 1, damit Lok0 wieder einfahren kann. 

### Implementation - Beispiel 2

Im zweiten Beispiel wird Lok1 schneller gemacht und der Thread wird zuerst gestartet. Trotzdem soll gegeben sein, dass Lok0 zuerst den gemeinsamen Abschnitt betritt.

``` java
	public static void start() {
		
		Lok l = new Lok();	

		LokThread lok0 = new LokThread(0, l, 1.0D);
		LokThread lok1 = new LokThread(1, l, 1.1D);

		lok1.start();
		lok0.start();
		
	}
```

### Ausgabe - Beispiel 2

``` java
Lok1 will den geteilten Abschnitt befahren!
Lok0 will den geteilten Abschnitt befahren!
Lok0 f�hrt ein! Choo choo!
Lok0 verl�sst den geteilten Abschnitt...
Lok1 f�hrt ein! Choo choo!
Lok1 verl�sst den geteilten Abschnitt...
Lok0 will den geteilten Abschnitt befahren!
Lok0 f�hrt ein! Choo choo!
Lok1 will den geteilten Abschnitt befahren!
Lok0 verl�sst den geteilten Abschnitt...
Lok1 f�hrt ein! Choo choo!
Lok1 verl�sst den geteilten Abschnitt...
Lok0 will den geteilten Abschnitt befahren!
Lok0 f�hrt ein! Choo choo!
Lok1 will den geteilten Abschnitt befahren!
Lok0 verl�sst den geteilten Abschnitt...
Lok1 f�hrt ein! Choo choo!
Lok1 verl�sst den geteilten Abschnitt...
```

### Auswertung - Beispiel 2

Im zweiten Beispiel wird sichtbar, wie die Semaphoren "sharedTrack" und "lock" wirklich arbeiten. Lok1 f�hrt schneller und erreicht die Weiche schneller, muss jedoch Lok0 vor lassen. Da Lok1 zum Einfahren den "lock"-Semaphor benutzt, der mit 0 initialisiert wurde, kann Lok1 nicht einfahren, bis Lok0 beim Verlassen den "lock"-Semaphor-Counter auf 1 setzt. Dies hindert Lok1 daran, zu Beginn als erstes den gemeinsamen Gleisabschnitt zu befahren. Danach m�ssen die Loks nur warten, bis der Abschnitt frei ist, damit sie einfahren k�nnen.

## Aufgabe 1b

Implementieren Sie eine Java-L�sung f�r die enter- und exit-Methoden mit privaten Semaphoren.

