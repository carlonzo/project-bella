package it.carlom.feature_a.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.WithConstraints
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun GlideImage(
    model: Any,
    onImageReady: (() -> Unit)? = null,
    preferredSize: Pair<Dp, Dp>? = null,
    customize: RequestBuilder<Bitmap>.() -> RequestBuilder<Bitmap> = { this }
) {

    var image by remember { mutableStateOf<ImageBitmap?>(null) }
    var drawable by remember { mutableStateOf<Drawable?>(null) }
    var size by remember { mutableStateOf(Pair(0, 0)) }
    val context = AmbientContext.current

    WithConstraints {
        onCommit(model, {

            if (preferredSize != null) {
                val widthPixel = preferredSize.first.toPx
                val heightPixel = preferredSize.second.toPx
                size = Pair(widthPixel, heightPixel)
            } else {
                val constraintWidth =
                    if (constraints.maxWidth > 0 && constraints.maxWidth < Int.MAX_VALUE) {
                        constraints.maxWidth
                    } else {
                        constraints.minWidth
                    }

                val constraintHeight =
                    if (constraints.maxHeight > 0 && constraints.maxHeight < Int.MAX_VALUE) {
                        constraints.maxHeight
                    } else {
                        constraints.minHeight
                    }

                size = Pair(constraintWidth, constraintHeight)
            }

            val glide = kotlin.runCatching { Glide.with(context) }.getOrNull() ?: run {
                drawable = ShapeDrawable().apply {
                    shape = RectShape()
                    intrinsicWidth = size.first
                    intrinsicHeight = size.second
                    paint.color = Color.GREEN
                }
                return@onCommit
            }

            var target: CustomTarget<Bitmap>? = null

            val job = CoroutineScope(Dispatchers.Main).launch {
                val customTarget = object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {
                        image = null
                        drawable = placeholder
                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        image = resource.asImageBitmap()
                        onImageReady?.invoke()
                    }
                }

                target = customTarget

                glide
                    .asBitmap()
                    .load(model)
                    .apply(
                        RequestOptions.overrideOf(size.first, size.second)
                    )
                    .let(customize)
                    .into(customTarget)
            }

            onDispose {
                image = null
                drawable = null
                target?.let { glide.clear(it) }
                job.cancel()
            }
        })

        val theImage = image
        val theDrawable = drawable
        when {
            theImage != null -> {
                Image(
                    modifier = Modifier.preferredSize(size.first.pixelToDp, size.second.pixelToDp)
                        .testTag("GlideImage"), bitmap = theImage
                )
            }
            theDrawable != null -> {
                Canvas(
                    modifier = Modifier.preferredSize(size.first.pixelToDp, size.second.pixelToDp)
                        .testTag("GlideImage-Canvas")
                ) {
                    drawIntoCanvas { canvas -> theDrawable.draw(canvas.nativeCanvas) }
                }
            }
        }
    }


}

private val Dp.toPx: Int
    get() = (this.value * Resources.getSystem().displayMetrics.density).toInt()

private val Int.pixelToDp: Dp
    get() = (this / Resources.getSystem().displayMetrics.density).dp
