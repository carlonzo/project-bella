package com.projectbella.renderer.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter

@Composable
actual fun ImagePlatform(
	source: String,
	modifier: Modifier
){
	Image(
		painter = rememberImagePainter(source),
		modifier= modifier,
		contentDescription = null
	)
}