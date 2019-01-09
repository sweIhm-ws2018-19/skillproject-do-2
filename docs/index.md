<img src="https://user-images.githubusercontent.com/38068315/48347565-e3633e80-e67e-11e8-9716-77451c3b12f0.png" alt="Logo" class = "inline"/>

## Status

![](https://travis-ci.org/sweIhm-ws2018-19/skillproject-do-2.svg?branch=master)

[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=skillproject%3AsimpleBarkeeper&metric=alert_status)](https://sonarcloud.io/dashboard?id=skillproject%3AsimpleBarkeeper)


## Systemidee
### Alexa Skill - The Simple Barkeeper

Unser Amazon Alexa Skill basiert auf der Funktionalität eines Barkeepers. 
Er soll eine Entscheidungshilfe bzgl. der Getränkewahl bieten, die Zutaten von Rezepten wiedergeben und die Möglichkeit bieten, aus allen Drinks einen persönlichen Favoriten festzulegen. 

### Key Features

- Zutaten eines Drinks abrufen
- Rezept eines Drinks abrufen
- Favoriten festlegen
- Drink vorschlagen lassen 

<br>

---
Die Entwicklung des Skills stützt sich auf eine agile Methode der Softwareentwicklung.
Bei genaueren Fragen zu Entwicklungsstand, Code etc. kann Ihnen unser Wiki sicher weiterhelfen, dort finden Sie alle nötigen Informationen. 

## Anwendungsfalldiagramm
<img src="https://raw.githubusercontent.com/sweIhm-ws2018-19/skillproject-do-2/master/Alexa-Skill/Sprint_3/Anwendungsfall.png" alt="Anwendungsfalldiagramm" class="inline"/>

## Klassendiagramm des AWS Lambda Funktion
<img src="https://raw.githubusercontent.com/sweIhm-ws2018-19/skillproject-do-2/master/Alexa-Skill/Sprint_3/Klassendiagramm.png" alt="Klassenmodell" class="inline"/>
<br><br>
## Highlight - Drink vorschlagen

### User Story

| User Story | Akzeptanzkriterien |
| --- | --- |
|Als User möchte ich vom Simple Barkeeper tageszeitabhängig einen Drink vorgeschlagen bekommen - anhand einer genannten Zutat, eines Geschmacks oder per Zufall und unter der Angabe ob Alkohol erwünscht ist. Ich will mir keine eigenen Gedanken zu einem Getränkewunsch machen und eventuell vorhandene Zutaten aufzubrauchen.| • User ruft Funktion per Ausruf "Drink vorschlagen auf" und wird vom SB gefragt, ob Alkohol erlaubt ist.<br>• Nach seiner Antwort wird er vom SB gefragt, ob nach Zutat/Geschmack/Zufall.<br>• Bei der Wahl "Zutat" wird vom SB eine gewünschte Zutat erfragt, bei der Wahl "Geschmack" ein Geschmack. Bei “Zufall“ wird der Drink random aber auf Basis der Tageszeit ausgesucht.<br>• User nennt Zutat/Geschmack und erhält, einen entsprechenden Vorschlag vom SB (Drinkname).<br>• Danach erfolgt Frage, ob weiterer Vorschlag erfolgen soll.<br>• Falls ja, neuer Vorschlag nach bekannten Vorgaben.<br>• Falls nein, Rückkehr zum Hauptmenü.<br>• Möglichkeit abzubrechen und in Hauptmenü zurückzukehren.
<br><br>
### Sequenzdiagramm
<img src="https://raw.githubusercontent.com/sweIhm-ws2018-19/skillproject-do-2/master/Alexa-Skill/Sprint_3/DrinkVorschlagen.png" alt="Drink vorschlagen" class="inline"/>

<br><br><br><br>
### Fußnote
Dieses Projekt enstand im Rahmen von "Softwareengineering I" im Laufe eines Semesters. 
