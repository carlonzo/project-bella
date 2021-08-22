package it.carlom.composables

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.example.devbox.ServerFlow
import com.projectbella.parser.models.Parser
import com.projectbella.renderer.Renderer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import radiography.Radiography

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var mainScope:CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScope = MainScope()

        mainScope.launch {

            val text = withContext(Dispatchers.IO) { resources.assets.open("ad-widget.json").bufferedReader().use { it.readText() } }

            val rootComponent = Parser.parse(text)

            val composeView = ComposeView(this@MainActivity)
            setContentView(composeView)

            composeView.setContent {
                Renderer.render(component = rootComponent)
            }

            ServerFlow(9000).flow.collect {

                composeView.setContent {
                    val newComponent = Parser.parse(it)
                    Renderer.render(component = newComponent)
                }

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