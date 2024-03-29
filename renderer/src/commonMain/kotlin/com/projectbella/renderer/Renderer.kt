@file:Suppress("RemoveRedundantQualifierName")

package com.projectbella.renderer

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.projectbella.parser.models.CardComponent
import com.projectbella.parser.models.ColumnComponent
import com.projectbella.parser.models.Component
import com.projectbella.parser.models.EmptyContent
import com.projectbella.parser.models.HorizontalScrollComponent
import com.projectbella.parser.models.ImageComponent
import com.projectbella.parser.models.RowComponent
import com.projectbella.parser.models.SpacerComponent
import com.projectbella.parser.models.TextButtonComponent
import com.projectbella.parser.models.TextComponent
import com.projectbella.parser.models.VerticalScrollComponent
import com.projectbella.renderer.composables.ImagePlatform
import com.projectbella.renderer.modifiers.toColor
import com.projectbella.renderer.modifiers.toHorizontalArrangement
import com.projectbella.renderer.modifiers.toModifier
import com.projectbella.renderer.modifiers.toTextStyle

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

		ImagePlatform(
			source = component.content ?: "",
			modifier = component.modifier
				.toModifier()
				.clip(shape = shape)
				.testTag("ImagePlatform")
		)

	}

	@Composable
	fun renderText(text: TextComponent) {
		androidx.compose.material.Text(
			text = text.content,
			style = text.textStyle.toTextStyle(),
			modifier = text.modifier
				.toModifier()
				.testTag("Text")
		)
	}

	@Composable
	fun renderRow(row: RowComponent) {

		androidx.compose.foundation.layout.Row(
			modifier = row.modifier
				.toModifier()
				.testTag("Row"),
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
			modifier = column.modifier
				.toModifier()
				.testTag("Column")
		) {

			column.content.forEach {
				render(component = it)
			}

		}

	}

	@Composable
	fun renderVerticalScroll(verticalScroll: VerticalScrollComponent) {

		androidx.compose.foundation.layout.Column(
			modifier = verticalScroll.modifier
				.toModifier()
				.verticalScroll(rememberScrollState())
				.testTag("VerticalScroll"),
		) {
			verticalScroll.content.forEach {
				render(component = it)
			}
		}

	}

	@Composable
	fun renderHorizontalScroll(horizontalScroll: HorizontalScrollComponent) {

		androidx.compose.foundation.layout.Row(
			modifier = horizontalScroll.modifier
				.toModifier()
				.horizontalScroll(rememberScrollState())
				.testTag("HorizontalScroll"),
		) {
			horizontalScroll.content.forEach {
				render(component = it)
			}
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

		println(textButton)

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
			@Suppress("RedundantExplicitType")
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