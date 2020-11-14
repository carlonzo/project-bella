package com.example.parser.adapters

import com.example.parser.models.ModifierComponent
import com.example.parser.models.TextComponent
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter

class TextComponentAdapter(): BaseComponentAdapter<TextComponent>() {



    override fun fromJson(reader: JsonReader): TextComponent? {

        var content: String? = content
        var modifier = modifier




    }

    override fun toJson(writer: JsonWriter, value: TextComponent?) {
        TODO("Not yet implemented")
    }

}