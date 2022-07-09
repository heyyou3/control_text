package adapter.output.text

import domain.text.Text

class StdOutTextAdapter : ITextAdapter {
    override fun save(text: Text): Boolean {
        println(text.value)
        return true
    }
}
