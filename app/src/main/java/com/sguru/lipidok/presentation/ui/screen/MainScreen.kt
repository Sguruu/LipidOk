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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.navigation.NavigationState

@Composable
internal fun MainScreen(
    isNavigationIconClick: () -> Unit,
    onButtonLipidProfileAssessmentClick: () -> Unit,
    onButtonIndividualSelectionTherapyClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            MainContent(
                paddingValue = paddingValue,
                onButtonLipidProfileAssessmentClick = onButtonLipidProfileAssessmentClick,
                onButtonIndividualSelectionTherapyClick = onButtonIndividualSelectionTherapyClick,
            )
        },
        bottomBar = {
            NavigationBarSample()
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
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            modifier = Modifier.size(300.dp, 180.dp),
            painter = painterResource(id = R.drawable.image_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(80.dp))
        ButtonLipidOk(
            onClick = onButtonLipidProfileAssessmentClick,
            text = "Оценка липидного профиля"
        )
        Spacer(modifier = Modifier.height(32.dp))
        ButtonLipidOk(
            onClick = onButtonIndividualSelectionTherapyClick,
            text = "Индивидуальный подбор терапии"
        )
        Spacer(modifier = Modifier.height(32.dp))
        ButtonLipidOk(onClick = {}, text = "Данные липидного профиля")

    }

}

@Composable
fun NavigationBarSample() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Пациент", "База данных", "Общее")

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Star, contentDescription = item) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}
