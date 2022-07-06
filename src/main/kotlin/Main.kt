import adapter.text.StdOutTextAdapter
import port.reverseText.input.ReverseTextInput
import port.reverseText.output.ReverseTextOutput
import port.reverseText.usecase.ReverseTextUseCase
import kotlin.system.exitProcess

fun main() {
    val textInput = ReverseTextInput(ReverseTextUseCase(ReverseTextOutput(StdOutTextAdapter())))
    val res = textInput.invoke("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
    if (res.code.number != 0) {
        println("Failed execute...")
        println(res.message)
        exitProcess(res.code.number)
    }
}
