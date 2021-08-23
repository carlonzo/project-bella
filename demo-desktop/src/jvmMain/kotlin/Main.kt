import androidx.compose.desktop.Window
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.projectbella.parser.models.Component
import com.projectbella.parser.models.EmptyContent
import com.projectbella.parser.models.Parser
import com.projectbella.renderer.Renderer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

fun main() = Window {

	var component by remember { mutableStateOf<Component>(EmptyContent) }

	LaunchedEffect(true) {
		val text = withContext(Dispatchers.IO) {
			File(
				"/Users/CarloMarinangeli/Projects/project-bella/demo-desktop/src",
				"ad-widget.json"
			).bufferedReader().use { it.readText() }
		}

		component = Parser.parse(text)
	}

	Renderer.render(component)
}
