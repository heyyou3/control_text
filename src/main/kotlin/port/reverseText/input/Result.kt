package port.reverseText.input

enum class Code(val number: Int) {
    SUCCESS(0), ASSUMED_FAIL(1), UNKNOWN_FAIL(255)
}

data class Result(val code: Code, val message: String)
