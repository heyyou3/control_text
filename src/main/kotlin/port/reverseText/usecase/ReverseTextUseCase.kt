package port.reverseText.usecase

import domain.text.Text
import port.reverseText.driven.ReverseTextDriven

class ReverseTextUseCase(private val outputPort: ReverseTextDriven) : ReverseTextUseCaseExecutor {
    override fun invoke(text: Text): Text {
        val revText = Text(text.value.reversed())
        outputPort.save(revText)
        return revText
    }
}
