package com.example.android.klasyfikatorptakow_wdssn

import ai.onnxruntime.OrtEnvironment
import ai.onnxruntime.OrtSession
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.android.klasyfikatorptakow_wdssn.cnn.ORTAnalyzer
import com.example.android.klasyfikatorptakow_wdssn.ui.NavGraphs
import com.example.android.klasyfikatorptakow_wdssn.ui.theme.KlasyfikatorPtakowwdssnTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.rememberNavHostEngine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val ortScope = CoroutineScope(Job() + Dispatchers.Main)
    private var ortEnv: OrtEnvironment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KlasyfikatorApp()
        }

        ortEnv = OrtEnvironment.getEnvironment()
        setORTAnalyzer()
    }

    private fun setORTAnalyzer(){
        ortScope.launch {
            ORTAnalyzer.getInstance(createOrtSession())
        }
    }

    private suspend fun createOrtSession(): OrtSession? = withContext(Dispatchers.Default) {
        ortEnv?.createSession(readModel())
    }

    private suspend fun readModel(): ByteArray = withContext(Dispatchers.IO) {
        resources.openRawResource(R.raw.model_525_ver3).readBytes()
    }
}

@RootNavGraph(start = true)
@NavGraph
annotation class MainNavGraph(
    val start: Boolean = false
)

@Composable
fun KlasyfikatorApp() {

    val navHostEngine = rememberNavHostEngine(
        navHostContentAlignment = Alignment.TopCenter,
        rootDefaultAnimations = RootNavGraphDefaultAnimations.ACCOMPANIST_FADING,
        defaultAnimationsForNestedNavGraph = mapOf(
            NavGraphs.main to NestedNavGraphDefaultAnimations(
                enterTransition = {
                    fadeIn(
                        animationSpec = tween(150, easing = LinearEasing)
                    )
                },
                exitTransition = {
                    fadeOut(
                        animationSpec = tween(150, easing = LinearEasing)
                    )
                },
            )
        )
    )

    KlasyfikatorPtakowwdssnTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DestinationsNavHost(
                navGraph = NavGraphs.root,
                engine = navHostEngine,
            )
        }
    }
}

