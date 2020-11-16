package com.example.parser.models

import kotlinx.serialization.Serializable

@Serializable
class CareemTileComponent(
    val image: String,
    val text: String
) : Component() {

    override val modifier: ModifierComponent = EmptyModifier
    override val content: String? = null

}

