package com.example.parser.models

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Serializable
abstract class Component {
    abstract val modifier: ModifierComponent?

    @Polymorphic
    abstract val content: Any?
}

object EmptyContent : Component() {
    override val modifier: ModifierComponent? = null
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
    override val modifier: ModifierComponent? = null,
) : Component()

/**
 * @param arrangement can be SPACE_EVENLY, SPACE_AROUND, CENTER, BOTTOM, TOP
 */
@SerialName("vstack")
@Serializable
class ColumnComponent(
    val arrangement: String? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent? = null,
) : Component()

@SerialName("card")
@Serializable
class CardComponent(
    override val content: Component = EmptyContent,
    override val modifier: ModifierComponent? = null,
) : Component()

@SerialName("text")
@Serializable
class TextComponent(
    @SerialName("text_style") val textStyle: TextStyleModifier? = null,
    override val content: String = "",
    override val modifier: ModifierComponent? = null,
) : Component()

@SerialName("button")
@Serializable
class TextButtonComponent(
    @SerialName("text_style") val textStyle: TextStyleModifier? = null,
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent? = null,
) : Component()

@SerialName("image")
@Serializable
class ImageComponent(
    override val content: String? = null,
    override val modifier: ModifierComponent? = null,
) : Component()

@SerialName("vscroll")
@Serializable
class VerticalScrollComponent(
    override val content: List<Component> = emptyList(),
    override val modifier: ModifierComponent? = null,
) : Component()

@SerialName("hscroll")
@Serializable
class BoxComponent(
    override val content: Component = EmptyContent,
    override val modifier: ModifierComponent? = null,
) : Component()


////////////////////////////////////////////////

fun main() {

    val modules = SerializersModule {
        fun PolymorphicModuleBuilder<Component>.registerProjectSubclasses() {
            subclass(RowComponent::class)
            subclass(RowComponent::class)
            subclass(ColumnComponent::class)
            subclass(CardComponent::class)
            subclass(TextComponent::class)
            subclass(TextButtonComponent::class)
            subclass(ImageComponent::class)
            subclass(VerticalScrollComponent::class)
            subclass(BoxComponent::class)
        }

        polymorphic(Component::class) { registerProjectSubclasses() }
    }

    val json = Json {
        classDiscriminator = "type"
        serializersModule = modules
        ignoreUnknownKeys = true // TODO maybe disable for testing?
    }

    val raw = """
        {
            "type": "vstack",
            "content": [
                {
                    "type": "text",
                    "content": "ORDER FOOD",
                    "text-style": {
                        "font": "caption",
                        "color": "ffff90"
                    }
                },
                {
                    "type": "hstack",
                    "content": [
                        {
                            "type": "image",
                            "content": "https://d2q79iu7y748jz.cloudfront.net/s/_squarelogo/d6b73a3eee477051428131275fc1c357",
                            "modifiers": {
                                "width": 86,
                                "height": 64,
                                "corner-radius": 8
                            }
                        },
                        {
                            "type": "vstack",
                            "content": [
                                {
                                    "type": "text",
                                    "content": "Today's special",
                                    "modifiers": {
                                        "font": "body",
                                        "font-weight": "semibold",
                                        "color": "ffff90"
                                    }
                                },
                                {
                                    "type": "text",
                                    "content": "Discover today’s special offers on your favourite restaurants.",
                                    "modifiers": {
                                        "font": "callout",
                                        "font-weight": "regular",
                                        "color": "ffff90"
                                    }
                                }
                            ],
                            "modifiers": {
                                "spacing": 8
                            }
                        }
                    ],
                    "modifiers": {
                        "spacing": 16
                    }
                },
                {
                    "type": "button",
                    "action": "careem://now.careem.com/home",
                    "content": [
                        {
                            "type": "image",
                            "content": "https://d2q79iu7y748jz.cloudfront.net/s/_squarelogo/d6b73a3eee477051428131275fc1c357",
                            "modifiers": {
                                "width": 86,
                                "height": 64,
                                "corner-radius": 8
                            }
                        },
                        {
                            "type": "text",
                            "content": "Order now",
                            "text-style": {
                                "text-style": "emerald-text-style-name",
                                "font": "body",
                                "font-weight": "semibold",
                                "color": "ffff90"
                            }
                        }
                    ],
                    "modifiers": {
                        "background-color": "green10",
                        "width": -1,
                        "height": 48,
                        "corner-radius": 24
                    }
                }
            ],
            "modifiers": {
                "spacing": 16,
                "padding-all": 16
            }
        }
    """.trimIndent()

    val component = json.decodeFromString<Component>(PolymorphicSerializer(Component::class), raw)

    print(component)
}