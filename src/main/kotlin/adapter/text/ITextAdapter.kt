package adapter.text

import domain.text.Text

interface ITextAdapter {
    fun save(text: Text): Boolean
}
