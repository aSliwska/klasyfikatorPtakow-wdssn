package com.example.android.klasyfikatorptakow_wdssn.ui.cnnresult

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.android.klasyfikatorptakow_wdssn.MainNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@MainNavGraph
@Destination
@Composable
fun CNNResultScreen(
    cnnResultViewModel: CnnResultViewModel = viewModel(),
    imageUri: Uri,
    modifier: Modifier = Modifier.width(300.dp)
) {
    val appContext = LocalContext.current.applicationContext
    val bitmap: Bitmap by remember { derivedStateOf {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(appContext.contentResolver, imageUri)
            ) { decoder, _, _ ->
                decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                decoder.isMutableRequired = true
            }
        } else {
            MediaStore.Images.Media.getBitmap(appContext.contentResolver, imageUri)
        }
    }}

    cnnResultViewModel.getCnnResults(bitmap)

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AsyncImage(
            model = bitmap,
            contentDescription = null,
            modifier = modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Fit,
        )
        Text(cnnResultViewModel.waitText)
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier,
        ) {
            Text(cnnResultViewModel.firstLabelText)
            Text(cnnResultViewModel.firstPercentageText)
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier,
        ) {
            Text(cnnResultViewModel.secondLabelText)
            Text(cnnResultViewModel.secondPercentageText)
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier,
        ) {
            Text(cnnResultViewModel.thirdLabelText)
            Text(cnnResultViewModel.thirdPercentageText)
        }
    }
}