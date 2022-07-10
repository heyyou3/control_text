package shared.port

enum class Code(val number: Int) {
    SUCCESS(0), ASSUMED_FAIL(1), UNKNOWN_FAIL(255)
}

interface IResult {
    val code: Code
    val message: String
}
