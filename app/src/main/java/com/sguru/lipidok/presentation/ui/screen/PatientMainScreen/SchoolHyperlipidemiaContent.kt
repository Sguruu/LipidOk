package com.sguru.lipidok.presentation.ui.screen.PatientMainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.Text20LipidOk
import com.sguru.lipidok.presentation.ui.theme.Wave

@Composable
internal fun SchoolHyperlipidemiaContent(
    paddingValue: PaddingValues,
    onClickArticle: (Int) -> Unit,
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
                Spacer(modifier = Modifier.height(32.dp))
                ArticleBox(
                    articleTitle = "Липиды, их строение и функции",
                    onClickArticle = {
                        onClickArticle(R.raw.article_1)
                    },
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ArticleBox(
                    articleTitle = "Гиперлипидемия: формы, проявления и диагностика",
                    onClickArticle = {
                        onClickArticle(R.raw.article_2)
                    },
                )
            }
            item {
                Spacer(modifier = Modifier.height(32.dp))
                ArticleBox(
                    articleTitle = "Приобретенная гиперлипидемия",
                    onClickArticle = {
                        onClickArticle(R.raw.article_3)
                    },
                )
            }
        }
    }
}


@Composable
fun ArticleBox(
    articleTitle: String,
    onClickArticle: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(Wave)
            .sizeIn(minHeight = 34.dp)
            .clickable {
                onClickArticle.invoke()
            },
    ) {
        Text20LipidOk(
            text = articleTitle,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(min = 24.dp),
            color = Color.White
        )
    }
}