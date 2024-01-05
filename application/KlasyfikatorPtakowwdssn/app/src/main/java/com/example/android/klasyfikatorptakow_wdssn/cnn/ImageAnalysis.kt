package com.example.android.klasyfikatorptakow_wdssn.cnn

import ai.onnxruntime.OnnxTensor
import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.graphics.Bitmap
import java.util.Collections
import java.util.concurrent.CountDownLatch
import kotlin.math.exp

data class AnalysisResult(
    var detectedIndices: List<Int> = emptyList(),
    var detectedScore: MutableList<Float> = mutableListOf<Float>(),
) {}

class ORTAnalyzer private constructor(
    private val ortSession: OrtSession?,
) {

    // singleton
    companion object {
        @Volatile // for thread safety
        private var instance: ORTAnalyzer? = null
        private var initLatch = CountDownLatch(1)

        fun getInstance(ortSession: OrtSession?): ORTAnalyzer {
            // get existing instance or create one if null, thread safe
            val instanceRef = instance ?: synchronized(this) {
                instance ?: ORTAnalyzer(ortSession).also { instance = it }
            }
            initLatch.countDown()

            return instanceRef
        }

        fun getExistingInstance(): ORTAnalyzer {
            initLatch.await() // waits until instance is created
            return instance!!
        }
    }

    // Get indexes of top 3 values
    private fun getTop3(labelVals: FloatArray): List<Int> {
        val indices = mutableListOf<Int>()
        for (k in 0..2) {
            var max: Float = 0.0f
            var idx: Int = 0
            for (i in labelVals.indices) {
                val labelVal = labelVals[i]
                if (labelVal > max && !indices.contains(i)) {
                    max = labelVal
                    idx = i
                }
            }
            indices.add(idx)
        }
        return indices.toList()
    }

    // calculate confidence
    private fun softMax(modelResult: FloatArray): FloatArray {
        val labelVals = modelResult.copyOf()
        val max = labelVals.max()
        var sum = 0.0f

        // Get the reduced sum
        for (i in labelVals.indices) {
            labelVals[i] = exp(labelVals[i] - max)
            sum += labelVals[i]
        }

        if (sum != 0.0f) {
            for (i in labelVals.indices) {
                labelVals[i] /= sum
            }
        }

        return labelVals
    }

    fun analyze(
        imgBitmap: Bitmap,
    ): AnalysisResult {
        val bitmap = imgBitmap.let { Bitmap.createScaledBitmap(it, 224, 224, false) }

        val result = AnalysisResult()

        val imgData = preProcess(bitmap)
        val inputName = ortSession?.inputNames?.iterator()?.next()
        val shape = longArrayOf(1, 3, 224, 224)
        val env = OrtEnvironment.getEnvironment()
        env.use {
            val tensor = OnnxTensor.createTensor(env, imgData, shape)

            tensor.use {
                val output = ortSession?.run(Collections.singletonMap(inputName, tensor))

                output.use {
                    @Suppress("UNCHECKED_CAST")
                    val rawOutput = ((output?.get(0)?.value) as Array<FloatArray>)[0]
                    val probabilities = softMax(rawOutput)
                    result.detectedIndices = getTop3(probabilities)

                    for (idx in result.detectedIndices) {
                        result.detectedScore.add(probabilities[idx] * 100)
                    }
                }
            }
        }
        return result
    }
}
