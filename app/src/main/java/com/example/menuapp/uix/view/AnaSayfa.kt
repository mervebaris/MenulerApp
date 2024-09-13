package com.example.filmleruygulamasi.uix.view

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.menuapp.data.entity.Menuler
import com.google.gson.Gson
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnaSayfa(navController: NavController){
    val menulerListesi = remember { mutableStateListOf<Menuler>() }
    val scope = rememberCoroutineScope() //Snacbarı göstermek için
    val snackbarHostState = remember { SnackbarHostState() } //Snacbarı yönetmek için

    LaunchedEffect(key1 = true) {
        val m1 = Menuler(1, "Köfte", "kofte", 120)
        val m2 = Menuler(2, "Makarna", "makarna", 100)
        val m3 = Menuler(3, "Kadayıf", "kadayif", 80)
        val m4 = Menuler(4, "Baklava", "baklava", 90)
        val m5 = Menuler(5, "Ayran", "ayran", 15)
        val m6 = Menuler(6, "Fanta", "fanta", 25)
        menulerListesi.add(m1)
        menulerListesi.add(m2)
        menulerListesi.add(m3)
        menulerListesi.add(m4)
        menulerListesi.add(m5)
        menulerListesi.add(m6)
    }

    Scaffold(
        topBar = { TopAppBar(title = {Text(text = "Menüler")}) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {paddingValues ->
        LazyVerticalGrid(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            columns = GridCells.Fixed(count = 2) //Yatayda kaç kutucuk olacağını bellirledik
        ) {
            items(
                count = menulerListesi.count(), //kaç veri olduğunu öğrendik
                itemContent = { //tekrarlı çalışacak olan yapı
                    val menu = menulerListesi[it]
                    Card(modifier = Modifier.padding(all = 5.dp)) {
                        Column(modifier = Modifier.fillMaxWidth().clickable {  //Veri transferini ssağladık
                            val menuJson = Gson().toJson(menu) //O an gelen Film nesnesini stringleştirdik
                            navController.navigate("detaySayfa/${menuJson}")

                        }) {
                            val activity = (LocalContext.current as Activity) //resim dosyalarına erişim sağladık
                            Image(bitmap = ImageBitmap.imageResource(
                                id = activity.resources.getIdentifier(
                                    menu.resim,
                                    "drawable",
                                    activity.packageName
                                )
                            ), contentDescription = "", Modifier.size(200.dp, 300.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(text = "${menu.fiyat} ₺" , fontSize = 24.sp)
                                Button(onClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar("${menu.ad} sepete eklendi")
                                    }
                                }) {
                                    Text(text = "Sepet")
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}