package it.carlom.feature_a.models

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun ModifierComponent?.toModifier(): Modifier {
    return this?.createModifier() ?: Modifier
}

fun TextModifier?.toTextStyle(): TextStyle {

}

fun String?.toColor(): Color {
    if (this == null) return Color.Unspecified
    if (this.length > 8) throw IllegalArgumentException("Color string cant be longer than 8 chars. this should be ARGB format in hex")

    val formatted = if (this.length < 8) {
        "f".repeat(8 - this.length) + this
    } else {
        this
    }

    return Color(formatted.toLong(16))
}

open class ModifierComponent(
    open val preferredWidth: Float? = null,
    open val preferredHeight: Float? = null,
    open val fillWidth: Boolean? = null,
    open val fillHeight: Boolean? = null,
    open val padding: Float? = null
) {
    internal open fun createModifier(): Modifier {

        var initial: Modifier = Modifier

        preferredWidth?.let { initial = initial.preferredWidth(it.dp) }
        preferredHeight?.let { initial = initial.preferredHeight(it.dp) }
        fillWidth?.let { initial = initial.fillMaxWidth() }
        fillHeight?.let { initial = initial.fillMaxHeight() }
        padding?.let { initial = initial.padding(it.dp) }

        return initial.preferredWidth(preferredWidth?.dp ?: 0.dp)
    }
}

class TextModifier(
//    val font: String? = null,
//    val fontWeight: String? = null,
    val color: String? = null,
    val textSize: Float? = null,
) : ModifierComponent() {

    internal open fun createTextStyle(): TextStyle {
        return TextStyle(
            color = color.toColor(),
            fontSize = textSize?.sp ?: TextUnit.Inherit
        )
    }

}

class RowModifier(
    val horizontalArrangement: ArrangementModifier? = null,
    val verticalArrangement: ArrangementModifier? = null,
) : ModifierComponent()

class ColumnModifier(
    val horizontalArrangement: ArrangementModifier? = null,
    val verticalArrangement: ArrangementModifier? = null,
) : ModifierComponent()

enum class ArrangementModifier {
    SPACE_EVENLY, SPACE_AROUND, CENTER, START, BOTTOM, END, TOP
}

enum class AlignmentModifier {
    CENTER, CENTER_HORIZONTALLY, CENTER_VERTICALLY, START, END,
}