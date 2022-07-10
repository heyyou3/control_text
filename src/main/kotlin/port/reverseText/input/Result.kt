package port.reverseText.input

import shared.port.Code
import shared.port.IResult

data class Result(override val code: Code, override val message: String, val data: String?) : IResult
