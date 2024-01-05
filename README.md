# klasyfikatorPtakow-wdssn 
Sieć neuronowa rozpoznająca gatunek ptaka na zdjęciu + aplikacja androidowa implementująca model.

# Uwagi:
Przytnij zdjęcie ptaka tak, żeby zajmował większość powierzchni obrazu, ale był widoczny w całości.

# Uruchamianie aplikacji:
1. Importuj [application/KlasyfikatorPtakowwdssn](application/KlasyfikatorPtakowwdssn) jako nowy projekt w Android Studio
2. Zbuduj i uruchom 

# Uruchamianie uczenia sieci (Jupyter Notebook):
1. Pobierz dataset: [BIRDS 525 SPECIES- IMAGE CLASSIFICATION](https://www.kaggle.com/datasets/gpiosenka/100-bird-species)
3. Wrzuć archive.zip do dane/ i wypakuj foldery train/, valid/ i test/ (albo wszystko)
4. Uruchom [cnn/src/prepare_data.ipynb](cnn/src/prepare_data.ipynb)
5. Zmień wersję sieci (zmienna na początku pliku z kodem) jeżeli chcesz zapisać output jako nowe pliki i uruchom [cnn/src/net_ver1.ipynb](cnn/src/net_ver1.ipynb)
