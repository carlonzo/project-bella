package com.example.parser.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.PolymorphicModuleBuilder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

object Parser {

    private val modules = SerializersModule {
        fun PolymorphicModuleBuilder<Component>.registerProjectSubclasses() {
            subclass(RowComponent::class)
            subclass(ColumnComponent::class)
            subclass(CardComponent::class)
            subclass(TextComponent::class)
            subclass(TextButtonComponent::class)
            subclass(ImageComponent::class)
            subclass(VerticalScrollComponent::class)
            subclass(HorizontalScrollComponent::class)
            subclass(SpacerComponent::class)
        }

        polymorphic(Component::class) { registerProjectSubclasses() }
    }

    val json = Json {
        classDiscriminator = "type"
        serializersModule = modules
        ignoreUnknownKeys = true // TODO maybe disable for testing?
    }

    fun parse(string: String): Component{
       return json.decodeFromString(string)
    }
}


// test
private fun main() {

    val component = Parser.parse(
        """
            {
            "type": "vstack",
            "content": [
                {
                "type": "text",
                "content": "Discover today’s special offers on your favourite restaurants.",
                "text_style": {
                    "font": "callout",
                    "font-weight": "regular",
                    "color": "ffff90"
                    }
                }
            ],
            "modifier": {
                "width": 86,
                "height": 64,
                "corner-radius": 8
                }
            }
        """.trimIndent()
    )

    println(Parser.json.encodeToString(component))

}