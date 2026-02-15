## Viikkotehtävä 5 - Sää appi

### Mitä Retrofit tekee
Retrofit on Squaren kehittämä kirjasto, joka tekee HTTP-pyyntöjen tekemisestä helppoa ja tyyppiturvalista. Retrofit mahdollistaa WeatherApi rajapinnan.

### Miten JSON muutetaan dataluokiksi
Gson konvertoi JSONin Kotlinin data-luokiksi taustalla.

### Miten coroutines toimii tässä
API-kutsut tehdään taustasäikeessä viewModelScope.launch-coroutinella. Kun data tulee, stateFlow päivittyy ja Compose UI reagoi automaattisesti.

### Miten UI-tila toimii
ViewModel hallitsee sovelluksen UI-tilaa. Compose muuttaa sovelluksen tilaa StateFlown mukaisesti - Loading, Success, Error

### Miten API-key on tallennettu
API-key on tallennettu local.properties-tiedostoon. build.gradle lukee tiedoston ja lisää BuildConfig kenttään: buildConfigField("String", "OPENWEATHER_API_KEY", "\"$apiKey\"").
Retrofit käyttää kyseistä avainta API-kutsussa.

[Linkki videoon](https://youtu.be/jRCNj6CKuCs)
