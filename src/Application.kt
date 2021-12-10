package com.example

import io.ktor.application.*
import io.ktor.client.*
import io.ktor.client.engine.jetty.*
import io.ktor.client.features.logging.*
import io.ktor.response.*
import io.ktor.routing.*
import org.eclipse.jetty.util.ssl.SslContextFactory

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val client = HttpClient(Jetty) {
        engine {
            // this: HttpClientEngineConfig
            sslContextFactory = SslContextFactory.Client()
            clientCacheSize = 12
        }
        install(Logging) {
            level = LogLevel.ALL
        }

        routing {
            get ("/"){
                call.respondText("Hello, world!")
            }

            get ("/1"){
                call.respondText("Hello, world! Path '/1'")
            }
        }
    }
}

