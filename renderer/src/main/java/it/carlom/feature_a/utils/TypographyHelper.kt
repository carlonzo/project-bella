package it.carlom.feature_a.utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import it.carlom.feature_a.R

object TypographyHelper {

    val fontFamilies: Map<String, FontFamily> = mutableMapOf(

        "inter" to fontFamily(
            font(R.font.inter_regular, FontWeight.Normal),
            font(R.font.inter_medium, FontWeight.Medium),
            font(R.font.inter_semibold, FontWeight.SemiBold),
            font(R.font.inter_bold, FontWeight.Bold)
        )

    )

}


