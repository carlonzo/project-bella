package com.projectbella.renderer.modifiers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.projectbella.parser.models.ModifierComponent

fun ModifierComponent.toModifier(): Modifier {

	var initial: Modifier = Modifier
		.wrapContentHeight(align = Alignment.Top)
		.wrapContentWidth(align = Alignment.Start)

	width?.let { initial = initial.width(it.dp) }
	height?.let { initial = initial.height(it.dp) }
	fillWidth?.let { initial = initial.fillMaxWidth() }
	fillHeight?.let { initial = initial.fillMaxHeight() }
	paddingAll?.let { initial = initial.padding(it.dp) }
	return initial
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