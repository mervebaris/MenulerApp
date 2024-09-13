package com.example.menuapp.uix.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.filmleruygulamasi.uix.view.AnaSayfa
import com.example.filmleruygulamasi.uix.view.DetaySayfa
import com.example.menuapp.data.entity.Menuler
import com.google.gson.Gson

@Composable
fun SayfaGecisleri(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anaSayfa"){
        composable("anaSayfa"){
            AnaSayfa(navController = navController)
        }
        composable("detaySayfa/{menu}",
            arguments = listOf(
                navArgument("menu"){type = NavType.StringType}
            )){
            val json = it.arguments?.getString("menu") //Stringleştirme işlemi yaptık
            val nesne = Gson().fromJson(json,Menuler::class.java) //json'ı -> Nesneye çevirdik
            DetaySayfa(nesne)
        }
    }
}
