# Testat
Beim Spielen mit ihrer neuen Holzeisenbahn legen Wladimir und Donald eine Strecke aus zwei Schienenkreisen, die in der Mitte ein gemeinsames Teilstück haben. Damit es keinen Streit gibt, beschließen die beiden,
dass ihre Loks immer nur abwechselnd dieses gemeinsame Schienenstück befahren dürfen.

Entwerfen Sie unter Verwendung von Semaphoren eine Steuerung für die beiden Loks. Beachten Sie dabei,
dass die Loks unterschiedlich schnell sind. Eine Lok, welche die Weiche erreicht, jedoch noch nicht an der
Reihe ist, muss warten, bis die andere Lok das Mittelstück verl¨asst. Andererseits soll die Lok ohne zu warten
weiterfahren, wenn sie die Weiche erreicht und klar ist, dass das Schienenstück für sie frei ist. Lok 0 soll zu
Beginn das Mittelstück zuerst befahren.

## Aufgabe 1a

Implementieren Sie eine Java-Lösung für die enter- und exit-Methoden als Erzeuger/Verbraucher-Problem.

## Aufgabe 1b

Implementieren Sie eine Java-Lösung für die enter- und exit-Methoden mit privaten Semaphoren.

