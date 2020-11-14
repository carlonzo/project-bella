package com.example.parser.models

open class ModifierComponent(
    open val preferredWidth: Float? = null,
    open val preferredHeight: Float? = null,
    open val fillWidth: Boolean? = null,
    open val fillHeight: Boolean? = null,
    open val padding: Float? = null
)

class TextStyleModifier(
    val color: String? = null,
    val textSize: Float? = null,
)