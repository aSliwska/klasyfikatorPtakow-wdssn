# klasyfikatorPtakow-wdssn 
Sieć neuronowa rozpoznająca gatunek ptaka na zdjęciu + aplikacja androidowa implementująca model.

# Uwagi:
Przytnij zdjęcie ptaka tak, żeby zajmował większość powierzchni obrazu, ale był widoczny w całości.

# Uruchamianie aplikacji:
1. Importuj [a relative link](application/KlasyfikatorPtakowwdssn) jako nowy projekt w Android Studio
2. Zbuduj i uruchom 

# Uruchamianie uczenia sieci (Jupyter Notebook):
1. Pobierz dataset: https://www.kaggle.com/datasets/gpiosenka/100-bird-species
2. Wrzuć archive.zip do dane/ i wypakuj foldery train/, valid/ i test/ (albo wszystko)
3. Uruchom [a relative link](cnn/src/prepare_data.ipynb)
5. Zmień wersję sieci (zmienna na początku pliku z kodem) jeżeli chcesz zapisać output jako nowe pliki i uruchom [a relative link](cnn/src/net_ver1.ipynb)
