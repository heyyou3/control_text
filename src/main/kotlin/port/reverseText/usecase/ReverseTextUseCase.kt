package port.reverseText.usecase

import domain.text.Text
import port.reverseText.output.IReverseTextOutput

class ReverseTextUseCase(private val outputPort: IReverseTextOutput) : IReverseTextUseCase {
    override fun invoke(text: Text): Text {
        val revText = Text(text.value.reversed())
        outputPort.save(revText)
        return revText
    }
}
