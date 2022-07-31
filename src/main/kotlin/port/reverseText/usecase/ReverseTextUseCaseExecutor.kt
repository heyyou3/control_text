package port.reverseText.usecase

import domain.text.Text

interface ReverseTextUseCaseExecutor {
    fun invoke(text: Text): Text
}
