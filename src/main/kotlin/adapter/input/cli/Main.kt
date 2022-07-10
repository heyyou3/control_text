package adapter.input.cli

import adapter.output.text.StdOutTextAdapter
import port.reverseText.input.ReverseTextInput
import port.reverseText.output.ReverseTextOutput
import port.reverseText.usecase.ReverseTextUseCase
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val textInput = ReverseTextInput(ReverseTextUseCase(ReverseTextOutput(StdOutTextAdapter())))
    val res = textInput.invoke(args[0])
    if (res.code.number != 0) {
        println("Failed execute...")
        println(res.message)
        exitProcess(res.code.number)
    }
    println("Response Data is ${res.data}")
}
