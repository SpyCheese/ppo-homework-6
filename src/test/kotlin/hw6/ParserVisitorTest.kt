package hw6

import hw6.tokens.*
import junit.framework.TestCase
import kotlin.test.assertFailsWith

class ParserVisitorTest : TestCase() {
  fun testNumber() {
    assertEquals(listOf(NumberToken(123.0)), parse(listOf(NumberToken(123.0))))
  }

  fun testOperators() {
    for (op in listOf(OperatorPlus, OperatorMinus, OperatorMul, OperatorDiv)) {
      assertEquals(listOf(
        NumberToken(1.0),
        NumberToken(2.0),
        op
      ), parse(listOf(
        NumberToken(1.0),
        op,
        NumberToken(2.0)
      )))
    }
  }

  fun testAssoc() {
    assertEquals(listOf(
      NumberToken(1.0),
      NumberToken(2.0),
      OperatorPlus,
      NumberToken(3.0),
      OperatorMinus
    ), parse(listOf(
      NumberToken(1.0),
      OperatorPlus,
      NumberToken(2.0),
      OperatorMinus,
      NumberToken(3.0)
    )))
  }

  fun testPrecedence() {
    assertEquals(listOf(
      NumberToken(1.0),
      NumberToken(2.0),
      NumberToken(3.0),
      OperatorMul,
      OperatorPlus
    ), parse(listOf(
      NumberToken(1.0),
      OperatorPlus,
      NumberToken(2.0),
      OperatorMul,
      NumberToken(3.0)
    )))
  }

  fun testBraces() {
    assertEquals(listOf(
      NumberToken(1.0),
      NumberToken(2.0),
      NumberToken(3.0),
      OperatorMinus,
      OperatorDiv
    ), parse(listOf(
      NumberToken(1.0),
      OperatorDiv,
      Brace(true),
      NumberToken(2.0),
      OperatorMinus,
      NumberToken(3.0),
      Brace(false)
    )))
  }

  fun testNotClosedBrace() {
    assertFailsWith<RuntimeException> {
      parse(listOf(
        Brace(true),
        NumberToken(1.0),
        OperatorMul,
        NumberToken(2.0)
      ))
    }
  }

  fun testExtraClosingBrace() {
    assertFailsWith<RuntimeException> {
      parse(listOf(
        Brace(true),
        NumberToken(1.0),
        OperatorMul,
        NumberToken(2.0),
        Brace(false),
        Brace(false)
      ))
    }
  }

  companion object {
    fun parse(tokens: List<Token>): List<Token> {
      val visitor = ParserVisitor()
      tokens.forEach { it.accept(visitor) }
      return visitor.getResult()
    }
  }
}