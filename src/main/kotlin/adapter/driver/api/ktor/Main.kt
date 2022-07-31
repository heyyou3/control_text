package adapter.driver.api.ktor

import adapter.driven.text.StdOutTextAdapter
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import port.reverseText.driven.ReverseTextOutput
import port.reverseText.driver.ReverseTextInput
import port.reverseText.usecase.ReverseTextUseCase
import shared.port.Code
import java.text.DateFormat

data class ReverseTextResponse(val text: String)
data class ReverseTextErrorResponse(val message: String)

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            gson {
                setDateFormat(DateFormat.LONG)
                setPrettyPrinting()
            }
        }
        routing {
            get("/api/reverse_text") {
                val reqText = call.request.queryParameters["text"]
                if (reqText == null) {
                    call.respond(HttpStatusCode.BadRequest, ReverseTextErrorResponse("required text query parameter"))
                    return@get
                }
                val textInput = ReverseTextInput(ReverseTextUseCase(ReverseTextOutput(StdOutTextAdapter())))
                val res = textInput.invoke(reqText)
                val reverseText = res.data ?: ""
                when (res.code) {
                    Code.SUCCESS -> call.respond(HttpStatusCode.OK, ReverseTextResponse(reverseText))
                    Code.ASSUMED_FAIL -> call.respond(HttpStatusCode.BadRequest, ReverseTextErrorResponse(res.message))
                    Code.UNKNOWN_FAIL -> call.respond(
                        HttpStatusCode.InternalServerError,
                        ReverseTextErrorResponse(res.message)
                    )
                }
            }
        }
    }
    server.start(wait = true)
}
