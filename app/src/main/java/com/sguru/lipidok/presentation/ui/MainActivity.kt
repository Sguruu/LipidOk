package com.sguru.lipidok.presentation.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.sguru.lipidok.presentation.ui.screen.IndividualSelectionOfTherapyScreen
import com.sguru.lipidok.presentation.ui.screen.LipidProfileAssessmentResultScreen
import com.sguru.lipidok.presentation.ui.screen.LipidProfileAssessmentScreen
import com.sguru.lipidok.presentation.ui.screen.MainScreen
import com.sguru.lipidok.presentation.ui.screen.RoleSelectionScreen
import com.sguru.lipidok.presentation.ui.theme.LipidOkTheme
import com.sguru.lipidok.presentation.ui.viewmodel.MainViewModel
import com.sguru.lipidok.presentation.ui.viewmodel.factory.MainFactory

@SuppressLint("Custom")
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val factory = MainFactory()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoading.value
        }


        setContent {
            LipidOkTheme {
                MyAppNavHost(factory = factory, viewModel = viewModel)
            }
        }
    }
}

@Composable
internal fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationState.RoleSelectionScreen.baseRoute,
    factory: MainFactory,
    viewModel: MainViewModel,
) {
    val navGraph: NavGraph = remember(navController) {
        navController.createGraph(startDestination = startDestination) {
            composable(
                route = NavigationState.RoleSelectionScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                RoleSelectionScreen(
                    onButton1Click = {
                        navController.navigate(NavigationState.MainScreen.baseRoute)
                    },
                    onButton2Click = {
                        // Экран пациента
                    },
                )
            }
            composable(
                route = NavigationState.MainScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                MainScreen(
                    isNavigationIconClick = {
                        navController.navigate(NavigationState.RoleSelectionScreen.baseRoute)
                    },
                    onButtonLipidProfileAssessmentClick = {
                        navController.navigate(
                            NavigationState
                                .LipidProfileAssessmentScreen.baseRoute
                        )
                    },
                    onButtonIndividualSelectionTherapyClick = {
                        navController.navigate(
                            NavigationState.IndividualSelectionOfTherapyScreen.baseRoute
                        )
                    },
                    startItemNavigationBar = 0
                )
            }
            composable(
                route = NavigationState.DataBaseScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                Greeting("31")
            }
            composable(
                route = NavigationState.GeneralScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                Greeting("31")
            }
            composable(
                route = NavigationState
                    .LipidProfileAssessmentScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                LipidProfileAssessmentScreen(
                    isNavigationIconClick = {
                        navController.navigate(NavigationState.MainScreen.baseRoute)
                    },
                    isButtonGetResultClick = {
                        navController.navigate(
                            NavigationState.LipidProfileAssessmentResultScreen.baseRoute
                        )
                    },
                    lipidProfileQuestions = factory.getReadyLipidProfileQuestions(),
                    viewModel = viewModel
                )
            }
            composable(
                route = NavigationState.LipidProfileAssessmentResultScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                LipidProfileAssessmentResultScreen(
                    isNavigationIconClick = {
                        viewModel.clearLipidProfileQuestions()
                        navController.navigate(NavigationState.LipidProfileAssessmentScreen.baseRoute)
                    },
                    lipidProfileResult = viewModel.getLipidProfileQuestionsResult(),
                    onButtonCompleteClick = {
                        viewModel.clearLipidProfileQuestions()
                        navController.navigate(NavigationState.MainScreen.baseRoute)
                        navController.clearBackStack(NavigationState.MainScreen.baseRoute)
                    }
                )
            }
            composable(
                route = NavigationState.IndividualSelectionOfTherapyScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                IndividualSelectionOfTherapyScreen(
                    individualSelectionQuestion = factory.getIndividualSelectionQuestions(),
                    isNavigationIconClick = {
                        navController.navigate(NavigationState.MainScreen.baseRoute)
                    },
                    onButtonCompleteClick = {
                        navController.navigate(NavigationState.MainScreen.baseRoute)
                    },
                )
            }
        }

        /* Экраны пациента */
    }
    NavHost(navController = navController, graph = navGraph)
}

@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visibleState = MutableTransitionState(
            initialState = false
        ).apply { targetState = true },
        modifier = Modifier,
        enter = slideInVertically(
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
    ) {
        content()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
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