package it.carlom.composables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import it.carlom.feature_a.Home
import it.carlom.feature_a.Renderer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{

            Surface(color = Color.White, modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                Renderer.render(component = Home.content)
            }

        }
    }
}