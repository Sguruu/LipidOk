package com.sguru.lipidok.presentation.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RawRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.presentation.ui.model.LipidRiskGroupType
import com.sguru.lipidok.presentation.ui.model.ScreenEvent
import com.sguru.lipidok.presentation.ui.navigation.NavigationState
import com.sguru.lipidok.presentation.ui.screen.AddLipidProfileScreen
import com.sguru.lipidok.presentation.ui.screen.CreatePatientScreen
import com.sguru.lipidok.presentation.ui.screen.EditLipidProfileScreen
import com.sguru.lipidok.presentation.ui.screen.EditPatientScreen
import com.sguru.lipidok.presentation.ui.screen.IndividualSelectionOfTherapyScreen
import com.sguru.lipidok.presentation.ui.screen.LipidProfileAssessmentResultScreen
import com.sguru.lipidok.presentation.ui.screen.LipidProfileAssessmentScreen
import com.sguru.lipidok.presentation.ui.screen.MainScreen.MainScreen
import com.sguru.lipidok.presentation.ui.screen.PatientInfoScreen
import com.sguru.lipidok.presentation.ui.screen.PatientMainScreen.PatientMainScreen
import com.sguru.lipidok.presentation.ui.screen.PdfReaderScreen
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

        viewModel.appVersion = getAppVersion()

        setContent {
            LipidOkTheme {
                MyAppNavHost(factory = factory, viewModel = viewModel)
            }
        }
    }

    fun getAppVersion(): String {
        return try {
            val packageInfo = this.packageManager.getPackageInfo(this.packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            "N/A"
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
internal fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationState.RoleSelectionScreen.baseRoute,
    factory: MainFactory,
    viewModel: MainViewModel,
) {
    var selectedItemNavigationBar by remember { mutableIntStateOf(0) }
    val onEvent = remember { { event: ScreenEvent -> viewModel.onEvent(event) } }
    var lipidProfileModel: LipidProfileModel? = null
    var rawRes by remember { mutableStateOf<@receiver:RawRes Int?>(null) }

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
                        navController.navigate(NavigationState.PatientMainScreen.baseRoute)
                    },
                )
            }
            composable(
                route = NavigationState.MainScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                // вызов листа
                viewModel.getListPatient()

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
                    onClickCreatePatientButton = {
                        navController.navigate(
                            NavigationState.CreatePatientScreen.baseRoute
                        )
                    },
                    selectedItemNavigationBar = selectedItemNavigationBar,
                    onClickNavBar = { selectedItemNavigationBar = it },
                    viewModel = viewModel,
                    onEvent = onEvent,
                    onPatientClick = {
                        navController.navigate(NavigationState.PatientInfoScreen.baseRoute)
                    }
                )
            }
            composable(
                route = NavigationState.DataBaseScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
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
            composable(
                route = NavigationState.CreatePatientScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                CreatePatientScreen(
                    isNavigationIconClick = {
                        navController.navigate(NavigationState.MainScreen.baseRoute)
                        navController.clearBackStack(NavigationState.MainScreen.baseRoute)
                    },
                    onEvent = onEvent,
                )
            }
            composable(
                route = NavigationState.PatientInfoScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                PatientInfoScreen(
                    viewModel = viewModel,
                    isNavigationIconClick = {
                        navController.popBackStack()
                    },
                    lipidProfileResult = viewModel.getLipidProfileQuestionsResult(
                        LipidRiskGroupType.getByTextType(
                            viewModel.patientInfo.value?.first?.riskLevel ?: ""
                        )
                    ),
                    editLipidProfile = {
                        lipidProfileModel = it
                        navController.navigate(
                            route =
                            NavigationState.EditLipidProfileScreen.baseRoute
                        )
                    },
                    isButtonAddLipidProfileClick = {
                        navController.navigate(
                            route = NavigationState.AddLipidProfileScreen.baseRoute
                        )
                    },
                    isButtonEditPatientClick = {
                        navController.navigate(
                            route = NavigationState.PatientEditScreen.baseRoute
                        )
                    }
                )
            }
            composable(
                route = NavigationState.EditLipidProfileScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                lipidProfileModel?.let {
                    EditLipidProfileScreen(
                        isNavigationIconClick = {
                            navController.popBackStack()
                        },
                        onEvent = onEvent,
                        onSaveButtonClick = {
                            navController.popBackStack()
                        },
                        lipidProfile = it
                    )
                }
            }
            composable(
                route = NavigationState.AddLipidProfileScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                AddLipidProfileScreen(
                    isNavigationIconClick = {
                        navController.popBackStack()
                    },
                    patientId = viewModel.patientInfo.value?.first?.id ?: 0L,
                    onSaveButtonClick = {
                        navController.popBackStack()
                    },
                    onEvent = onEvent
                )
            }
            composable(
                route = NavigationState.PatientEditScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                viewModel.patientInfo.value?.first?.let {
                    EditPatientScreen(
                        isNavigationIconClick = {
                            navController.popBackStack()
                        },
                        patientModel = it,
                        isOnSaveButtonClick = {
                            navController.popBackStack()
                        },
                        onEvent = onEvent
                    )
                }
            }
            /* Экраны пациента */
            composable(
                route = NavigationState.PatientMainScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                PatientMainScreen(
                    isNavigationIconClick = {
                        navController.popBackStack()
                    },
                    appVersion = viewModel.appVersion,
                    onClickArticle = {
                        rawRes = it
                        Log.d("MyTest", ">>>onClickArticle $it")
                        navController.navigate(NavigationState.PdfReaderScreen.baseRoute)
                    }
                )
            }
            composable(
                route = NavigationState.PdfReaderScreen.baseRoute,
                enterTransition = { fadeIn(animationSpec = tween(100)) },
            ) {
                rawRes?.let {
                    PdfReaderScreen(
                        isNavigationIconClick = {
                            navController.popBackStack()
                        },
                        rawRes = it
                    )
                }
            }
        }
    }
    NavHost(navController = navController, graph = navGraph)
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