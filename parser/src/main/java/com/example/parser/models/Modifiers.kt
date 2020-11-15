package com.example.parser.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ModifierComponent(
    val width: Float? = null,
    val height: Float? = null,
    val fillWidth: Boolean? = null,
    val fillHeight: Boolean? = null,
    val paddingAll: Float? = null,
    val padding: List<Float>? = null, // [top, right, bottom, left]
    @SerialName("corner_radius") val cornerRadius: Float? = null
)

@Serializable
data class TextStyleModifier(
    val color: String? = null,
    @SerialName("text_size") val textSize: Float? = null,
)