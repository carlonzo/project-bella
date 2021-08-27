package com.projectbella.renderer.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberImagePainter
import com.projectbella.renderer.Renderer
import com.projectbella.renderer.modifiers.toColor
import com.projectbella.renderer.modifiers.toModifier

@Composable
actual fun ImagePlatform(
	source: String,
	modifier: Modifier
) {
	Image(
		painter = rememberImagePainter(source),
		modifier = modifier,
		contentDescription = null
	)
}