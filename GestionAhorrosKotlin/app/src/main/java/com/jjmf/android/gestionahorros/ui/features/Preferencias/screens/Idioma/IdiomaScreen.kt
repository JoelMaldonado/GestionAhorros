package com.jjmf.android.gestionahorros.ui.features.Preferencias.screens.Idioma

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jjmf.android.gestionahorros.R
import com.jjmf.android.gestionahorros.ui.theme.ColorFondo
import com.jjmf.android.gestionahorros.ui.theme.ColorP1
import com.jjmf.android.gestionahorros.ui.theme.ColorP3
import com.jjmf.android.gestionahorros.ui.theme.TextSubTitle
import java.util.Currency
import java.util.Locale


@Composable
fun IdiomaScreen(
    update: () -> Unit,
    back: () -> Unit,
) {

    val context = LocalContext.current

    val idiomas = listOf(
        Locale("es", "PE"),
        Locale("es", "CL"),
        Locale("es", "CO"),
        Locale("es", "VE"),
        Locale("es", "EC"),
        Locale.US
    )

    val idiomaSelect = Locale.getDefault()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorFondo)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = back) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIos,
                    contentDescription = null,
                    tint = ColorP1
                )
            }


            Text(text = stringResource(id = R.string.idioma), style = TextSubTitle, color = ColorP1)
        }

        idiomas.forEach { idioma ->
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    updateLocale(context, idioma)
                    update()
                },
                colors = CardDefaults.cardColors(
                    containerColor = if (idiomaSelect == idioma) ColorP1 else Color.White,
                    contentColor = if (idiomaSelect == idioma) Color.White else Color.Black,
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = idioma.displayCountry
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = Currency.getInstance(idioma).symbol)
                }
            }
        }
    }
}

fun updateLocale(context: android.content.Context, locale: Locale) {
    Locale.setDefault(locale)
    val configuration = context.resources.configuration
    configuration.setLocale(locale)
    context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
}