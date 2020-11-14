package com.example.parser

import com.example.parser.adapters.BaseComponentAdapter
import com.example.parser.models.Component
import com.example.parser.models.ModifierComponent
import com.example.parser.models.TextComponent
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import kotlin.reflect.KClass

class ComponentAdapter(private val moshi: Moshi) : JsonAdapter<Component>() {

    val baseoptions = JsonReader.Options.of("type", "content", "modifiers")

    override fun fromJson(reader: JsonReader): Component {

        return readGenericComponent(reader)
    }

    override fun toJson(writer: JsonWriter, value: Component?) {
        throw IllegalStateException("Not supported. yet.")
    }


    fun readGenericComponent(reader: JsonReader): Component {
        reader.beginObject()

        var modifier: ModifierComponent? = null
        var content: Any? = null

        try {
            while (reader.hasNext()) {

                when (reader.selectName(baseoptions)) {
                    0 -> return findApaterForType<TextComponent>(reader.nextString()).fromJsonWithContent()
                    1 ->

                }

            }
        } finally {
            reader.endObject()
        }

    }

    private fun <C : Component> findApaterForType(type: String): BaseComponentAdapter<C> {
        return moshi.adapter<Component>(findComponentFromType(type).java) as BaseComponentAdapter<C>
    }

    private fun findComponentFromType(type: String): KClass<out Component> {

        return when (type) {
            "vstack" -> TODO()
            "hstack" -> TODO()
            "text" -> TextComponent::class
            "image" -> TODO()
            "button" -> TODO()
            "vscroll" -> TODO()
            "hscroll" -> TODO()
            "spacer" -> TODO()
            "card" -> TODO()

            else -> throw IllegalStateException("Dont have adapter form $type")
        }


    }
}