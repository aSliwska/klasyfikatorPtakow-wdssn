# klasyfikatorPtakow-wdssn // Bird Classifier
## PL
Sieć neuronowa rozpoznająca gatunek ptaka na zdjęciu + aplikacja androidowa implementująca model.

### Uwagi:
Przytnij zdjęcie ptaka tak, żeby zajmował większość powierzchni obrazu, ale był widoczny w całości.

### Uruchamianie aplikacji:
1. Importuj [application/KlasyfikatorPtakowwdssn](application/KlasyfikatorPtakowwdssn) jako nowy projekt w Android Studio
2. Zbuduj i uruchom 

### Uruchamianie uczenia sieci (Jupyter Notebook):
1. Pobierz dataset: [BIRDS 525 SPECIES- IMAGE CLASSIFICATION](https://www.kaggle.com/datasets/gpiosenka/100-bird-species)
3. Wrzuć archive.zip do [cnn/dane/](cnn/dane/) i wypakuj
4. Uruchom [cnn/src/prepare_data.ipynb](cnn/src/prepare_data.ipynb)
5. Zmień wersję sieci (zmienna na początku pliku z kodem) jeżeli chcesz zapisać output jako nowe pliki i uruchom [cnn/src/net_ver3.ipynb](cnn/src/net_ver3.ipynb)

## ENG
Convolutional neural network that recognizes bird species from photos + android app that implements the model.

### Notes:
Crop the photo before use - the bird should take up most of the image and still have its entire body visible.

### Run the app:
1. Import [application/KlasyfikatorPtakowwdssn](application/KlasyfikatorPtakowwdssn) as a new project in Android Studio
2. Build and run 

### Run neural network training (Jupyter Notebook):
1. Download the dataset: [BIRDS 525 SPECIES- IMAGE CLASSIFICATION](https://www.kaggle.com/datasets/gpiosenka/100-bird-species)
3. Put archive.zip in [cnn/dane/](cnn/dane/) and extract
4. Run [cnn/src/prepare_data.ipynb](cnn/src/prepare_data.ipynb)
5. Change net version (variable at the beginning of the file) if you want to save the output as new files and run [cnn/src/net_ver3.ipynb](cnn/src/net_ver3.ipynb)
