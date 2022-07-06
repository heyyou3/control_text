package adapter.text

import domain.text.Text

class StdOutTextAdapter : ITextAdapter {
    override fun save(text: Text): Boolean {
        println(text.text)
        return true
    }
}
