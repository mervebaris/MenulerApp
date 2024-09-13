package com.example.filmleruygulamasi.uix.view

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menuapp.data.entity.Menuler

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetaySayfa(gelenMenu:Menuler){
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = gelenMenu.ad) }) }
    ) {paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val activity = (LocalContext.current as Activity) //resim dosyalarına erişim sağladık
            Image(bitmap = ImageBitmap.imageResource(
                id = activity.resources.getIdentifier(
                    gelenMenu.resim,
                    "drawable",
                    activity.packageName
                )
            ), contentDescription = "", Modifier.size(200.dp, 300.dp))
            Text(text = "${gelenMenu.fiyat} ₺" , fontSize = 50.sp)

        }
    }
}
