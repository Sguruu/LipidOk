package com.sguru.lipidok.presentation.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.navigation.NavigationState
import com.sguru.lipidok.presentation.ui.screen.MainScreen
import com.sguru.lipidok.presentation.ui.screen.RoleSelectionScreen
import com.sguru.lipidok.presentation.ui.theme.LipidOkTheme
import com.sguru.lipidok.presentation.ui.viewmodel.MainViewModel

@SuppressLint("Custom")
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        //  window.statusBarColor = getColor(R.color.wave)

        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }


        setContent {
            LipidOkTheme {
                MyAppNavHost()
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationState.RoleSelectionScreen.baseRoute
) {
    val navGraph: NavGraph = remember(navController) {
        navController.createGraph(startDestination = startDestination) {
            composable(route = NavigationState.RoleSelectionScreen.baseRoute) {
                RoleSelectionScreen(
                    onButton1Click = {
                        navController.navigate(NavigationState.MainScreen.baseRoute)
                    },
                    onButton2Click = {
                        // Экран пациента
                    },
                )
            }
            composable(route = NavigationState.MainScreen.baseRoute) {
                MainScreen(
                    isNavigationIconClick = {
                        navController.navigate(NavigationState.RoleSelectionScreen.baseRoute)
                    }
                )
            }
            composable(route = NavigationState.DataBaseScreen.baseRoute) {
                Greeting("31")
            }
            composable(route = NavigationState.GeneralScreen.baseRoute) {
                Greeting("31")
            }
        }
    }
    NavHost(navController = navController, graph = navGraph)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LipidOkTheme {
        Greeting("Android")
    }
}