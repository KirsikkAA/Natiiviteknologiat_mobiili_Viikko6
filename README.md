## Viikkotehtävä 5 - Sää appi

### Mitä Retrofit tekee
- HTTP-pyyntöjen hallinta


### Miten JSON muutetaan dataluokiksi
- Gson hoitaa muunnoksen taustalla

### Miten coroutines toimii tässä
- API-kutsu tehdään taustasäikeessä
- UI päivittyy kun data tulee

### Miten UI-tila toimii
- ViewModel hallitsee WeatherUiState-olion
- Compose reagoi tilamuutoksiin automaattisesti

### Miten API-key on tallennettu
- local.properties → BuildConfig → Retrofit
