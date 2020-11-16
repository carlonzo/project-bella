package it.carlom.composables

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import com.example.parser.models.Parser
import it.carlom.feature_a.Renderer
import radiography.Radiography

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = resources.assets.open("ad-widget.json").bufferedReader().use { it.readText() }
        val rootComponent = Parser.parse(text)

        setContent {

            Surface(color = Color.White, modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                Renderer.render(component = rootComponent)
            }
        }

        Handler(Looper.getMainLooper()).post {
            val radio = Radiography.scan()
            println(radio)
        }
    }
}