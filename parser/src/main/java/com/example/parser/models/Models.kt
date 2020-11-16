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
class RowComponent(
    val arrangement: String? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

/**
 * @param arrangement can be SPACE_EVENLY, SPACE_AROUND, CENTER, BOTTOM, TOP
 */
@SerialName("vstack")
@Serializable
class ColumnComponent(
    val arrangement: String? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("card")
@Serializable
class CardComponent(
    override val content: Component = EmptyContent,
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("text")
@Serializable
class TextComponent(
    @SerialName("text_style") val textStyle: TextStyleModifier = EmptyTextStyle,
    override val content: String = "",
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("button")
@Serializable
class TextButtonComponent(
    @SerialName("text_style") val textStyle: TextStyleModifier = EmptyTextStyle,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("image")
@Serializable
class ImageComponent(
    override val content: String? = null,
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("vscroll")
@Serializable
class VerticalScrollComponent(
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()

@SerialName("hscroll")
@Serializable
class BoxComponent(
    override val content: Component = EmptyContent,
    override val modifier: ModifierComponent = EmptyModifier,
) : Component()
