package com.example.parser.models

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class Component {
    abstract val modifier: ModifierComponent

    @Polymorphic
    abstract val content: Any?
}

object EmptyContent : Component() {
    override val modifier: ModifierComponent = EmptyModifier
    override val content: Any? = null
}

/**
 * @param arrangement can be SPACE_EVENLY, SPACE_AROUND, CENTER, START, END
 */
@SerialName("hstack")
@Serializable
data class RowComponent(
    val arrangement: String? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

/**
 * @param arrangement can be SPACE_EVENLY, SPACE_AROUND, CENTER, BOTTOM, TOP
 */
@SerialName("vstack")
@Serializable
data class ColumnComponent(
    val arrangement: String? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("card")
@Serializable
data class CardComponent(
    override val content: Component = EmptyContent,
    override val modifier: ModifierComponent = EmptyModifier,
    val elevation: Int = 1
) : Component()

@SerialName("text")
@Serializable
data class TextComponent(
    @SerialName("text_style") val textStyle: TextStyleModifier = EmptyTextStyle,
    override val content: String = "",
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("button")
@Serializable
data class TextButtonComponent(
    @SerialName("text_style") val textStyle: TextStyleModifier = EmptyTextStyle,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
    @SerialName("background_color") val backgroundColor: String? = null,
    @SerialName("corner_radius") val cornerRadius: Int? = null
) : Component()

@SerialName("image")
@Serializable
data class ImageComponent(
    override val content: String? = null,
    override val modifier: ModifierComponent = EmptyModifier,
    @SerialName("corner_radius") val cornerRadius: Int? = null
) : Component()

@SerialName("vscroll")
@Serializable
data class VerticalScrollComponent(
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

//@SerialName("hscroll")
//@Serializable
data class HorizontalScrollComponent(
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("spacer")
@Serializable
data class SpacerComponent(
    override val content: String? = null,
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()


