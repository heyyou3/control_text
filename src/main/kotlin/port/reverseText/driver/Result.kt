package port.reverseText.driver

import shared.port.Code
import shared.port.ResultDefiner

data class Result(override val code: Code, override val message: String, val data: String?) : ResultDefiner
