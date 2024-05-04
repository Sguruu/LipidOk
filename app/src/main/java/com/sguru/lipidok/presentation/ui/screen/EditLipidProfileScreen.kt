package com.sguru.lipidok.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.presentation.ui.component.ButtonLipidOk
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldFloatLipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.ui.model.ScreenEvent

@Composable
internal fun EditLipidProfileScreen(
    isNavigationIconClick: () -> Unit,
    lipidProfile: LipidProfileModel,
    onEvent: (ScreenEvent) -> Unit,
    onSaveButtonClick: () -> Unit,
) {

    var newLipidProfile by remember { mutableStateOf(lipidProfile) }
    Screen(
        lipidProfile = newLipidProfile,
        isNavigationIconClick = isNavigationIconClick,
        onSaveButtonClick = {
            onEvent(ScreenEvent.EditLipidProfile.OnButtonClickedSave(newLipidProfile))
            onSaveButtonClick.invoke()
        },
        updateLipidProfileModel = {
            newLipidProfile = it
        },
    )

}

@Composable
private fun Screen(
    lipidProfile: LipidProfileModel,
    isNavigationIconClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
    updateLipidProfileModel: (LipidProfileModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.EditLipidProfileScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            Content(
                paddingValue = paddingValue,
                lipidProfile = lipidProfile,
                updateLipidProfileModel = updateLipidProfileModel
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                ButtonLipidOk(
                    onClick = onSaveButtonClick,
                    text = "Сохранить"
                )
            }
        },
    )
}

@Composable
private fun Content(
    paddingValue: PaddingValues,
    lipidProfile: LipidProfileModel,
    updateLipidProfileModel: (LipidProfileModel) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text20LipidOk(
                    text = "Данные липидного профиля",
                    textAlign = TextAlign.Center
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    defaultValue = lipidProfile.cholesterol,
                    labelText = "Общий холестерин, ммоль/л",
                    onValueChange = {
                        val newLipidProfile = lipidProfile.copy(
                            cholesterol = it.toString()
                        )
                        updateLipidProfileModel(newLipidProfile)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    defaultValue = lipidProfile.lpnp,
                    labelText = "ЛПНП, ммоль/л",
                    onValueChange = {
                        val newLipidProfile = lipidProfile.copy(
                            lpnp = it.toString()
                        )
                        updateLipidProfileModel(newLipidProfile)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    defaultValue = lipidProfile.lpvp,
                    labelText = "ЛПВП, ммоль/л",
                    onValueChange = {
                        val newLipidProfile = lipidProfile.copy(
                            lpvp = it.toString()
                        )
                        updateLipidProfileModel(newLipidProfile)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    defaultValue = lipidProfile.triglycerols,
                    labelText = "Триглицериды, ммоль/л",
                    onValueChange = {
                        val newLipidProfile = lipidProfile.copy(
                            triglycerols = it.toString()
                        )
                        updateLipidProfileModel(newLipidProfile)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextFieldFloatLipidOk(
                    defaultValue = lipidProfile.atherogenicIndex,
                    labelText = "Индекс атерогенности",
                    onValueChange = {
                        val newLipidProfile = lipidProfile.copy(
                            atherogenicIndex = it.toString()
                        )
                        updateLipidProfileModel(newLipidProfile)
                    }
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}