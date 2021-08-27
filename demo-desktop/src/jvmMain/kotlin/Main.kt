import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.application
import com.projectbella.parser.models.Component
import com.projectbella.parser.models.EmptyContent
import com.projectbella.parser.models.Parser
import com.projectbella.renderer.Renderer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

fun main() = application {

	var title by remember { mutableStateOf("Loading") }

	androidx.compose.ui.window.Window(
		onCloseRequest = { exitApplication() },
		resizable = true,
		title = title
	) {

		var component by remember { mutableStateOf<Component>(EmptyContent) }

		LaunchedEffect(true) {
			val text = withContext(Dispatchers.IO) {
				File(
					"/Users/CarloMarinangeli/Projects/project-bella/demo-desktop/src",
					"ad-widget.json"
				).bufferedReader().use { it.readText() }
			}

			component = Parser.parse(text)

			title = "Project Bella demo"
		}

		Renderer.render(component)

	}

}
