import androidx.compose.desktop.Window
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import com.projectbella.renderer.desktop.Renderer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import models.Component
import models.EmptyContent
import models.Parser
import java.io.File

fun main() = Window {

    var component by remember { mutableStateOf<Component>(EmptyContent) }

    LaunchedEffect(true) {
        val text = withContext(Dispatchers.IO) { File("/home/carlo/Projects/project-bella/renderer-desktop/src","ad-widget.json").bufferedReader().use { it.readText() } }

        component = Parser.parse(text)
    }

    Renderer.render(component)
}
