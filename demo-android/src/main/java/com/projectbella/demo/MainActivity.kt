package com.projectbella.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.projectbella.parser.models.Parser
import com.projectbella.renderer.Renderer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
	private lateinit var mainScope: CoroutineScope

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

//			ServerFlow(9000).flow.collect { input: String ->
//
//				composeView.setContent {
//					val newComponent = Parser.parse(input)
//					Renderer.render(component = newComponent)
//				}
//
//			}
		}
	}

	override fun onDestroy() {
		mainScope.cancel()
		super.onDestroy()
	}
}
