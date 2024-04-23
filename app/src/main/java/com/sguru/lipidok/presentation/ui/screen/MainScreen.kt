package com.sguru.lipidok.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk

@Composable
internal fun MainScreen(
    isNavigationIconClick: () -> Unit,
    onButtonLipidProfileAssessmentClick: () -> Unit,
    onButtonIndividualSelectionTherapyClick: () -> Unit,
    startItemNavigationBar: Int = 0
) {
    var selectedItemNavigationBar by remember { mutableIntStateOf(startItemNavigationBar) }

    Scaffold(
        topBar = {
            TopBarLipidOk(
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            if (selectedItemNavigationBar == 0) {
                MainContent(
                    paddingValue = paddingValue,
                    onButtonLipidProfileAssessmentClick = onButtonLipidProfileAssessmentClick,
                    onButtonIndividualSelectionTherapyClick = onButtonIndividualSelectionTherapyClick,
                )
            }
            if (selectedItemNavigationBar == 1) {

            }
            if (selectedItemNavigationBar == 2) {
            }
        },
        bottomBar = {
            NavigationBarSample(selectedItemNavigationBar,
                onClickNavBar = { i ->
                    selectedItemNavigationBar = i
                })
        }
    )
}

@Composable
private fun MainContent(
    paddingValue: PaddingValues,
    onButtonLipidProfileAssessmentClick: () -> Unit,
    onButtonIndividualSelectionTherapyClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(30.dp))
                Image(
                    modifier = Modifier.size(300.dp, 180.dp),
                    painter = painterResource(id = R.drawable.image_logo),
                    contentDescription = null
                )
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
                ButtonLipidOk(
                    onClick = onButtonLipidProfileAssessmentClick,
                    text = "Оценка липидного профиля"
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ButtonLipidOk(
                    onClick = onButtonIndividualSelectionTherapyClick,
                    text = "Индивидуальный подбор терапии"
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ButtonLipidOk(onClick = {}, text = "Данные липидного профиля")
            }
        }


    }
}

@Composable
fun NavigationBarSample(
    selectedItemNavigationBar: Int,
    onClickNavBar: (Int) -> Unit,
) {

    val items = listOf("Пациент", "База данных", "Общее")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Star, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItemNavigationBar == index,
                onClick = { onClickNavBar(index) }
            )
        }
    }
}
