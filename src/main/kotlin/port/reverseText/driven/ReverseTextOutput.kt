package port.reverseText.driven

import adapter.driven.text.ITextAdapter
import domain.text.Text

class ReverseTextOutput(private val textAdapter: ITextAdapter) : ReverseTextDriven {
    override fun save(text: Text) {
        textAdapter.save(text)
    }
}
