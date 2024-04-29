package com.sguru.lipidok.presentation.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.sguru.lipidok.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBarLipidOk(
    @StringRes stringResTitle: Int? = null,
    isNavigationIconClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResTitle?.let {
                    stringResource(id = stringResTitle)
                } ?: "",
            )
        },
        navigationIcon = if (isNavigationIconClick != null) {
            {
                Icon(
                    modifier = Modifier.clickable {
                        isNavigationIconClick()
                    },
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = null
                )
            }
        } else {
            { }
        }
    )
}