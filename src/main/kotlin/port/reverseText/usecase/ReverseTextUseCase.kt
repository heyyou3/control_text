package port.reverseText.usecase

import domain.text.Text
import port.reverseText.output.IReverseTextOutput

class ReverseTextUseCase(private val outputPort: IReverseTextOutput) : IReverseTextUseCase {
    override fun invoke(text: Text) {
        val revText = Text(text.text.reversed())
        outputPort.save(revText)
    }
}
