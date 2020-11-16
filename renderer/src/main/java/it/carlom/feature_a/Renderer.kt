@file:Suppress("RemoveRedundantQualifierName")

package it.carlom.feature_a

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parser.models.*
import it.carlom.feature_a.utils.GlideImage


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

        GlideImage(model = component.content ?: "") {
            if (component.modifier.width != null && component.modifier.height != null) {
                override(component.modifier.width!!, component.modifier.height!!)
            }
            this
        }

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

        TextButton(
            modifier = textButton.modifier.toModifier().testTag("TextButton"),
            onClick = {},
        ) {
            textButton.content.forEach {
                render(it)
            }
        }

    }

    @Composable
    fun renderSpacer(spacer: SpacerComponent) {

        Spacer(
            modifier = spacer.modifier.toModifier().testTag("Spacer"),
        )

    }


    @Composable
    fun renderCareemTile(careemTile: CareemTileComponent) {

        Card(modifier = Modifier.preferredHeight(100.dp).padding(4.dp)) {

            Column(horizontalAlignment = CenterHorizontally) {

                Box(modifier = Modifier.preferredSize(80.dp, 52.dp)) {
                    GlideImage(careemTile.image)
                }

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