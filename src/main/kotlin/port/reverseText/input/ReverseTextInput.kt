package port.reverseText.input

import domain.text.Exceptions.LimitOverException
import domain.text.Exceptions.NgTextException
import domain.text.Text
import port.reverseText.usecase.IReverseTextUseCase

class ReverseTextInput(private val useCase: IReverseTextUseCase) {
    fun invoke(inputText: String): Result {
        return try {
            val text = Text(inputText)
            useCase.invoke(text)
            Result(Code.SUCCESS, "Success")
        } catch (e: LimitOverException) {
            Result(Code.ASSUMED_FAIL, "Input text is over limit length!")
        } catch (e: NgTextException) {
            Result(Code.ASSUMED_FAIL, "Input text is ng word!")
        } catch (e: Exception) {
            Result(Code.UNKNOWN_FAIL, "Unknown Error!")
        }
    }
}
