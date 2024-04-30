package com.sguru.lipidok.presentation.ui.screen

import android.os.ParcelFileDescriptor
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sguru.lipidok.R
import com.sguru.lipidok.presentation.ui.component.TopBarLipidOk
import com.sguru.lipidok.presentation.util.PdfRender
import java.io.File

@Composable
internal fun PdfReaderScreen(
    isNavigationIconClick: () -> Unit,
    @RawRes rawRes: Int,
) {
    Screen(
        isNavigationIconClick = isNavigationIconClick,
        rawRes,
    )
}

@Composable
private fun Screen(
    isNavigationIconClick: () -> Unit,
    @RawRes rawRes: Int,
) {
    Scaffold(
        topBar = {
            TopBarLipidOk(
                stringResTitle = R.string.PatientMainShoolScreen,
                isNavigationIconClick = isNavigationIconClick
            )
        },
        content = { paddingValue ->
            Content(rawRes = rawRes, paddingValue = paddingValue)
        },
    )
}

@Composable
private fun Content(
    @RawRes rawRes: Int,
    paddingValue: PaddingValues,
) {
    val context = LocalContext.current
    val pdfFile = remember {
        val file = File.createTempFile("article", "pdf")
        val inputStream = context.resources.openRawResource(rawRes)
        file.outputStream().use { fileOut ->
            inputStream.copyTo(fileOut)
        }
        file
    }
    PDFReader(file = pdfFile, paddingValue = paddingValue)
}

@Composable
fun PDFReader(
    file: File,
    paddingValue: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue)
    ) {
        val pdfRender = PdfRender(
            fileDescriptor = ParcelFileDescriptor.open(
                file,
                ParcelFileDescriptor.MODE_READ_ONLY
            )
        )
        DisposableEffect(key1 = Unit) {
            onDispose {
                pdfRender.close()
            }
        }
        LazyColumn {
            items(count = pdfRender.pageCount) { index ->
                BoxWithConstraints(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val page = pdfRender.pageLists[index]
                    DisposableEffect(key1 = Unit) {
                        page.load()
                        onDispose {
                            page.recycle()
                        }
                    }
                    page.pageContent.collectAsState().value?.asImageBitmap()?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "Pdf page number: $index",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.FillWidth
                        )
                    } ?: Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                page.heightByWidth(constraints.maxWidth)
                                    .dp
                            )
                    )
                }
            }
        }
    }
}