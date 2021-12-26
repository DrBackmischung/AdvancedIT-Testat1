# Testat
Beim Spielen mit ihrer neuen Holzeisenbahn legen Wladimir und Donald eine Strecke aus zwei Schienenkreisen, die in der Mitte ein gemeinsames Teilst�ck haben. Damit es keinen Streit gibt, beschlie�en die beiden,
dass ihre Loks immer nur abwechselnd dieses gemeinsame Schienenst�ck befahren d�rfen.

Entwerfen Sie unter Verwendung von Semaphoren eine Steuerung f�r die beiden Loks. Beachten Sie dabei,
dass die Loks unterschiedlich schnell sind. Eine Lok, welche die Weiche erreicht, jedoch noch nicht an der
Reihe ist, muss warten, bis die andere Lok das Mittelst�ck verl�asst. Andererseits soll die Lok ohne zu warten
weiterfahren, wenn sie die Weiche erreicht und klar ist, dass das Schienenst�ck f�r sie frei ist. Lok 0 soll zu
Beginn das Mittelst�ck zuerst befahren.

## Aufgabe 1a

Implementieren Sie eine Java-L�sung f�r die enter- und exit-Methoden als Erzeuger/Verbraucher-Problem.

## Aufgabe 1b

Implementieren Sie eine Java-L�sung f�r die enter- und exit-Methoden mit privaten Semaphoren.

