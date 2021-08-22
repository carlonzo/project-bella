package com.projectbella.renderer.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun ImagePlatform(
	source: String,
	modifier: Modifier
)