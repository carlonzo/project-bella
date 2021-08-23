package com.projectbella.renderer.modifiers

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.projectbella.parser.models.TextStyleModifier
import com.projectbella.renderer.utils.TypographyHelper

fun TextStyleModifier?.toTextStyle(): TextStyle {
	if (this == null) return TextStyle()

	return TextStyle(
		fontFamily = font.toFontFamily(),
		fontWeight = fontWeight.toFontWeight(),
		color = color.toColor(),
		fontSize = size?.sp ?: TextUnit.Unspecified,
		lineHeight = lineHeight?.sp ?: TextUnit.Unspecified
	)
}

private fun String?.toFontWeight(): FontWeight? {
	if (this == null) return null
	return when (this) {
		"thin" -> FontWeight.Thin
		"extralight" -> FontWeight.ExtraLight
		"light" -> FontWeight.Light
		"normal" -> FontWeight.Normal
		"medium" -> FontWeight.Medium
		"semibold" -> FontWeight.SemiBold
		"bold" -> FontWeight.Bold
		"extrabold" -> FontWeight.ExtraBold
		"black" -> FontWeight.Black
		else -> FontWeight.Normal
	}
}

private fun String?.toFontFamily(): FontFamily? {
	if (this == null) return null
	return TypographyHelper.fontFamilies[this]
		?: kotlin.run {
			println("Renderer, FontFamily $this is not available")
			null
		}
}