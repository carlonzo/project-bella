@file:Suppress("RemoveRedundantQualifierName")

package it.carlom.feature_a

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonConstants.defaultTextButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parser.models.*
import dev.chrisbanes.accompanist.glide.GlideImage
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
            is RowComponent -> renderRow(component)
            is ColumnComponent -> renderColumn(component)
            is TextComponent -> renderText(component)
            is CardComponent -> renderCard(component)
            is TextButtonComponent -> renderTextButton(component)
            is ImageComponent -> renderImage(component)
            is SpacerComponent -> renderSpacer(component)
            is VerticalScrollComponent -> renderVerticalScroll(component)
            is HorizontalScrollComponent -> renderHorizontalScroll(component)
            is CareemTileComponent -> renderCareemTile(component)

            else -> throw IllegalStateException("Component $component does not have a renderer")

        }

    }

    @Composable
    fun renderImage(component: ImageComponent) {
        GlideImage(
            data = component.content ?: "",
            modifier = component.modifier.toModifier().testTag("GlideImage")
        )
    }

    @Composable
    fun renderText(text: TextComponent) {
        Text(
            text = text.content,
            style = text.textStyle.toTextStyle(),
            modifier = text.modifier.toModifier().testTag("Text")
        )
    }

    @Composable
    fun renderRow(row: RowComponent) {

        androidx.compose.foundation.layout.Row(
            modifier = row.modifier.toModifier().testTag("Row"),
            horizontalArrangement = toHorizontalArrangement(row.arrangement)
        ) {

            row.content.forEach {
                render(component = it)
            }

        }

    }

    @Composable
    fun renderColumn(column: ColumnComponent) {

        androidx.compose.foundation.layout.Column(
            modifier = column.modifier.toModifier().testTag("Column")
        ) {

            column.content.forEach {
                render(component = it)
            }

        }

    }

    @Composable
    fun renderVerticalScroll(verticalScroll: VerticalScrollComponent) {

        ScrollableColumn(
            modifier = verticalScroll.modifier.toModifier().testTag("ScrollableColumn")
        ) {

            verticalScroll.content.forEach {
                render(component = it)
            }

        }

    }

    @Composable
    fun renderHorizontalScroll(horizontalScroll: HorizontalScrollComponent) {

        ScrollableRow(
            modifier = horizontalScroll.modifier.toModifier().testTag("ScrollableRow")
        ) {
            horizontalScroll.content.forEach {
                render(component = it)
            }
        }

    }

    @Composable
    fun renderCard(card: CardComponent) {

        Card(
            modifier = card.modifier.toModifier().testTag("Card"),
            elevation = card.elevation.dp
        ) {
            render(component = card.content)
        }

    }

    @Composable
    fun renderTextButton(textButton: TextButtonComponent) {

        val colors = if (textButton.backgroundColor != null) {
            defaultTextButtonColors(
                backgroundColor = textButton.backgroundColor.toColor()
            )
        } else {
            ButtonConstants.defaultTextButtonColors()
        }

        val shape = if (textButton.cornerRadius != null) {
            RoundedCornerShape(percent = textButton.cornerRadius!!)
        } else {
            MaterialTheme.shapes.small
        }

        TextButton(
            modifier = textButton.modifier.toModifier().testTag("TextButton"),
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


    @Composable
    fun renderCareemTile(careemTile: CareemTileComponent) {

        Card(modifier = Modifier.preferredHeight(100.dp).padding(4.dp)) {

            Column(horizontalAlignment = CenterHorizontally) {

                GlideImage(careemTile.image, modifier = Modifier.preferredSize(80.dp, 52.dp))

                Text(
                    modifier = Modifier.align(CenterHorizontally),
                    text = careemTile.text,
                    style = TextStyle(
                        color = Color(0xff2d2e2e), //black100
                        fontSize = 14.sp
                    )
                )
            }

        }


    }
}