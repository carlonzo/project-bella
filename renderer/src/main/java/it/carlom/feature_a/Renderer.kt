@file:Suppress("RemoveRedundantQualifierName")

package it.carlom.feature_a

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.carlom.feature_a.models.*
import it.carlom.feature_a.utils.GlideImage


object Renderer {

    @Composable
    fun render(component: Component) {

        when (component) {

            is EmptyContent -> return
            is RowComponent -> renderRow(component)
            is ColumnComponent -> renderColumn(component)
            is VerticalScrollComponent -> renderVerticalScroll(component)
            is BoxComponent -> renderBox(component)
            is CareemTileComponent -> renderCareemTile(component)
            is CardComponent -> renderCard(component)
            is TextButtonComponent -> renderTextButton(component)

        }

    }

    @Composable
    fun renderRow(row: RowComponent) {

        androidx.compose.foundation.layout.Row(
            modifier = row.modifier.toModifier()
        ) {

            row.content.forEach {
                render(component = it)
            }

        }

    }

    @Composable
    fun renderColumn(column: ColumnComponent) {

        androidx.compose.foundation.layout.Column(
            modifier = column.modifier.toModifier()
        ) {

            column.content.forEach {
                render(component = it)
            }

        }

    }

    @Composable
    fun renderVerticalScroll(verticalScroll: VerticalScrollComponent) {

        ScrollableColumn {

            verticalScroll.childs.forEach {
                render(component = it)
            }

        }

    }

    @Composable
    fun renderBox(box: BoxComponent) {

        androidx.compose.foundation.layout.Box(
            modifier = box.modifier.toModifier()
        ) {
            render(box.content)
        }

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

    @Composable
    fun renderCard(card: CardComponent) {

        Card(
            modifier = card.modifier.toModifier()
        ) {
            render(component = card.content)
        }

    }

    @Composable
    fun renderTextButton(textButton: TextButtonComponent) {

        TextButton(
            modifier = textButton.modifier.toModifier()
            onClick = {}
        ) {
            Text(text = textButton.text, style = TextStyle())
        }

    }


}