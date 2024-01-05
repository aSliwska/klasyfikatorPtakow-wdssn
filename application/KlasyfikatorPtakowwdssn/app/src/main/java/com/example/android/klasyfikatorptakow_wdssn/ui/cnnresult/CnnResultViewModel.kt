package com.example.android.klasyfikatorptakow_wdssn.ui.cnnresult

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.klasyfikatorptakow_wdssn.cnn.AnalysisResult
import com.example.android.klasyfikatorptakow_wdssn.cnn.Labels
import com.example.android.klasyfikatorptakow_wdssn.cnn.ORTAnalyzer
import kotlinx.coroutines.launch

class CnnResultViewModel: ViewModel() {
    var waitText by mutableStateOf("Waiting for results...")
    var firstLabelText by mutableStateOf("")
    var firstPercentageText by mutableStateOf("")
    var secondLabelText by mutableStateOf("")
    var secondPercentageText by mutableStateOf("")
    var thirdLabelText by mutableStateOf("")
    var thirdPercentageText by mutableStateOf("")
    private var hasAnalysisLaunched = false

    private fun showResults(results: AnalysisResult) {
        waitText = ""

        firstLabelText = Labels.LABELS[results.detectedIndices[0]]
        firstPercentageText = "%.2f%%".format(results.detectedScore[0])

        secondLabelText = Labels.LABELS[results.detectedIndices[1]]
        secondPercentageText = "%.2f%%".format(results.detectedScore[1])

        thirdLabelText = Labels.LABELS[results.detectedIndices[2]]
        thirdPercentageText = "%.2f%%".format(results.detectedScore[2])
    }

    fun getCnnResults(bitmap: Bitmap) {
        if (!hasAnalysisLaunched) {
            hasAnalysisLaunched = true

            viewModelScope.launch {
                val analysisResult = ORTAnalyzer.getExistingInstance().analyze(bitmap)
                showResults(analysisResult)
            }
        }
    }
}