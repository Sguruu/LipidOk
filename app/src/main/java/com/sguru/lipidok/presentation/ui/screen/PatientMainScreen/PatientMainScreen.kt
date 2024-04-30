package com.sguru.lipidok.presentation.ui.screen.PatientMainScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.screen.MainScreen.GeneralContent

private const val SCHOOL_HYPERLIPIDEMIA = 0
private const val GENERAL = 1

@Composable
internal fun PatientMainScreen(
    isNavigationIconClick: () -> Unit,
    appVersion: String,
    onClickArticle: (Int) -> Unit,
) {
    var selectedItemNavigatorBar by remember { mutableIntStateOf(0) }

    Screen(
        selectedItemNavigationBar = selectedItemNavigatorBar,
        onClickNavBar = {
            selectedItemNavigatorBar = it
        },
        isNavigationIconClick = isNavigationIconClick,
        appVersion = appVersion,
        onClickArticle = onClickArticle,
    )
}

@Composable
private fun Screen(
    isNavigationIconClick: () -> Unit,
    selectedItemNavigationBar: Int,
    onClickNavBar: (Int) -> Unit,
    appVersion: String,
    onClickArticle: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                isNavigationIconClick = isNavigationIconClick,
                stringResTitle = when (selectedItemNavigationBar) {
                    SCHOOL_HYPERLIPIDEMIA -> {
                        R.string.PatientMainShoolScreen
                    }

                    GENERAL -> {
                        R.string.PatientMainGenerakScreen
                    }

                    else -> {
                        null
                    }
                }

            )
        },
        content = { paddingValue ->
            when (selectedItemNavigationBar) {
                SCHOOL_HYPERLIPIDEMIA -> {
                    SchoolHyperlipidemiaContent(
                        paddingValue = paddingValue,
                        onClickArticle = onClickArticle,
                    )
                }

                GENERAL -> {
                    GeneralContent(
                        paddingValue = paddingValue,
                        appVersion = appVersion,
                    )

                }
            }
        },
        bottomBar = {
            NavigationBar(
                selectedItemNavigationBar = selectedItemNavigationBar,
                onClickNavBar = onClickNavBar
            )
        }
    )
}

@Composable
private fun NavigationBar(
    selectedItemNavigationBar: Int,
    onClickNavBar: (Int) -> Unit,
) {

    val items = listOf("Школа гиперлипидемии", "Общее")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when (index) {
                        SCHOOL_HYPERLIPIDEMIA -> {
                            Icon(Icons.Filled.List, contentDescription = item)
                        }

                        GENERAL -> {
                            Icon(Icons.Filled.AccountBox, contentDescription = item)
                        }

                    }
                },
                label = { Text(item) },
                selected = selectedItemNavigationBar == index,
                onClick = { onClickNavBar(index) }
            )
        }
    }
}
