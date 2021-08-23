package com.example.devbox

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.nio.ByteBuffer

@ExperimentalCoroutinesApi
class ServerFlow(private val port: Int) {

	val flow = callbackFlow {

		val aSocket = aSocket(ActorSelectorManager(Dispatchers.IO))
		val tcpSocketBuilder = aSocket.tcp()

		val server = tcpSocketBuilder.bind(port = port)
		println("Created connection with ${server.localAddress}: $server")

		var active = true

		val job = launch(context = Dispatchers.IO) {
			val buffer = ByteBuffer.allocate(1 * 1024 * 1024)

			while (active) {
				val socket = server.accept()
				val input = socket.openReadChannel()
				buffer.clear()

				val builder = StringBuilder()
				do {
					buffer.clear()
					input.readAvailable(buffer)
					builder.append(String(buffer.array().copyOfRange(0, buffer.position())))
				} while (buffer.remaining() == 0)

				trySend(builder.toString())
				socket.close()
			}
		}


		awaitClose {
			active = false
			job.cancel()
			server.close()
		}
	}.flowOn(Dispatchers.IO)


}