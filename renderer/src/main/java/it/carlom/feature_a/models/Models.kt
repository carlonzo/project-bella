package it.carlom.feature_a.models


abstract class Component(
)

object EmptyContent : Component()

class RowComponent(
    val content: List<Component> = emptyList(),
    val modifier: RowModifier? = null,
) : Component()

class ColumnComponent(
    val content: List<Component> = emptyList(),
    val modifier: ColumnModifier? = null,
) : Component()

class CardComponent(
    val content: Component = EmptyContent,
    val modifier: ModifierComponent? = null,
) : Component()

class TextComponent(
    val text: String,
    val modifier: TextModifier? = null,
) : Component()

class TextButtonComponent(
    val text: String,
    val modifier: TextModifier? = null,
) : Component()

class ImageComponent(
    val url: String? = null,
    val res: Int? = null,
    val modifier: TextModifier? = null,
) : Component()

class VerticalScrollComponent(
    val id: String? = null,
    val childs: List<Component> = emptyList(),
    val modifier: ModifierComponent? = null,
) : Component()

class BoxComponent(
    val id: String? = null,
    val content: Component = EmptyContent,
    val modifier: ModifierComponent? = null,
) : Component()

class CareemTileComponent(
    val image: String,
    val text: String
) : Component() {

}


