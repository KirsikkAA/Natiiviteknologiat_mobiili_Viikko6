## Viikkotehtävä 6 - Room

### Mitä Room tekee
Room on Androidin tietokantakirjasto, joka yksinkertaistaa SQLite-tietokantojen kanssa työskentelyn. Datan käsittely tapahtuu järjestelmällisesti: Entity – DAO – Database – Repository – ViewModel – UI.

### Projektin rakenne
<laita kuva>

### Miten datavirta kulkee
1. Käyttäjä syöttää kaupungin hakukenttään
2. Ui kutsuu WeatherViewModel.searchWeather()
3. ViewModel pyytää dataa WeatherRepository-luokalta
4. Repository tarkistaa Room-tietokannan ja jos data puuttuu tai on vanhentunut niin hakee verkosta
5. ViewModel päivittää tilan (StateFlow)
6. Compose UI reagoi tilamuutokseen automaattisesti

### Miten välimuistilogiikka toimii
1. Kun käyttäjä hakee kaupungin sään:
  - Tietokannasta etsitään tallennettu tulos
2. Jos tallennettu data on alle 30 minuuttia vanha:
  - Käytetään välimuistia
3. Muussa tapauksessa:
  - Haetaan data OpenWeather API:sta
  - Tallennetaan uusi tulos tietokantaan
