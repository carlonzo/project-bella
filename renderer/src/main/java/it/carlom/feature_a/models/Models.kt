package it.carlom.feature_a.models


abstract class Component(
    open val modifier: ModifierComponent?,
    open val content: Any? = null
)

object EmptyContent : Component(null)

class RowComponent(
    val arrangement: String? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

class ColumnComponent(
    override val content: List<Component> = emptyList(),
    val arrangement: String? = null,
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

class CardComponent(
    override val content: Component = EmptyContent,
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

class TextComponent(
    val textStyle: TextStyleModifier? = null,
    override val content: String,
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

class TextButtonComponent(
    val textStyle: TextStyleModifier? = null,
    override val content: String,
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

class ImageComponent(
    val url: String? = null,
    val res: Int? = null,
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

class VerticalScrollComponent(
    val id: String? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

class BoxComponent(
    val id: String? = null,
    override val content: Component = EmptyContent,
    override val modifier: ModifierComponent? = null,
) : Component(modifier)

