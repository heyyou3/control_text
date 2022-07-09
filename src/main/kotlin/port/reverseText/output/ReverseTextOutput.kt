package port.reverseText.output

import adapter.output.text.ITextAdapter
import domain.text.Text

class ReverseTextOutput(private val textAdapter: ITextAdapter) : IReverseTextOutput {
    override fun save(text: Text) {
        textAdapter.save(text)
    }
}
