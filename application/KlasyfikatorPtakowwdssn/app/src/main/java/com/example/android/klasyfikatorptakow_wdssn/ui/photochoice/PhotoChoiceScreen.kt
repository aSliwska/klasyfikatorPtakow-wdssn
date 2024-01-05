package com.example.android.klasyfikatorptakow_wdssn.ui.photochoice

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.example.android.klasyfikatorptakow_wdssn.MainNavGraph
import com.example.android.klasyfikatorptakow_wdssn.ui.destinations.CNNResultScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@MainNavGraph(start = true)
@Destination(start = true)
@Composable
fun PhotoChoiceScreen(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current

    val photoChoiceLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
    ) { uri: Uri? ->
        if (uri != null) {
            navigator.navigate(CNNResultScreenDestination(uri))
        } else {
            Toast.makeText(context, "Please choose a photo", Toast.LENGTH_SHORT).show()
        }
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Button(onClick = {
                photoChoiceLauncher.launch("image/*")
            }) {
                Text("Pick a photo")
            }
        }
    }
}