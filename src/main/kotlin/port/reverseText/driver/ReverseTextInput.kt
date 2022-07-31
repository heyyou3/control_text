package port.reverseText.driver

import domain.text.Exceptions.LimitOverException
import domain.text.Exceptions.NgTextException
import domain.text.Text
import port.reverseText.usecase.ReverseTextUseCaseExecutor
import shared.port.Code

class ReverseTextInput(private val useCase: ReverseTextUseCaseExecutor) {
    fun invoke(inputText: String): Result {
        return try {
            val text = Text(inputText)
            val res = useCase.invoke(text)
            Result(Code.SUCCESS, "Success", res.value)
        } catch (e: LimitOverException) {
            Result(Code.ASSUMED_FAIL, "Input text is over limit length!", null)
        } catch (e: NgTextException) {
            Result(Code.ASSUMED_FAIL, "Input text is ng word!", null)
        } catch (e: Exception) {
            Result(Code.UNKNOWN_FAIL, "Unknown Error!", null)
        }
    }
}
