package port.reverseText.driver

import domain.text.Text
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import port.reverseText.usecase.ReverseTextUseCaseExecutor
import shared.port.Code
import kotlin.test.assertEquals

internal class MockUseCaseExecutor() : ReverseTextUseCaseExecutor {
    override fun invoke(text: Text): Text {
        return Text("ABC")
    }
}

internal class MockExceptionUseCaseExecutor() : ReverseTextUseCaseExecutor {
    override fun invoke(text: Text): Text {
        throw Exception("Unknown Error")
    }
}

internal class ReverseTextInputTest {

    @Nested
    inner class TestSuccess() {

        private lateinit var result: Result

        @BeforeEach
        fun beforeEach() {
            val textInput = ReverseTextInput(MockUseCaseExecutor())
            this.result = textInput.invoke("A")
        }

        @Test
        fun returnSuccessCode() {
            assertEquals(Code.SUCCESS, result.code)
        }
    }

    @Nested
    inner class TestLimitFail() {
        private lateinit var result: Result

        @BeforeEach
        fun beforeEach() {
            val textInput = ReverseTextInput(MockUseCaseExecutor())
            this.result =
                // NOTE: 101 Character
                textInput.invoke("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB")
        }

        @Test
        fun returnTextLimitCodeFail() {
            assertEquals(Code.ASSUMED_FAIL, result.code)
        }

        @Test
        fun returnTextLimitMessageFail() {
            assertEquals("Input text is over limit length!", result.message)
        }
    }

    @Nested
    inner class TestNgTextFail() {
        @Test
        fun returnNgTextCodeFail() {
            val textInput = ReverseTextInput(MockUseCaseExecutor())
            val res = textInput.invoke("ng")
            assertEquals(Code.ASSUMED_FAIL, res.code)
        }

        @Test
        fun returnNgTextMessageFail() {
            val textInput = ReverseTextInput(MockUseCaseExecutor())
            val res = textInput.invoke("ng")
            assertEquals("Input text is ng word!", res.message)
        }

        @Test
        fun returnNgUpperCaseFail() {
            val textInput = ReverseTextInput(MockUseCaseExecutor())
            val res = textInput.invoke("NG")
            assertEquals(Code.ASSUMED_FAIL, res.code)
        }
    }

    @Nested
    inner class TestUnknownFail() {
        @Test
        fun returnUnknownCodeFail() {
            val textInput = ReverseTextInput(MockExceptionUseCaseExecutor())
            val res = textInput.invoke("ABC")
            assertEquals(Code.UNKNOWN_FAIL, res.code)
        }
    }
}
