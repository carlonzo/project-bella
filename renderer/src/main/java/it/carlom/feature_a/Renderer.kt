@file:Suppress("RemoveRedundantQualifierName")

package it.carlom.feature_a

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.parser.models.*
import com.google.accompanist.glide.rememberGlidePainter
import it.carlom.feature_a.modifiers.toColor
import it.carlom.feature_a.modifiers.toHorizontalArrangement
import it.carlom.feature_a.modifiers.toModifier
import it.carlom.feature_a.modifiers.toTextStyle

@SuppressLint("ComposableNaming")
object Renderer {

    @Composable
    fun <C : Component> render(component: C) {

        when (component) {

            is EmptyContent -> return
//            is RowComponent -> renderRow()
//            is ColumnComponent -> renderColumn(component)
            is TextComponent -> renderText(component)
            is CardComponent -> renderCard(component)
            is TextButtonComponent -> renderTextButton(component)
            is ImageComponent -> renderImage(component)
            is SpacerComponent -> renderSpacer(component)
//            is VerticalScrollComponent -> renderVerticalScroll(component)
            is HorizontalScrollComponent -> RenderHorizontalScroll()

            else -> throw IllegalStateException("Component $component does not have a renderer")

        }

    }


    @Composable
    fun renderImage(component: ImageComponent) {
        val shape = if (component.cornerRadius != null) {
            RoundedCornerShape(percent = component.cornerRadius!!)
        } else {
            MaterialTheme.shapes.small
        }

        Image(
            painter = rememberGlidePainter(request = component.content ?: ""),
            contentDescription = null,
            modifier = component.modifier
                .toModifier()
                .clip(shape = shape)
                .testTag("GlideImage")
        )
    }

    @Composable
    fun renderText(text: TextComponent) {
        Text(
            text = text.content,
            style = text.textStyle.toTextStyle(),
            modifier = text.modifier
                .toModifier()
                .testTag("Text")
        )
    }

//    @Composable
//    fun renderRow() {
//
//        androidx.compose.foundation.layout.Row(
//            modifier = Modifier.testTag("Row"),
//            horizontalArrangement = Arrangement.Start
//        ) {
//
//            render(component = EmptyContent)
////            row.content.forEach {
////                render(component = it)
////            }
//
//        }
//
//    }

//    @Composable
//    fun renderColumn(column: ColumnComponent) {
//
//        androidx.compose.foundation.layout.Column(
//            modifier = column.modifier
//                .toModifier()
//                .testTag("Column")
//        ) {
//
//            column.content.forEach {
//                render(component = it)
//            }
//
//        }
//
//    }

//    @Composable
//    fun renderVerticalScroll(verticalScroll: VerticalScrollComponent) {
//
//        androidx.compose.foundation.layout.Column(
//            modifier = verticalScroll.modifier
//                .toModifier()
//                .verticalScroll(rememberScrollState())
//                .testTag("LazyColumn"),
//        ) {
//            verticalScroll.content.forEach {
//                render(component = it)
//            }
//        }
//
//    }

    @Composable
    fun RenderHorizontalScroll() {

        androidx.compose.foundation.layout.Row() {

        }

    }

    @Composable
    fun renderCard(card: CardComponent) {

        Card(
            modifier = card.modifier
                .toModifier()
                .testTag("Card"),
            elevation = card.elevation.dp
        ) {
            render(component = card.content)
        }

    }

    @Composable
    fun renderTextButton(textButton: TextButtonComponent) {

        val colors = if (textButton.backgroundColor != null) {
            ButtonDefaults.textButtonColors(
                backgroundColor = textButton.backgroundColor.toColor()
            )
        } else {
            ButtonDefaults.textButtonColors()
        }

        val shape = if (textButton.cornerRadius != null) {
            RoundedCornerShape(percent = textButton.cornerRadius!!)
        } else {
            MaterialTheme.shapes.small
        }

        TextButton(
            modifier = textButton.modifier
                .toModifier()
                .testTag("TextButton"),
            onClick = {},
            colors = colors,
            shape = shape
        ) {
            textButton.content.forEach {
                render(it)
            }
        }

    }

    @Composable
    fun renderSpacer(spacer: SpacerComponent) {

        val modifier = spacer.modifier.let { spacerModifier ->
            var modifier: Modifier = Modifier
            spacerModifier.width?.let { modifier = modifier.width(it.dp) }
            spacerModifier.height?.let { modifier = modifier.height(it.dp) }
            modifier
        }

        Spacer(
            modifier = modifier.testTag("Spacer"),
        )

    }

}