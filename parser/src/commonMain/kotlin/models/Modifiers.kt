package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val EmptyModifier = ModifierComponent()
val EmptyTextStyle = TextStyleModifier()

@Serializable
data class ModifierComponent(
    val width: Int? = null,
    val height: Int? = null,
    @SerialName("fill_width") val fillWidth: Boolean? = null,
    @SerialName("fill_height") val fillHeight: Boolean? = null,
    @SerialName("padding_all")val paddingAll: Float? = null,
    val padding: List<Int>? = null, // [top, right, bottom, left]
    @SerialName("corner_radius") val cornerRadius: Float? = null
)

@Serializable
data class TextStyleModifier(
    val color: String? = null,
    val font: String? = null,
    @SerialName("font_weight") val fontWeight: String? = null,
    @SerialName("size") val size: Int? = null,
    @SerialName("line_height") val lineHeight: Int? = null,
)