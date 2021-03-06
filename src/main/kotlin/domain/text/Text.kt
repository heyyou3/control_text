package domain.text

import domain.text.Exceptions.LimitOverException
import domain.text.Exceptions.NgTextException

class Text(val value: String) {
    private val textLimit: Int = 100
    private val ngTexts: List<String> = listOf("ng")

    init {
        if (isLimit(value)) {
            throw LimitOverException()
        }

        if (isNgText(value)) {
            throw NgTextException()
        }
    }

    private fun isLimit(text: String): Boolean {
        return text.length > textLimit
    }

    private fun isNgText(text: String): Boolean {
        return ngTexts.none {
            val reg = Regex(it)
            return reg.containsMatchIn(text.lowercase())
        }
    }
}
