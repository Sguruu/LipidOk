package com.sguru.lipidok.presentation.ui.screen.PatientMainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.ScreenEvent
import com.sguru.lipidok.presentation.ui.screen.MainScreen.DATA_BASE_NAV
import com.sguru.lipidok.presentation.ui.screen.MainScreen.DataBaseContent
import com.sguru.lipidok.presentation.ui.screen.MainScreen.GENERAL_NAV
import com.sguru.lipidok.presentation.ui.screen.MainScreen.GeneralContent
import com.sguru.lipidok.presentation.ui.screen.MainScreen.NavigationBarSample
import com.sguru.lipidok.presentation.ui.screen.MainScreen.PATIENT_NAV

private const val SCHOOL_HYPERLIPIDEMIA = 0
private const val GENERAL = 1

@Composable
internal fun PatientMainScreen(
    isNavigationIconClick: () -> Unit,
    appVersion: String,
) {
    var selectedItemNavigatorBar by remember { mutableIntStateOf(0) }

    Screen(
        selectedItemNavigationBar = selectedItemNavigatorBar,
        onClickNavBar = {
            selectedItemNavigatorBar = it
        },
        isNavigationIconClick = isNavigationIconClick,
        appVersion = appVersion,
    )
}

@Composable
private fun Screen(
    isNavigationIconClick: () -> Unit,
    selectedItemNavigationBar: Int,
    onClickNavBar: (Int) -> Unit,
    appVersion: String,
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
