package hw6

import hw6.tokens.*

class Tokenizer(private val visitor: TokenVisitor) {
  private interface State {
    fun nextChar(c: Char)
  }

  private val initialState = object : State {
    override fun nextChar(c: Char) {
      when {
        c.isWhitespace() -> return
        c == '\u0000' -> return
        c.isDigit() -> {
          state = NumberState()
          state.nextChar(c)
        }
        else -> {
          CHAR_TO_TOKEN[c]?.accept(visitor) ?: throw RuntimeException("Unexpected character $c")
        }
      }
    }
  }

  inner class NumberState : State {
    private var value: Double = 0.0

    override fun nextChar(c: Char) {
      if (c.isDigit()) {
        value = value * 10 + (c - '0')
      } else {
        NumberToken(value).accept(visitor)
        state = initialState
        state.nextChar(c)
      }
    }
  }

  private var state: State = initialState

  fun nextChar(c: Char) {
    state.nextChar(c)
  }

  companion object {
    fun tokenize(s: String, visitor: TokenVisitor) {
      val tokenizer = Tokenizer(visitor)
      s.forEach(tokenizer::nextChar)
      tokenizer.nextChar('\u0000')
    }

    private val CHAR_TO_TOKEN = listOf(OperatorPlus, OperatorMinus, OperatorMul, OperatorDiv)
      .map { it.char to it }.toMap()
      .plus(listOf('(' to Brace(true), ')' to Brace(false)))
  }
}