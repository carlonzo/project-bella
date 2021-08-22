package com.projectbella.renderer.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun ImagePlatform(
	source: String,
	modifier: Modifier
) {

	fetchImage(source)?.let {
		Image(
			bitmap = it,
			contentDescription = null,
			modifier = modifier
		)
	}

}