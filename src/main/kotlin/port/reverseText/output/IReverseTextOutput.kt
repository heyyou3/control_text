package port.reverseText.output

import domain.text.Text

interface IReverseTextOutput {
    fun save(text: Text)
}
