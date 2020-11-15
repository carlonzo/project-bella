package com.example.parser.models

import kotlinx.serialization.Serializable

@Serializable
class CareemTileComponent(
    val image: String,
    val text: String
) : Component() {

    override val modifier: ModifierComponent?
        get() = null
    override val content: Any?
        get() = null

}

