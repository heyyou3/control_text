package port.reverseText.usecase

import domain.text.Text

interface IReverseTextUseCase {
    fun invoke(text: Text): Text
}
