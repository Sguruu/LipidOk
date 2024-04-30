package com.sguru.lipidok.presentation.ui.screen.MainScreen

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.domain.model.LipidProfileModel
import com.sguru.lipidok.domain.model.PatientModel
import com.sguru.lipidok.presentation.ui.component.GrayLineLipidOk
import com.sguru.lipidok.presentation.ui.component.OutlinedTextFieldOutputLipidOk
import com.sguru.lipidok.presentation.ui.component.Text14LipidOk
import com.sguru.lipidok.presentation.ui.component.Text16LipidOk
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString

@Composable
internal fun GeneralContent(
    paddingValue: PaddingValues,
    appVersion: String,
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
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(36.dp))
                Image(
                    modifier = Modifier.size(300.dp, 180.dp),
                    painter = painterResource(id = R.drawable.image_logo),
                    contentDescription = null
                )
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
                Text20LipidOk(text = "Версия приложения : $appVersion")
            }
            item {
                val deviceModel = Build.MODEL
                Spacer(modifier = Modifier.height(36.dp))
                Text20LipidOk(text = "Модель устройства : $deviceModel")
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
                GrayLineLipidOk()
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
                Text20LipidOk(text = "Часто задаваемые вопросы", bold = true)
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
                ExpandableText(
                    title = "Что такое гиперлипидемия?",
                    description = "Гиперлипидемия – это повышенное содержание липидов в крови."
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExpandableText(
                    title = "Чем опасна гиперлипидемия, особенно в детском возрасте?",
                    description = "Гиперлипидемия является основной причиной " +
                            "развития атеросклероза, что при старте процесса в " +
                            "детском возрасте приводит к ишемической болезни сердца, а " +
                            "также ранним инфарктам и инсультам. "
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExpandableText(
                    title = "Что такое семейная гиперлипидемия?",
                    description = "Семейная форма гиперлипидемии – патологически повышенное " +
                            "содержание липидов в крови, обусловленное мутациями в генах."
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExpandableText(
                    title = "Что такое гетерозиготная и гомозиготная формы гиперлипидемии?",
                    description = "У человека двойной набор генов, отвечающих за синтез " +
                            "одного белка. В случае, когда мутация происходит в одном из " +
                            "них – заболевание называется гетерозиготной и протекает в " +
                            "более легкой форме. В случае, когда мутация в обоих генах – " +
                            "гиперлипидемия является гомозиготной и протекает тяжелее."
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExpandableText(
                    title = "Почему различают «хороший» и «плохой» холестерин?",
                    description = "Общий холестерин подразделяется на фракции: хиломикроны, а " +
                            "также липопротеиды различной плотности. Чем ниже плотность ЛП, тем " +
                            "больше они способны ускорять образование атеросклеротических бляшек. "
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExpandableText(
                    title = "Кто занимается проблемами гиперлипидемии у детей?",
                    description = "Гиперлипидемию может диагностировать и лечить " +
                            "врач-липидолог и врач-кардиолог, однако первичным скринингом " +
                            "должен заниматься врач-педиатр участковый."
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                ExpandableText(
                    title = "Как можно диагностировать гиперлипидемию?",
                    description = "Обнаружить гиперлипидемию можно в ходе скрининга с " +
                            "использованием холестерометра и тест полосок, а также " +
                            "специализированного анализа крови на показатели липидного профиля."
                )
            }
            item {
                Spacer(modifier = Modifier.height(36.dp))
                Text20LipidOk(text = "Форма для обратной связи")
                Spacer(modifier = Modifier.height(16.dp))
                val context = LocalContext.current
                val clipboardManager = LocalClipboardManager.current
                Text20LipidOk(
                    text = "lipidok@list.ru",
                    modifier = Modifier.clickable {
                        clipboardManager.setText(AnnotatedString("lipidok@list.ru"))
                        Toast.makeText(context, "Данные скопированы", Toast.LENGTH_SHORT).show()
                    })
                Spacer(modifier = Modifier.height(36.dp))
            }
        }
    }
}

@Composable
fun ExpandableText(
    title: String,
    description: String,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        Modifier.fillMaxWidth()
    ) {
        Text16LipidOk(
            text = title,
            textAlign = TextAlign.Start,
            bold = true,
            modifier = Modifier.clickable { expanded = !expanded })

        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text16LipidOk(
                text = description,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}