package it.carlom.composables

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.example.parser.models.Parser
import it.carlom.feature_a.Renderer
import kotlinx.coroutines.*
import radiography.Radiography

class MainActivity : AppCompatActivity() {
    private lateinit var mainScope:CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScope = MainScope()

        mainScope.launch {

            val text = withContext(Dispatchers.IO) { resources.assets.open("ad-widget.json").bufferedReader().use { it.readText() } }

            val rootComponent = Parser.parse(text)

            setContent {
                Renderer.render(component = rootComponent)
            }
        }

        Handler(Looper.getMainLooper()).post {
            val radio = Radiography.scan()
            println(radio)
        }
    }

    override fun onDestroy() {
        mainScope.cancel()
        super.onDestroy()
    }
}