package com.kareem.gyms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.kareem.gyms.ui.theme.GymsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymsTheme {
                GymsApp()
            }
        }
    }
}

@Composable
private fun GymsApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "gyms") {
        composable(route = "gyms") {
            GymsScreen { id ->
                navController.navigate("gyms/$id")
            }
        }
        composable(
            route = "gyms/{gym_id}",
            arguments = listOf(
                navArgument("gym_id") {
                    type = NavType.IntType
                },
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "https://www.gymaround.com/details/{gym_id}"
                }
            )
        ) {
            GymDetailsScreen()

            }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    GymsTheme {

    }
}