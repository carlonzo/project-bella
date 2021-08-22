package com.projectbella.renderer.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skija.Image
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun fetchImage(url: String): ImageBitmap? {
	var image by remember(url) { mutableStateOf<ImageBitmap?>(null) }

	LaunchedEffect(url) {
		loadFullImage(url)?.let {
			image = Image.makeFromEncoded(it).asImageBitmap()
		}
	}

	return image
}

private suspend fun loadFullImage(source: String): ByteArray? = withContext(Dispatchers.IO) {
	runCatching {
		val url = URL(source)
		val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
		connection.connectTimeout = 5000
		connection.connect()

		val input: InputStream = connection.inputStream
		input.use { it.readBytes() }
	}.getOrNull()
}