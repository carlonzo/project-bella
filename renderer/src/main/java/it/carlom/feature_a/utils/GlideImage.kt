package it.carlom.feature_a.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.asImageAsset
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.testTag
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GlideImage(
    model: Any,
    onImageReady: (() -> Unit)? = null,
    customize: RequestBuilder<Bitmap>.() -> RequestBuilder<Bitmap> = { this }
) {
    WithConstraints {

        val image = mutableStateOf<ImageAsset?>(null)
        val drawable = mutableStateOf<Drawable?>(null)
        val context = ContextAmbient.current

        onCommit(model, {

            val width =
                if (constraints.maxWidth > 0 && constraints.maxWidth < Int.MAX_VALUE) {
                    constraints.maxWidth
                } else {
                    SIZE_ORIGINAL
                }

            val height =
                if (constraints.maxHeight > 0 && constraints.maxHeight < Int.MAX_VALUE) {
                    constraints.maxHeight
                } else {
                    SIZE_ORIGINAL
                }


            val glide = kotlin.runCatching { Glide.with(context) }.getOrNull() ?: run {
                drawable.value = ShapeDrawable().apply {
                    shape = RectShape()
                    intrinsicHeight = height
                    intrinsicWidth = width
                    paint.color = Color.GREEN
                }
                return@onCommit
            }

            var target: CustomTarget<Bitmap>? = null

            val job = CoroutineScope(Dispatchers.Main).launch {
                val customTarget = object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        image.value = null
                        drawable.value = placeholder
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        FrameManager.ensureStarted()
                        image.value = resource.asImageAsset()
                        onImageReady?.invoke()
                    }
                }

                target = customTarget

                glide
                    .asBitmap()
                    .load(model)
                    .override(width, height)
                    .let(customize)
                    .into(customTarget)
            }

            onDispose {
                image.value = null
                drawable.value = null
                target?.let {  glide.clear(it)}
                job.cancel()
            }
        })

        val theImage = image.value
        val theDrawable = drawable.value
        when {
            theImage != null -> {
                Image(modifier = Modifier.testTag("GlideImage"), asset = theImage)
            }
            theDrawable != null -> {
                Canvas(modifier = Modifier.fillMaxSize().testTag("GlideImage-Canvas")) {
                    drawIntoCanvas { canvas -> theDrawable.draw(canvas.nativeCanvas) }
                }
            }
        }
    }
}