package it.carlom.feature_a

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parser.models.ModifierComponent
import com.example.parser.models.TextStyleModifier

fun ModifierComponent?.toModifier(): Modifier {
    if (this == null) return Modifier

    var initial: Modifier = Modifier
        .wrapContentHeight(align = Alignment.Top)
        .wrapContentWidth(align = Alignment.Start)

    width?.let { initial = initial.preferredWidth(it.dp) }
    height?.let { initial = initial.preferredHeight(it.dp) }
    fillWidth?.let { initial = initial.fillMaxWidth() }
    fillHeight?.let { initial = initial.fillMaxHeight() }
    paddingAll?.let { initial = initial.padding(it.dp) }

    return initial
}

fun TextStyleModifier?.toTextStyle(): TextStyle {
    if (this == null) return TextStyle()

    return TextStyle(
        color = color.toColor(),
        fontSize = size?.sp ?: TextUnit.Inherit
    )
}

fun String?.toColor(): Color {
    if (this == null) return Color.Unspecified
    if (this.length > 8) throw IllegalArgumentException("Color string cant be longer than 8 chars. this should be ARGB format in hex")
    if (this.length % 2 != 0) throw IllegalArgumentException("Color string cant have an odd number of characters")

    val formatted = if (this.length < 8) {
        "f".repeat(8 - this.length) + this
    } else {
        this
    }

    return Color(formatted.toLong(16))
}


@OptIn(InternalLayoutApi::class)
fun toHorizontalArrangement(horizontalArrangement: String?): Arrangement.Horizontal {
    return when (horizontalArrangement) {
        "SPACE_EVENLY" -> Arrangement.SpaceEvenly
        "SPACE_AROUND" -> Arrangement.SpaceAround
        "CENTER" -> Arrangement.Center
        "START" -> Arrangement.Start
        "END" -> Arrangement.End
        else -> Arrangement.Start
    }
}

@OptIn(InternalLayoutApi::class)
fun toVerticalArrangement(verticalArrangement: String?): Arrangement.Vertical {
    return when (verticalArrangement) {
        "SPACE_EVENLY" -> Arrangement.SpaceEvenly
        "SPACE_AROUND" -> Arrangement.SpaceAround
        "CENTER" -> Arrangement.Center
        "BOTTOM" -> Arrangement.Bottom
        "TOP" -> Arrangement.Top
        else -> Arrangement.Top
    }
}