package com.example.sneakersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.sneakersapp.navigation.NavGraph
import com.example.sneakersapp.ui.theme.SneakersAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var userPreferences: UserPreferences


    //var userPreferences = UserPreferences()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val startDestination = if(userPreferences.isLoggedIn()) {
                "home"
            }
                else{
                    "login"
                }
            SneakersAppTheme {
             val navController = rememberNavController()
               NavGraph( navController, startDestination)
            }
        }
    }
}
