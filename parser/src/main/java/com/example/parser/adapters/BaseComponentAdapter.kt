package com.example.parser.adapters

import com.example.parser.models.Component
import com.example.parser.models.ModifierComponent
import com.squareup.moshi.JsonAdapter

abstract class  BaseComponentAdapter<C: Component>(): JsonAdapter<C>() {

    abstract fun fromJsonWithContent( content: Any? = null,
                             modifier: ModifierComponent? = null): C
}