package hw6

import hw6.tokens.*
import junit.framework.TestCase
import kotlin.test.assertFailsWith
import kotlin.test.expect

class TokenizerTest : TestCase() {
  fun testEmpty() {
    assertEquals(listOf<Token>(), tokenize(""))
    assertEquals(listOf<Token>(), tokenize("    \n  "))
  }

  fun testNumbers() {
    assertEquals(listOf<Token>(NumberToken(0.0)), tokenize("0"))
    assertEquals(listOf<Token>(NumberToken(1.0), NumberToken(123.0)), tokenize("1 123"))
  }

  fun testOperators() {
    assertEquals(listOf(
      NumberToken(1.0),
      OperatorPlus,
      NumberToken(2.0),
      OperatorMinus,
      NumberToken(3.0),
      OperatorMul,
      NumberToken(4.0),
      OperatorDiv,
      NumberToken(5.0)
    ), tokenize("1+2-3*4/5"))
  }

  fun testBraces() {
    assertEquals(listOf<Token>(
      NumberToken(1.0),
      OperatorPlus,
      Brace(true),
      Brace(true),
      NumberToken(2.0),
      OperatorPlus,
      NumberToken(3.0),
      Brace(false),
      Brace(false)
    ), tokenize("1+((2+3))"))
  }

  fun testInvalidChar() {
    assertFailsWith<RuntimeException> {
      tokenize("1 + x")
    }
  }

  companion object {
    private fun tokenize(s: String): List<Token> {
      val tokens = mutableListOf<Token>()
      val visitor = object : TokenVisitor {
        override fun visit(token: NumberToken) {
          tokens.add(token)
        }

        override fun visit(token: Operator) {
          tokens.add(token)
        }

        override fun visit(token: Brace) {
          tokens.add(token)
        }
      }
      Tokenizer.tokenize(s, visitor)
      return tokens
    }
  }
}